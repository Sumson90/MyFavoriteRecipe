package pl.foodRecipe.domain.recipe;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.foodRecipe.domain.category.Category;
import pl.foodRecipe.domain.category.CategoryRepository;
import pl.foodRecipe.domain.recipe.dto.RecipeDto;
import pl.foodRecipe.domain.recipe.dto.RecipeSaveDto;
import pl.foodRecipe.storage.FileStorageService;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final FileStorageService fileStorageService;

    public RecipeService(RecipeRepository recipeRepository,
                         CategoryRepository categoryRepository,
                         FileStorageService fileStorageService) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.fileStorageService = fileStorageService;
    }


    public List<RecipeDto> findAllPromotedRecipes() {
        return recipeRepository.findAllByPromotedIsTrue().stream()
                .map(recipe -> RecipeDtoMapper.map(recipe))
                .toList();
    }

    public Optional<RecipeDto> findRecipeById(long id) {
        return recipeRepository.findById(id).map(recipe -> RecipeDtoMapper.map(recipe));
    }

    public List<RecipeDto> findRecipesByCategoryName(String category) {
        return recipeRepository.findAllByCategory_NameIgnoreCase(category).stream()
                .map(recipe -> RecipeDtoMapper.map(recipe))
                .toList();
    }

    public void addRecipe(RecipeSaveDto recipeToSave) {
        Recipe recipe = new Recipe();
        recipe.setTitle(recipeToSave.getTitle());
        recipe.setPromoted(recipeToSave.isPromoted());
        recipe.setShortDescription(recipeToSave.getShortDescription());
        recipe.setDescription(recipeToSave.getDescription());
        recipe.setYoutubeTrailerId(recipeToSave.getYoutubeTrailerId());
        Category category = categoryRepository.findByNameIgnoreCase(recipeToSave.getCategory()).orElseThrow();
        recipe.setCategory(category);
        if (recipeToSave.getPoster() != null) {
            String savedFileName = fileStorageService.saveImage(recipeToSave.getPoster());
            recipe.setPoster(savedFileName);
        }
        recipeRepository.save(recipe);
    }
    public List<RecipeDto> findTopRecipes(int size) {
        Pageable page = Pageable.ofSize(size);
        return recipeRepository.findTopByRating(page).stream()
                .map(recipe -> RecipeDtoMapper.map(recipe))
                .toList();
    }


}

