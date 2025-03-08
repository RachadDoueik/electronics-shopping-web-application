package mytechshop.mytechshop.repositories;

import mytechshop.mytechshop.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mytechshop.mytechshop.models.*;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByOrderDateBetween(java.time.LocalDateTime start, java.time.LocalDateTime end);
    Optional<Order> findById(Long orderId);
    void deleteById(Long orderId);
    void deleteByUser(User user);
}