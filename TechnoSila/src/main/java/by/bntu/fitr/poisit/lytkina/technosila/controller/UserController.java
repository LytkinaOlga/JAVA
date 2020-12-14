package by.bntu.fitr.poisit.lytkina.technosila.controller;

import by.bntu.fitr.poisit.lytkina.technosila.domain.Product;
import by.bntu.fitr.poisit.lytkina.technosila.domain.Role;
import by.bntu.fitr.poisit.lytkina.technosila.domain.User;
import by.bntu.fitr.poisit.lytkina.technosila.repos.UserRepo;
import by.bntu.fitr.poisit.lytkina.technosila.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user)

    {
        userService.saveUser(user, username, form);
        return "redirect:/user";
    }
}
