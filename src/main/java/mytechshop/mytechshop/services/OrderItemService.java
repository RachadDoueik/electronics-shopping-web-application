package mytechshop.mytechshop.services;

import mytechshop.mytechshop.interfaces.IOrderItemService;
import mytechshop.mytechshop.models.Order;
import mytechshop.mytechshop.models.OrderItem;
import mytechshop.mytechshop.models.Product;
import mytechshop.mytechshop.repositories.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService implements IOrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository){
           this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderItem createOrderItem(Order order, Product product, int quantity) {
        // Check if the product already exists in the order
        Optional<OrderItem> existingOrderItem = orderItemRepository.findByOrderAndProduct(order, product);
        if (existingOrderItem.isPresent()) {
            // If the product already exists, update the quantity
            OrderItem orderItem = existingOrderItem.get();
            orderItem.setQuantity(orderItem.getQuantity() + quantity);
            return orderItemRepository.save(orderItem);
        } else {
            // If the product does not exist, create a new order item
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(quantity);
            orderItem.setPriceAtPurchase(product.getPrice()); // Assuming the product has a price field
            return orderItemRepository.save(orderItem);
        }
    }

    @Override
    public Optional<OrderItem> getOrderItemById(Long id) {
        return orderItemRepository.findById(id);
    }

    @Override
    public List<OrderItem> getOrderItemsByOrder(Order order) {
        return orderItemRepository.findByOrder(order);
    }

    @Override
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }
}