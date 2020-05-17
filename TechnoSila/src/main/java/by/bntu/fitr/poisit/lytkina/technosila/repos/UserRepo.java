package by.bntu.fitr.poisit.lytkina.technosila.repos;

import by.bntu.fitr.poisit.lytkina.technosila.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository <User, Long> {
    User findByUsername(String username);
}
