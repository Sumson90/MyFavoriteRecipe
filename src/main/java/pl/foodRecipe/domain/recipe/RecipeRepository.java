package pl.foodRecipe.domain.recipe;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findAllByPromotedIsTrue();
    List<Recipe> findAllByCategory_NameIgnoreCase(String category);
    @Query("select m from Recipe m join m.ratings r group by m order by avg(r.rating) desc")
    List<Recipe> findTopByRating(Pageable page);
}
