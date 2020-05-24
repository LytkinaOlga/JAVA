package by.bntu.fitr.poisit.lytkina.technosila.controller;

import by.bntu.fitr.poisit.lytkina.technosila.domain.Product;
import by.bntu.fitr.poisit.lytkina.technosila.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

@Controller
public class ProductController {
    @Autowired
    private ProductRepo productRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/product")
    public String product (Map<String, Object> model){
        Iterable<Product> products = productRepo.findAll();
        model.put("products", products);
        return "product";
    }
    @GetMapping("/product/add")
    public String productAdd (Map<String, Object> model){
        return "productAdd";
    }
    @PostMapping("/product/add")
    public String add(@RequestParam String name,
                      @RequestParam Double price,
                      @RequestParam String description,
                      @RequestParam("file") MultipartFile file,
                      Map<String, Object> model
                      ) throws IOException {
        Product product = new Product(name, price, description);
        if(file !=  null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));
            product.setFilename(resultFileName);
        }
        productRepo.save(product);
        return "redirect:/product";
    }
    @GetMapping("/product/{id}")
    public String productDetails (@PathVariable(value = "id") long id, Map<String, Object> model){
        if (!productRepo.existsById(id)){
            return "redirect:/product";
        }
        Iterable<Product> products = productRepo.findAllById(Collections.singleton(id));
        model.put("products", products);
        return "productDetails";
    }

    @GetMapping("/product/{id}/edit")
    public String productEdit (@PathVariable(value = "id") long id, Map<String, Object> model){
        if (!productRepo.existsById(id)){
            return "redirect:/product";
        }
        Iterable<Product> products = productRepo.findAllById(Collections.singleton(id));
        model.put("products", products);
        return "productEdit";
    }
    @PostMapping("/product/{id}/edit")
    public String productEdit(@PathVariable(value = "id") long id, @RequestParam String name,
                      @RequestParam Double price,
                      @RequestParam String description,
                      @RequestParam("file") MultipartFile file,
                      Map<String, Object> model
    ) throws IOException {
        Iterable<Product> products = productRepo.findAllById(Collections.singleton(id));
        model.put("products", products);

        Product product = productRepo.findById(id).orElseThrow();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        if(file !=  null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));
            product.setFilename(resultFileName);
        }
        productRepo.save(product);
        return "redirect:/product";
    }


    @PostMapping("/product/{id}/remove")
    public String productRemove(@PathVariable(value = "id") long id,Map<String, Object> model) {
        Product product = productRepo.findById(id).orElseThrow();
        productRepo.delete(product);
        return "redirect:/product";
    }
}
