package shop.uz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.uz.dto.ProductDTO;
import shop.uz.entity.ProductEntity;
import shop.uz.enums.ProductStatus;
import shop.uz.repository.ProductRepository;

import java.time.LocalDateTime;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductAttachService productAttachService;


    public ProductDTO create(ProductDTO productDTO) {
        ProductEntity entity = new ProductEntity();
        entity.setName(productDTO.getName());
        entity.setPrice(productDTO.getPrice());
        entity.setDescription(productDTO.getDescription());
        entity.setVisible(Boolean.TRUE);
        entity.setProductStatus(ProductStatus.AVAILABLE);
        entity.setCreatedDate(LocalDateTime.now());
        productRepository.save(entity);
        productCategoryService.merge(entity.getId(), productDTO.getCategories());
        productAttachService.merge(entity.getId(), productDTO.getPhotos());

        return toDTO(entity);
    }

    private ProductDTO toDTO(ProductEntity entity) {
        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setDescription(entity.getDescription());
        dto.setVisible(entity.getVisible());
        dto.setProductStatus(entity.getProductStatus());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCategories(productCategoryService.getCategories(entity.getId()));
        dto.setPhotosDTO(productAttachService.getPhotos(entity.getId()));
        return dto;

    }
}
