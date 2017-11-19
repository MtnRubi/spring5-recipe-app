package net.unixplanet.spring5recipeapp.repositories;

import net.unixplanet.spring5recipeapp.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
}
