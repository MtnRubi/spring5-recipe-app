package net.unixplanet.spring5recipeapp.bootstrap;

import net.unixplanet.spring5recipeapp.domain.*;
import net.unixplanet.spring5recipeapp.repositories.CategoryRepository;
import net.unixplanet.spring5recipeapp.repositories.RecipeRepository;
import net.unixplanet.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);

        //Get UOM
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
        if (!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
        if (!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
        if (!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");
        if (!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        //get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupsUom = cupUomOptional.get();

        //get Categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if (!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("ExpectedCategory Not Found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if (!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("ExpectedCategory Not Found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        //Yummy Guac
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setCookTime(0);
        guacRecipe.setPrepTime(10);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1: Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh into a bowl." +
                "\n" +
                "2: Mash the avocado with a fork, (Don't overdo it, the guacamole should be chunky)" +
                "\n" +
                "3: Add salt, lime juice, and the rest. Add the chopped onion, black pepper, and chilies. Remember, this is done to taste, so taste often." +
                "\n" +
                "4: Cover with plastic wrap and chill to store");

        Notes guacNote = new Notes();
        guacNote.setRecipeNotes("For a super quick guacamole just take 1/4 cup of your favorite salsa and still into your mashed avocados");

        guacNote.setRecipe(guacRecipe);
        guacRecipe.setNotes(guacNote);


        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Kosher Salt", new BigDecimal(5), teaSpoonUom));
        guacRecipe.addIngredient(new Ingredient("juice of fresh limes", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("minced red onion", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("serrano chiles, chopped and seeded", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), teaSpoonUom));
        guacRecipe.addIngredient(new Ingredient("ripe tomato, chopped and seeded", new BigDecimal(0.5), eachUom));
        guacRecipe.addIngredient(new Ingredient("Cilantro, (NO, into the trash it goes!)", new BigDecimal(2), eachUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        recipes.add(guacRecipe);


        return recipes;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }
}
