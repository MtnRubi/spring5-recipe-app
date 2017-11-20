package net.unixplanet.spring5recipeapp.services;

import net.unixplanet.spring5recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
}
