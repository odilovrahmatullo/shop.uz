package shop.uz.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import shop.uz.entity.ProductAttachEntity;

import java.util.List;

public interface ProductAttachRepository extends CrudRepository<ProductAttachEntity, Integer> {
    @Query("select attachId from  ProductAttachEntity where productId = ?1")
    List<String> findAllByProductId(Integer productId);

    @Query("DELETE FROM ProductAttachEntity where productId = ?1 and attachId = ?2")
    void deleteProductAndCategory(Integer productId, String attachId);
}
