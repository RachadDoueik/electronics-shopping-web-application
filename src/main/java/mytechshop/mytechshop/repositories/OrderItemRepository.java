package mytechshop.mytechshop.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mytechshop.mytechshop.models.*;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder(Order order);
    Optional<OrderItem> findByOrderAndProduct(Order order, Product product);
    void deleteByOrder(Order order);
}
