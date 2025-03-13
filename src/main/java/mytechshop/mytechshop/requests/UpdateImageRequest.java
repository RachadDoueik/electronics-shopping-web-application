package mytechshop.mytechshop.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class UpdateImageRequest {

    @NotBlank(message = "Image URL is required")
    private String imageUrl; // URL or file path of the image

    @NotNull(message = "Product ID is required")
    private Long productId; // ID of the product this image belongs to

    private List<MultipartFile> images;

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

    public List<MultipartFile> getImages() {
        return images;
    }

    public void setImages(List<MultipartFile> images){
        this.images = images;
    }
}