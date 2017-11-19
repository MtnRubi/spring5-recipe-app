package net.unixplanet.spring5recipeapp.repositories;

import net.unixplanet.spring5recipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
