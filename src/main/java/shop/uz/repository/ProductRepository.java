package shop.uz.repository;

import org.springframework.data.repository.CrudRepository;
import shop.uz.entity.ProductEntity;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
}
