package pl.foodRecipe.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import pl.foodRecipe.recipe.RecipeService;
import pl.foodRecipe.recipe.dto.RecipeDto;

import java.util.Optional;

@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping("/przepis/{id}")
    public String getMovie(@PathVariable long id, Model model) {
        RecipeDto recipe = recipeService.findRecipeById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("movie", recipe);
        return "recipe";
    }

}
