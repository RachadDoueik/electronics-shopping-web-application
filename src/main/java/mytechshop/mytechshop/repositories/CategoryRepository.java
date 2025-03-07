package mytechshop.mytechshop.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mytechshop.mytechshop.models.*;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Custom query method to find categories by name
    Category findByName(String name);
}