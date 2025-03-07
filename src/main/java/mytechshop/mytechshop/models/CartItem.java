package mytechshop.mytechshop.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many CartItems belong to one Cart
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    // Many CartItems refer to one Product
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // The quantity of the product in the cart
    @Column(nullable = false)
    private Integer quantity;

    // You can add additional fields like total price for this CartItem if needed.
}
