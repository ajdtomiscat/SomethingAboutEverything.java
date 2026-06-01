
package com.example.secondhand.controller;

import com.example.secondhand.entity.Message;
import com.example.secondhand.entity.User;
import com.example.secondhand.service.MessageService;
import com.example.secondhand.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String list(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        List<Message> conversations = messageService.findConversations(user.getId());
        int unreadCount = messageService.findByToUserId(user.getId()).size();
        messageService.markAsRead(user.getId());
        model.addAttribute("conversations", conversations);
        model.addAttribute("unreadCount", unreadCount);
        return "message/list";
    }

    @GetMapping("/chat/{userId}")
    public String chat(@PathVariable Long userId, HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }
        User targetUser = userService.findById(userId);
        if (targetUser == null) {
            return "redirect:/messages";
        }
        List<Message> conversation = messageService.findConversation(currentUser.getId(), userId);
        model.addAttribute("conversation", conversation);
        model.addAttribute("targetUser", targetUser);
        model.addAttribute("currentUser", currentUser);
        return "message/chat";
    }

    @PostMapping("/send")
    public String send(@RequestParam Long toUserId, @RequestParam String content, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        Message message = new Message();
        message.setFromUserId(user.getId());
        message.setToUserId(toUserId);
        message.setContent(content);
        messageService.send(message);
        return "redirect:/messages/chat/" + toUserId;
    }
}
