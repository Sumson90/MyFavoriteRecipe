package pl.foodRecipe.domain.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.foodRecipe.domain.category.dto.CategoryDto;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<CategoryDto> findCategoryByName(String name){
        return categoryRepository.findByNameIgnoreCase(name)
                .map(CategoryDtoMapper::map);
    }

    public List<CategoryDto> findAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryDtoMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional
    public void addCategory(CategoryDto category) {
        Category categoryToSave = new Category();
        categoryToSave.setName(category.getName());
        categoryToSave.setDescription(category.getDescription());
        categoryRepository.save(categoryToSave);
    }
}

