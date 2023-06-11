package pl.foodRecipe.domain.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RecipeDto {
    private Long id;
    private String title;
    private String shortDescription;
    private String description;
    private String youtubeTrailerId;
    private String category;
    private boolean promoted;
    private String poster;
    private double avgRating;
    private int ratingCount;
}
