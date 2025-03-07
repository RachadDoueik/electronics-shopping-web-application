package mytechshop.mytechshop.interfaces;

import java.util.List;
import java.util.Optional;

import mytechshop.mytechshop.enums.OrderStatus;
import mytechshop.mytechshop.models.*;

public interface IOrderService {
    Order placeOrder(User user, List<OrderItem> orderItems);
    Optional<Order> getOrderById(Long id);
    List<Order> getOrdersByUser(User user);
    List<Order> getOrdersByStatus(OrderStatus status);
    void updateOrderStatus(Long id, OrderStatus status);
    void deleteOrder(Long id);
}
