package by.bntu.fitr.poisit.lytkina.technosila.service;

import by.bntu.fitr.poisit.lytkina.technosila.domain.Product;
import by.bntu.fitr.poisit.lytkina.technosila.domain.User;
import by.bntu.fitr.poisit.lytkina.technosila.repos.ProductRepo;
import by.bntu.fitr.poisit.lytkina.technosila.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ProductRepo productRepo;

    public Double totalPrice(User user){
        Double totalPrice = 0.0;
        for (Product product : user.getProducts()){
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }
}
