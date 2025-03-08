package mytechshop.mytechshop.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class UpdateOrderItemRequest {

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be a positive number")
    private Integer quantity; // Updated quantity of the product in the order

    @NotNull(message = "Price at purchase is required")
    @Positive(message = "Price at purchase must be a positive number")
    private Double priceAtPurchase; // Updated price of the product at the time of purchase

    // Getters and Setters
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public void setPriceAtPurchase(Double priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }
}