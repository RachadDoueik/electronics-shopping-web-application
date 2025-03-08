package mytechshop.mytechshop.services;

import mytechshop.mytechshop.interfaces.ICategoryService;
import mytechshop.mytechshop.models.Category;
import mytechshop.mytechshop.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {


    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
         this.categoryRepository = categoryRepository;
    }

    @Override
    public Category saveCategory(Category category) {
        // Check if a category with the same name already exists
        if (categoryRepository.existsByName(category.getName())) {
            throw new RuntimeException("Category with the same name already exists");
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        // Find the existing category
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        // Update the category fields
        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());
        return categoryRepository.save(existingCategory);
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}