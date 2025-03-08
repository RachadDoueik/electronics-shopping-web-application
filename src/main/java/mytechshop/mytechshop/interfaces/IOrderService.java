package mytechshop.mytechshop.interfaces;

import mytechshop.mytechshop.models.Order;
import mytechshop.mytechshop.models.User;
import mytechshop.mytechshop.enums.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    Order placeOrder(Order order); // Takes an Order object instead of User and OrderItems
    Optional<Order> getOrderById(Long id);
    List<Order> getOrdersByUser(User user);
    List<Order> getOrdersByStatus(OrderStatus status);
    void updateOrderStatus(Long id, OrderStatus status);
    void deleteOrder(Long id);
}