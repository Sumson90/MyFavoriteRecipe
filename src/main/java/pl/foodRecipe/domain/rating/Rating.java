package pl.foodRecipe.domain.rating;

import lombok.Getter;
import lombok.Setter;
import pl.foodRecipe.domain.recipe.Recipe;
import pl.foodRecipe.domain.user.User;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "recipe_rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
    private Integer rating;
}
