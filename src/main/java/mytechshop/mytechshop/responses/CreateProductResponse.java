package mytechshop.mytechshop.responses;

import mytechshop.mytechshop.models.Product;

import java.util.List;

public class CreateProductResponse {

    private Long id;

    private String name;

    public CreateProductResponse(Product product){
        setId(product.getId());
        setName(product.getName());
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

}
