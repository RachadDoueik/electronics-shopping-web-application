package mytechshop.mytechshop.requests;

import jakarta.validation.constraints.*;
import lombok.Data;
import mytechshop.mytechshop.models.Category;
import mytechshop.mytechshop.models.Brand;
import java.util.List;

@Data
public class UpdateProductRequest {

    @NotBlank(message = "Product name is required")
    @Size(max = 100, message = "Product name must be less than 100 characters")
    private String name;

    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be a positive number")
    private Double price;

    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock must be a non-negative number")
    private Integer stock;

    @NotNull(message = "Category is required")
    private Category category; // Category object

    @NotNull(message = "Brand is required")
    private Brand brand; // Brand object

    private List<String> imageUrls; // List of image URLs
}