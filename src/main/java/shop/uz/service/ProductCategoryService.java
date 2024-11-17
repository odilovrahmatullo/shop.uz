package shop.uz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import shop.uz.entity.ProductCategoryEntity;
import shop.uz.repository.ProductCategoryRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public void merge(Integer productId, List<Integer> newIdList) {
        List<Integer> oldIdList = productCategoryRepository.findAllByProductId(productId);

            if (oldIdList.isEmpty()) {
                oldIdList = new ArrayList<>();
            }
            for (Integer categoryId : oldIdList) {
                if (!newIdList.contains(categoryId)) {
                    // delete operation {categoryId}
                    productCategoryRepository.deleteProductAndCategory(productId, categoryId);
                }
            }



        for (Integer newItemId : newIdList) {
            if (!oldIdList.contains(newItemId)) {
                // save
                ProductCategoryEntity entity = new ProductCategoryEntity();
                entity.setProductId(productId);
                entity.setCategoryId(newItemId);
                entity.setCreatedDate(LocalDateTime.now());
                productCategoryRepository.save(entity);
            }
        }
    }

    public List<Integer> getCategories(Integer id) {
        return productCategoryRepository.findAllByProductId(id);
    }
}
