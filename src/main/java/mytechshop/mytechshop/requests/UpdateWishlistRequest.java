package mytechshop.mytechshop.requests;

import jakarta.validation.constraints.NotNull;
import mytechshop.mytechshop.models.User;

public class UpdateWishlistRequest {

    @NotNull(message = "User is required")
    private User user; // User associated with the wishlist

    // Getters and Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}