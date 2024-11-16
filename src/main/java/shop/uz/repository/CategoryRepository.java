package shop.uz.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import shop.uz.entity.CategoryEntity;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<CategoryEntity,Integer> {

    @Query("from CategoryEntity where nameUz = ?1 and nameRu = ?2 and nameEn = ?3 and visible = true")
    Optional<CategoryEntity> isExist(String nameUz, String nameRu,String nameEn);
}
