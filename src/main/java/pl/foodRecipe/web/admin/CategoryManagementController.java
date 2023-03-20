package pl.foodRecipe.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.foodRecipe.domain.category.CategoryService;
import pl.foodRecipe.domain.category.dto.CategoryDto;

@Controller
public class CategoryManagementController {
    private final CategoryService categoryService;

    public CategoryManagementController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/admin/dodaj-kategorie")
    public String addCategoryForm(Model model) {
        CategoryDto category = new CategoryDto();
        model.addAttribute("category", category);
        return "admin/category-form";
    }

    @PostMapping("/admin/dodaj-kategorie")
    public String addCategory(CategoryDto category, RedirectAttributes redirectAttributes) {
        categoryService.addCategory(category);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Gatunek %s został zapisany".formatted(category.getName()));
        return "redirect:/admin";
    }


}
