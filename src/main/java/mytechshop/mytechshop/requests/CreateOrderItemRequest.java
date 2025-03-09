package mytechshop.mytechshop.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import mytechshop.mytechshop.models.Order;
import mytechshop.mytechshop.models.Product;

public class CreateOrderItemRequest {

    @NotNull(message = "Order is required")
    private Order order; // Order associated with the order item

    @NotNull(message = "Product is required")
    private Product product; // Product associated with the order item

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be a positive number")
    private Integer quantity; // Quantity of the product in the order

    @NotNull(message = "Price at purchase is required")
    @Positive(message = "Price at purchase must be a positive number")
    private Double priceAtPurchase; // Price of the product at the time of purchase

    // Getters and Setters
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

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