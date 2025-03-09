package mytechshop.mytechshop.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import mytechshop.mytechshop.models.Cart;
import mytechshop.mytechshop.models.Product;

public class CreateCartItemRequest {

    @NotNull(message = "Cart is required")
    private Cart cart; // Cart associated with the cart item

    @NotNull(message = "Product is required")
    private Product product; // Product associated with the cart item

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be a positive number")
    private Integer quantity; // Quantity of the product in the cart

    // Getters and Setters
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
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
}