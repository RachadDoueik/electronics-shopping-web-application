package mytechshop.mytechshop.interfaces;

import java.util.List;
import java.util.Optional;
import mytechshop.mytechshop.models.*;

public interface ICategoryService {
    Category saveCategory(Category category);
    Category updateCategory(Long id, Category category);
    Optional<Category> getCategoryById(Long id);
    List<Category> getAllCategories();
    void deleteCategory(Long id);
}

