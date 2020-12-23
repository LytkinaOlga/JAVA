package by.bntu.fitr.poisit.lytkina.technosila.repos;

import by.bntu.fitr.poisit.lytkina.technosila.beans.Order;
import by.bntu.fitr.poisit.lytkina.technosila.beans.Product;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<Order, Long> {
}
