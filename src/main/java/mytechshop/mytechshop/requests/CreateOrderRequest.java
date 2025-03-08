package mytechshop.mytechshop.requests;

import jakarta.validation.constraints.NotNull;
import mytechshop.mytechshop.models.User;
import mytechshop.mytechshop.models.OrderItem;

import java.util.List;

public class CreateOrderRequest {

    @NotNull(message = "User is required")
    private User user; // User associated with the order

    @NotNull(message = "Order items are required")
    private List<OrderItem> orderItems; // List of items in the order

    @NotNull(message = "Total price is required")
    private Double totalPrice; // Total price of the order

    // Getters and Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}