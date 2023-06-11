package pl.foodRecipe.domain.recipe.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
public class RecipeSaveDto {
    private Long id;
    private String title;
    private String shortDescription;
    private String description;
    private String youtubeTrailerId;

    private String category;
    private boolean promoted;
    private MultipartFile poster;
}
