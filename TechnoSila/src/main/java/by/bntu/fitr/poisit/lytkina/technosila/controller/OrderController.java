package by.bntu.fitr.poisit.lytkina.technosila.controller;

import by.bntu.fitr.poisit.lytkina.technosila.beans.*;
import by.bntu.fitr.poisit.lytkina.technosila.repos.OrderRepo;
import by.bntu.fitr.poisit.lytkina.technosila.repos.ProductRepo;
import by.bntu.fitr.poisit.lytkina.technosila.repos.UserRepo;
import by.bntu.fitr.poisit.lytkina.technosila.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
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
    public String add(@RequestParam  String phone,
                      @RequestParam  String payment,
                      @RequestParam  String delivery,
                      @RequestParam String apartment,
                      @RequestParam String building,
                      @RequestParam String corps,
                      @RequestParam String intercom,
                      @RequestParam String street,

                      @AuthenticationPrincipal UserDetails userDetails,
                      Map<String, Object> model
    ) throws IOException {

        String currentUserName = userDetails.getUsername();
        User currentUser = userRepo.findByUsername(currentUserName);

        Double totalPrice = productService.totalPrice(currentUser);
//        if (bindingResult.hasErrors()) {
//            if (bindingResult.hasFieldErrors("phone"))
//            {
//                model.put("errorInputPhone", "Поле обязательно для заполнения! Введите номер контактного телефона");
//            }
//            return "order";
//        }
        Order order = new Order();
        order.setPhone(phone);
        order.setPayment(Payment.valueOf(payment));
        order.setDelivery(Delivery.valueOf(delivery));
        order.setApartment(apartment);
        order.setBuilding(building);
        order.setCorps(corps);
        order.setIntercom(intercom);
        order.setStreet(street);
        order.setUsername(currentUserName);
        order.setPrice(totalPrice);

        if(order.getDelivery().equals("DELIVERY")){
            if (order.getStreet() == null | order.getBuilding() == null){
                model.put("errorInputStreetAndBuilding", "Поля для заполнения улицы и номера дома являются обязательными!");
                return "order";
            }
        }
        if(order.getPhone() == null){
            model.put("errorInputPhone", "Поле обязательно для заполнения! Введите номер контактного телефона");
        }

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
