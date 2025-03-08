package mytechshop.mytechshop.services;

import mytechshop.mytechshop.interfaces.IOrderService;
import mytechshop.mytechshop.models.Order;
import mytechshop.mytechshop.models.OrderItem;
import mytechshop.mytechshop.models.User;
import mytechshop.mytechshop.enums.OrderStatus;
import mytechshop.mytechshop.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderService implements IOrderService {


    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
         this.orderRepository = orderRepository;
    }

    @Override
    public Order placeOrder(Order order) {
        // Set default values for the order
        order.setStatus(OrderStatus.PENDING); // Default status
        order.setOrderDate(LocalDateTime.now()); // Set current date and time

        // Calculate total price if not already set
        if (order.getTotalPrice() == null) {
            order.setTotalPrice(calculateTotalPrice(order.getOrderItems()));
        }

        // Save the order to the database
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    @Override
    public void updateOrderStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        order.setStatus(status);
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    // Helper method to calculate total price of the order
    private Double calculateTotalPrice(Set<OrderItem> orderItems) {
        return orderItems.stream()
                .mapToDouble(item -> item.getPriceAtPurchase() * item.getQuantity())
                .sum();
    }
}