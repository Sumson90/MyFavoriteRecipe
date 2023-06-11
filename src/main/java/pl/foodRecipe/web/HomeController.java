package pl.foodRecipe.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.foodRecipe.domain.recipe.RecipeService;
import pl.foodRecipe.domain.recipe.dto.RecipeDto;

import java.util.List;
@AllArgsConstructor
@Controller
public class HomeController {
    private final RecipeService recipeService;

    @GetMapping("/")
    public String home(Model model){
        List<RecipeDto> promotedRecipes = recipeService.findAllPromotedRecipes();
        model.addAttribute("heading", "Promowane przepisy");
        model.addAttribute("description", "Przepisy polecane przez nasz zespół");
        model.addAttribute("recipes" ,promotedRecipes);
        return "recipe-listing";
    }
}
