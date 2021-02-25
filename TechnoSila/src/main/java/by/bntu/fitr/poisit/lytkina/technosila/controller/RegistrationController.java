package by.bntu.fitr.poisit.lytkina.technosila.controller;

import by.bntu.fitr.poisit.lytkina.technosila.beans.Role;
import by.bntu.fitr.poisit.lytkina.technosila.beans.User;
import by.bntu.fitr.poisit.lytkina.technosila.repos.UserRepo;
import by.bntu.fitr.poisit.lytkina.technosila.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }


    @PostMapping("/registration")
    public String addUser(@Valid User user, BindingResult bindingResult, Map<String, Object> model){
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null){
            model.put("message", "Пользователь с таким именем уже есть в системе");
            return "registration";
        }
        if (user.getPassword() != null && !user.getPassword().equals(user.getConfirmPassword()) ){
            model.put("errorConfirmPassword", "Пароли не совпадают!");
            return "registration";
        }
        if (bindingResult.hasErrors()) {
            if (bindingResult.hasFieldErrors("username"))
            {
                model.put("errorLogin", "Логин должен содержать от 2 до 20 символов");
            }
            if (bindingResult.hasFieldErrors("password")){
                model.put("errorPassword", "Пароль должен содержать от 8 симвлов");
            }
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        return "redirect:/login";
    }
}
