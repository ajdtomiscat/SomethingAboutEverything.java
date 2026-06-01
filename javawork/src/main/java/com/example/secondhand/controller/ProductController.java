
package com.example.secondhand.controller;

import com.example.secondhand.entity.Category;
import com.example.secondhand.entity.Product;
import com.example.secondhand.entity.User;
import com.example.secondhand.service.CategoryService;
import com.example.secondhand.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private com.example.secondhand.service.ReviewService reviewService;

    @GetMapping
    public String list(@RequestParam(required = false) Long categoryId,
                      @RequestParam(required = false) String keyword,
                      Model model) {
        List<Product> products;
        if (keyword != null && !keyword.isEmpty()) {
            products = productService.search(keyword);
        } else if (categoryId != null) {
            products = productService.findByCategoryId(categoryId);
        } else {
            products = productService.findAll();
        }
        model.addAttribute("products", products);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("keyword", keyword);
        return "product/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model, HttpSession session) {
        Product product = productService.findById(id);
        if (product == null) {
            return "redirect:/products";
        }
        productService.incrementViews(id);
        model.addAttribute("product", product);
        model.addAttribute("currentUser", session.getAttribute("user"));
        // 获取商品评价
        model.addAttribute("reviews", reviewService.findByProductId(id));
        return "product/detail";
    }

    @GetMapping("/publish")
    public String publishPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("categories", categoryService.findAll());
        return "product/publish";
    }

    @PostMapping("/publish")
    public String publish(@RequestParam String title, @RequestParam String description,
                         @RequestParam BigDecimal price, @RequestParam(required = false) BigDecimal originalPrice,
                         @RequestParam Long categoryId,
                         @RequestParam(required = false, defaultValue = "[]") String images,
                         HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        Product product = new Product();
        product.setUserId(user.getId());
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setOriginalPrice(originalPrice);
        product.setCategoryId(categoryId);
        product.setImages(images);
        productService.create(product);
        return "redirect:/products";
    }

    @GetMapping("/my")
    public String myProducts(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        List<Product> products = productService.findByUserId(user.getId());
        model.addAttribute("products", products);
        return "product/my";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable Long id, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        Product product = productService.findById(id);
        if (product == null || !product.getUserId().equals(user.getId())) {
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAll());
        return "product/edit";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id, @RequestParam String title, @RequestParam String description,
                      @RequestParam BigDecimal price, @RequestParam(required = false) BigDecimal originalPrice,
                      @RequestParam Long categoryId,
                      @RequestParam(required = false, defaultValue = "[]") String images,
                      HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        Product product = productService.findById(id);
        if (product == null || !product.getUserId().equals(user.getId())) {
            return "redirect:/products";
        }
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setOriginalPrice(originalPrice);
        product.setCategoryId(categoryId);
        product.setImages(images);
        productService.update(product);
        return "redirect:/products/my";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        Product product = productService.findById(id);
        if (product != null && product.getUserId().equals(user.getId())) {
            productService.delete(id);
        }
        return "redirect:/products/my";
    }

    @GetMapping("/{id}/toggleStatus")
    public String toggleStatus(@PathVariable Long id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        Product product = productService.findById(id);
        if (product != null && product.getUserId().equals(user.getId())) {
            if (product.getStatus() == 1) {
                product.setStatus(3); // 下架
            } else if (product.getStatus() == 3) {
                product.setStatus(1); // 上架
            }
            productService.update(product);
        }
        return "redirect:/products/my";
    }
}
