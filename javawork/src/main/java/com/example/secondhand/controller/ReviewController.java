
package com.example.secondhand.controller;

import com.example.secondhand.entity.Order;
import com.example.secondhand.entity.Review;
import com.example.secondhand.entity.User;
import com.example.secondhand.service.OrderService;
import com.example.secondhand.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/create/{orderId}")
    public String createPage(@PathVariable Long orderId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        Order order = orderService.findById(orderId);
        if (order == null || !order.getBuyerId().equals(user.getId())) {
            return "redirect:/orders/buy";
        }
        model.addAttribute("order", order);
        return "review/create";
    }

    @PostMapping("/create")
    public String create(@RequestParam Long orderId, @RequestParam Integer rating,
                        @RequestParam(required = false) String content, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        try {
            Review review = new Review();
            review.setOrderId(orderId);
            review.setUserId(user.getId());
            review.setRating(rating);
            review.setContent(content);
            review.setImages("[]");
            reviewService.create(review);
            return "redirect:/orders/buy";
        } catch (RuntimeException e) {
            return "redirect:/reviews/create/" + orderId + "?error=" + e.getMessage();
        }
    }

    @GetMapping("/product/{productId}")
    public String productReviews(@PathVariable Long productId, Model model) {
        List<Review> reviews = reviewService.findByProductId(productId);
        model.addAttribute("reviews", reviews);
        return "review/list";
    }
}
