package by.bntu.fitr.poisit.lytkina.technosila.controller;

import by.bntu.fitr.poisit.lytkina.technosila.beans.Product;
import by.bntu.fitr.poisit.lytkina.technosila.beans.User;
import by.bntu.fitr.poisit.lytkina.technosila.repos.ProductRepo;
import by.bntu.fitr.poisit.lytkina.technosila.repos.UserRepo;
import by.bntu.fitr.poisit.lytkina.technosila.service.ProductService;
import by.bntu.fitr.poisit.lytkina.technosila.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public  class CartController{
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/product/{id}/order")
    public String addToChart(@AuthenticationPrincipal UserDetails userDetails,
                             @PathVariable(value = "id") long id,
                             Map<String, Object> model){

        String currentUserName = userDetails.getUsername();
        User currentUser = userRepo.findByUsername(currentUserName);

        Product product = productRepo.findById(id).orElseThrow();


        model.put("products", product);
        model.put("user", currentUser);
        userService.addToChart(currentUser, product);
        model.put("users", currentUser.getProducts());
        model.put("productsCount", currentUser.getProducts().size());

        Double totalPrice = 0.0;
        totalPrice = productService.totalPrice(currentUser);
        model.put("totalPrice", totalPrice);
        return "cart";

    }
    @GetMapping("/cart")
    public String cart(@AuthenticationPrincipal UserDetails userDetails, Map<String, Object> model) {
        String currentUserName = userDetails.getUsername();
        User currentUser = userRepo.findByUsername(currentUserName);

        Double totalPrice = productService.totalPrice(currentUser);

        model.put("products", currentUser.getProducts());
        model.put("user", currentUser);
        model.put("users", currentUser.getProducts());
        model.put("productsCount", currentUser.getProducts().size());
        model.put("totalPrice", totalPrice);
        return "cart";
    }
    @PostMapping("/product/{id}/order/remove")
    public String removeFromCart(@PathVariable(value = "id") long id, Map<String, Object> model, @AuthenticationPrincipal UserDetails userDetails){

        String currentUserName = userDetails.getUsername();
        User currentUser = userRepo.findByUsername(currentUserName);
        Product product = productRepo.findById(id).orElseThrow();
        model.put("products", product);
        model.put("user", currentUser);
        userService.removeFromCart(currentUser, product);
        model.put("users", currentUser.getProducts());
        model.put("productsCount", currentUser.getProducts().size());

        Double totalPrice = 0.0;
        totalPrice = productService.totalPrice(currentUser);
        model.put("totalPrice", totalPrice);
        return "cart";

    }

}