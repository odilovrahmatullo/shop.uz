package shop.uz.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import shop.uz.entity.ProductEntity;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    @Query("update ProductEntity SET visible = false where id = ?1")
    void deleteProduct(Integer productId);
}
