package app.controller;

import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/")
    public String userListPage(Model model) {
        model.addAttribute("users", userService.getUsersList());
        return "index";
    }

    @GetMapping(value = "/new_user")
    public String showCreateUserPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "editOrCreateUser";
    }

    @PostMapping(value = "/new_user")
    public String createUserPage(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/users/";
    }

    @GetMapping(value = "/edit/{userId}")
    public String showEditUserPage(Model model, @PathVariable long userId) {
        User user = userService.getUser(userId);
        model.addAttribute("user", user);
        return "editOrCreateUser";
    }

    @PostMapping(value = "/edit/{userId}")
    public String editUserPage(@ModelAttribute("user") User user, @PathVariable long userId) {
        user.setId(userId);
        userService.changeUser(user);
        return "redirect:/users/";
    }

    @GetMapping(value = "/delete/{userId}")
    public String deleteUserPage(@PathVariable long userId) {
        userService.removeUser(userId);
        return "redirect:/users/";
    }
}
