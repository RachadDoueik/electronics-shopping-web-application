package mytechshop.mytechshop.requests;

import jakarta.validation.constraints.NotBlank;

public class UpdateBrandRequest {

    @NotBlank(message = "Brand name is required")
    private String name; // Name of the brand

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}