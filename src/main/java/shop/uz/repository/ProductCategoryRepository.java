package shop.uz.repository;

import jakarta.persistence.criteria.From;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import shop.uz.entity.ProductCategoryEntity;

import java.util.List;

public interface ProductCategoryRepository extends CrudRepository<ProductCategoryEntity, Integer> {

    @Query("select categoryId FROM ProductCategoryEntity where productId = ?1 ")
    List<Integer> findAllByProductId(Integer productId);

    @Query("DELETE FROM ProductCategoryEntity where productId = ?1 and categoryId = ?2")
    void deleteProductAndCategory(Integer productId, Integer categoryId);
}
