package app.controller;

import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/")
    public String userListPage(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "index";
    }

    @GetMapping(value = "/add")
    public String showCreateUserPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "editOrCreateUser";
    }

    @PostMapping(value = "/add")
    public String createUserPage(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/";
    }

    @GetMapping(value = "/edit")
    public String showEditUserPage(Model model, @RequestParam(value = "userId") long userId) {
        User user = userService.getUser(userId);
        model.addAttribute("user", user);
        return "editOrCreateUser";
    }

    @PostMapping(value = "/edit")
    public String editUserPage(@ModelAttribute("user") User user, @RequestParam(value = "userId") long userId) {
        user.setId(userId);
        userService.changeUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "/delete")
    public String deleteUserPage(@RequestParam(value = "userId") long userId) {
        userService.removeUser(userId);
        return "redirect:/";
    }
}
