package mytechshop.mytechshop.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imageUrl; // URL or file path of the image

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; // Many images belong to one product
}