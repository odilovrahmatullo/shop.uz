package shop.uz.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import shop.uz.entity.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<CategoryEntity,Integer> {

    @Query("from CategoryEntity where nameUz = ?1 and nameRu = ?2 and nameEn = ?3 and visible = true")
    Optional<CategoryEntity> isExist(String nameUz, String nameRu,String nameEn);

    @Query("from CategoryEntity where id = ?1 or parentId = ?1 and visible = true")
    List<CategoryEntity> findAllByVisibleTrue(Integer id);

    @Transactional
    @Modifying
    @Query("update CategoryEntity set visible = false where id = ?1")
    void deleteCategory(Integer id);
}
