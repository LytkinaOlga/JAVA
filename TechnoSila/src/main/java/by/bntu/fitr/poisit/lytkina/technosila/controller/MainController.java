package by.bntu.fitr.poisit.lytkina.technosila.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @GetMapping("/")
    public String greeting(Map<String, Object> model){
        return "greeting";
    }



}
