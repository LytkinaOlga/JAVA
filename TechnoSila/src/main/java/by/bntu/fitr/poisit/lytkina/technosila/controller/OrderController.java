package by.bntu.fitr.poisit.lytkina.technosila.controller;

import by.bntu.fitr.poisit.lytkina.technosila.beans.Order;
import by.bntu.fitr.poisit.lytkina.technosila.beans.Product;
import by.bntu.fitr.poisit.lytkina.technosila.beans.User;
import by.bntu.fitr.poisit.lytkina.technosila.repos.OrderRepo;
import by.bntu.fitr.poisit.lytkina.technosila.repos.ProductRepo;
import by.bntu.fitr.poisit.lytkina.technosila.repos.UserRepo;
import by.bntu.fitr.poisit.lytkina.technosila.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class OrderController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderRepo orderRepo;

    @GetMapping("/order")
    public String order(@AuthenticationPrincipal UserDetails userDetails, Map<String, Object> model) {
        String currentUserName = userDetails.getUsername();
        User currentUser = userRepo.findByUsername(currentUserName);

        Double totalPrice = productService.totalPrice(currentUser);

//        model.put("products", currentUser.getProducts());
//        model.put("user", currentUser);
//        model.put("users", currentUser.getProducts());
//        model.put("productsCount", currentUser.getProducts().size());
//        model.put("totalPrice", totalPrice);
        return "order";
    }

    @PostMapping("/order")
    public String add(@RequestParam String phone,
                      @RequestParam String address,
                      @AuthenticationPrincipal UserDetails userDetails,
                      Map<String, Object> model
    ) throws IOException {

        String currentUserName = userDetails.getUsername();
        User currentUser = userRepo.findByUsername(currentUserName);

        Double totalPrice = productService.totalPrice(currentUser);

        Order order = new Order(currentUser.getUsername(), totalPrice, phone, address);


        orderRepo.save(order);
        return "successOrder";
    }

    @GetMapping("/orders")
    public String orders(Map<String, Object> model) {
        Iterable<Order> orders = orderRepo.findAll();
        model.put("orders", orders);
        return "orders";
    }
}
