
package com.example.secondhand.controller;

import com.example.secondhand.entity.Order;
import com.example.secondhand.entity.Product;
import com.example.secondhand.entity.User;
import com.example.secondhand.service.OrderService;
import com.example.secondhand.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @GetMapping("/buy")
    public String buyerOrders(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        List<Order> orders = orderService.findByBuyerId(user.getId());
        model.addAttribute("orders", orders);
        return "order/buyer";
    }

    @GetMapping("/sell")
    public String sellerOrders(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        List<Order> orders = orderService.findBySellerId(user.getId());
        model.addAttribute("orders", orders);
        return "order/seller";
    }

    @PostMapping("/create")
    public String create(@RequestParam Long productId, @RequestParam String address,
                        @RequestParam(required = false) String remark, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        try {
            Order order = new Order();
            order.setBuyerId(user.getId());
            order.setProductId(productId);
            order.setAddress(address);
            order.setRemark(remark);
            orderService.create(order);
            return "redirect:/orders/buy";
        } catch (RuntimeException e) {
            return "redirect:/products/" + productId + "?error=" + e.getMessage();
        }
    }

    @PostMapping("/{id}/pay")
    public String pay(@PathVariable Long id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        Order order = orderService.findById(id);
        if (order != null && order.getBuyerId().equals(user.getId()) && order.getStatus() == 1) {
            order.setStatus(2);
            orderService.update(order);
        }
        return "redirect:/orders/buy";
    }

    @PostMapping("/{id}/ship")
    public String ship(@PathVariable Long id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        Order order = orderService.findById(id);
        if (order != null && order.getSellerId().equals(user.getId()) && order.getStatus() == 2) {
            order.setStatus(3);
            orderService.update(order);
        }
        return "redirect:/orders/sell";
    }

    @PostMapping("/{id}/receive")
    public String receive(@PathVariable Long id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        Order order = orderService.findById(id);
        if (order != null && order.getBuyerId().equals(user.getId()) && order.getStatus() == 3) {
            order.setStatus(4);
            orderService.update(order);
        }
        return "redirect:/orders/buy";
    }

    @PostMapping("/{id}/cancel")
    public String cancel(@PathVariable Long id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        Order order = orderService.findById(id);
        if (order != null && order.getBuyerId().equals(user.getId()) && order.getStatus() == 1) {
            order.setStatus(5);
            orderService.update(order);
            Product product = productService.findById(order.getProductId());
            if (product != null) {
                product.setStatus(1);
                productService.update(product);
            }
        }
        return "redirect:/orders/buy";
    }
}
