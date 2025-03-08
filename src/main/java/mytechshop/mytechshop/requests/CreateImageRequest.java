package mytechshop.mytechshop.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateImageRequest {

    @NotBlank(message = "Image URL is required")
    private String imageUrl; // URL or file path of the image

    @NotNull(message = "Product ID is required")
    private Long productId; // ID of the product this image belongs to

    // Getters and Setters
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}