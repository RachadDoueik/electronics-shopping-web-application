package mytechshop.mytechshop.requests;

import jakarta.validation.constraints.NotNull;
import mytechshop.mytechshop.enums.CartStatus;
import mytechshop.mytechshop.models.User;

public class UpdateCartRequest {

    @NotNull(message = "User is required")
    private User user; // User associated with the cart

    @NotNull(message = "Cart status is required")
    private CartStatus status; // Status of the cart

    @NotNull(message = "Active cart flag is required")
    private boolean activeCart; // Whether the cart is active

    // Getters and Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CartStatus getStatus() {
        return status;
    }

    public void setStatus(CartStatus status) {
        this.status = status;
    }

    public boolean isActiveCart() {
        return activeCart;
    }

    public void setActiveCart(boolean activeCart) {
        this.activeCart = activeCart;
    }
}