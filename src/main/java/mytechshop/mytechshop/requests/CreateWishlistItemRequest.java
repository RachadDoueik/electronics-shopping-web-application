package mytechshop.mytechshop.requests;

import jakarta.validation.constraints.NotNull;
import mytechshop.mytechshop.models.Wishlist;
import mytechshop.mytechshop.models.Product;

public class CreateWishlistItemRequest {

    @NotNull(message = "Wishlist is required")
    private Wishlist wishlist; // Wishlist associated with the wishlist item

    @NotNull(message = "Product is required")
    private Product product; // Product associated with the wishlist item

    // Getters and Setters
    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}