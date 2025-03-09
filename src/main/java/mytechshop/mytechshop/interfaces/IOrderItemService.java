package mytechshop.mytechshop.interfaces;

import java.util.List;
import java.util.Optional;
import mytechshop.mytechshop.models.*;

public interface IOrderItemService {
    OrderItem createOrderItem(Order order, Product product, int quantity);
    Optional<OrderItem> getOrderItemById(Long id);
    List<OrderItem> getOrderItemsByOrder(Order order);
    void deleteOrderItem(Long id);
}
