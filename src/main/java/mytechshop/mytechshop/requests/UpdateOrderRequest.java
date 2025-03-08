package mytechshop.mytechshop.requests;

import jakarta.validation.constraints.NotNull;
import mytechshop.mytechshop.enums.OrderStatus;

public class UpdateOrderRequest {

    @NotNull(message = "Order status is required")
    private OrderStatus status; // Status of the order

    // Getters and Setters
    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}