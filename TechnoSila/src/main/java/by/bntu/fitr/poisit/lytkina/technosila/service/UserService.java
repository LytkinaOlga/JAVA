package by.bntu.fitr.poisit.lytkina.technosila.service;

import by.bntu.fitr.poisit.lytkina.technosila.beans.Product;
import by.bntu.fitr.poisit.lytkina.technosila.beans.Role;
import by.bntu.fitr.poisit.lytkina.technosila.beans.User;
import by.bntu.fitr.poisit.lytkina.technosila.repos.ProductRepo;
import by.bntu.fitr.poisit.lytkina.technosila.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
    public void addUser(User user){
        userRepo.save(user);
    }

    public void saveUser(User user, String username, Map<String, String> form){
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key: form.keySet()){
            if (roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepo.save(user);
    }

    public void addToChart(User currentUser, Product product) {
        product.getUsers().add(currentUser);
        productRepo.save(product);
        currentUser.getProducts().add(product);
        userRepo.save(currentUser);
    }
    public void removeFromCart(User currentUser, Product product) {
        product.getUsers().remove(currentUser);
        productRepo.save(product);
        currentUser.getProducts().remove(product);
        userRepo.save(currentUser);
    }
}
