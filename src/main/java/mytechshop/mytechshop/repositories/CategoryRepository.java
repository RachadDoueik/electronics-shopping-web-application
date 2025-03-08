package mytechshop.mytechshop.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mytechshop.mytechshop.models.*;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
    Optional<Category> findById(Long categoryId);
    Boolean existsByName(String name);
    void deleteByName(String name);
}