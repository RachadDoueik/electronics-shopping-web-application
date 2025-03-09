package mytechshop.mytechshop.requests;

import jakarta.validation.constraints.NotNull;
import mytechshop.mytechshop.models.User;

public class CreateCartRequest {

    @NotNull(message = "User is required")
    private User user; // User associated with the cart

    // Getters and Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}