package net.unixplanet.spring5recipeapp.repositories;

import net.unixplanet.spring5recipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
