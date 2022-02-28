package app.controller;

import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.starter.HelloWorld;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    HelloWorld helloWorld;

    @GetMapping()
    public String userListPage(Model model) {
        model.addAttribute("users", userService.getUsersList());
        return "index";
    }

    @GetMapping("/newUser")
    public String showCreateUserPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("create", true);
        return "editOrCreateUser";
    }

    @PostMapping("/newUser/create")
    public String createUserPage(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/users/";
    }

    @GetMapping("/{userId}/edit")
    public String showEditUserPage(Model model, @PathVariable long userId) {
        User user = userService.getUser(userId);
        model.addAttribute("user", user);
        model.addAttribute("create", false);
        return "editOrCreateUser";
    }

    @PostMapping("/{userId}/edit/submit")
    public String editUserPage(@ModelAttribute("user") User user, @PathVariable long userId) {
        user.setId(userId);
        userService.changeUser(user);
        return "redirect:/users/";
    }

    @GetMapping("/{userId}/delete")
    public String deleteUserPage(@PathVariable long userId) {
        userService.removeUser(userId);
        return "redirect:/users/";
    }
}
