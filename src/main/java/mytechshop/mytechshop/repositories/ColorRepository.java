package mytechshop.mytechshop.repositories;

import mytechshop.mytechshop.models.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
    // Custom query to find a color by name (optional)
    Color findByName(String name);
}