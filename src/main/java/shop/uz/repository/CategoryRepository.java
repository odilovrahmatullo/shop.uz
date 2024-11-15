package shop.uz.repository;

import org.springframework.data.repository.CrudRepository;
import shop.uz.entity.CategoryEntity;

public interface CategoryRepository extends CrudRepository<CategoryEntity,Integer> {

}
