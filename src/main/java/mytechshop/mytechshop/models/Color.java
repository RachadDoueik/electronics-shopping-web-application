package mytechshop.mytechshop.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "colors")
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false , unique = true)
    private String hexCode; // Additional metadata (e.g., #FF0000 for red)

    @OneToMany(mappedBy = "color")
    private Set<Product> products;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHexCode() {
        return hexCode;
    }

    public void setHexCode(String hexCode) {
        this.hexCode = hexCode;
    }

    public Set<Product> getProducts(){
        return products;
    }

    public void setProducts(Set<Product> products){
          this.products = products;
    }


}