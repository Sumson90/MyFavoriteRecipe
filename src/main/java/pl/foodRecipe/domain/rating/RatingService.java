package pl.foodRecipe.domain.rating;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.foodRecipe.domain.recipe.Recipe;
import pl.foodRecipe.domain.recipe.RecipeRepository;
import pl.foodRecipe.domain.user.User;
import pl.foodRecipe.domain.user.UserRepository;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    public void addOrUpdateRating(String userEmail, long recipeId, int rating) {
        Rating ratingToSaveOrUpdate = ratingRepository.findByUser_EmailAndRecipe_Id(userEmail, recipeId)
                .orElseGet(Rating::new);
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow();
        ratingToSaveOrUpdate.setUser(user);
        ratingToSaveOrUpdate.setRecipe(recipe);
        ratingToSaveOrUpdate.setRating(rating);
        ratingRepository.save(ratingToSaveOrUpdate);
    }

    public Optional<Integer> getUserRatingForRecipe(String userEmail, long recipeId) {
        return ratingRepository.findByUser_EmailAndRecipe_Id(userEmail, recipeId)
                .map(Rating::getRating);
    }
}