package shop.uz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.uz.dto.AttachDTO;
import shop.uz.entity.ProductAttachEntity;
import shop.uz.entity.ProductCategoryEntity;
import shop.uz.repository.ProductAttachRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductAttachService {
    @Autowired
    private ProductAttachRepository productAttachRepository;
    @Autowired
    private AttachService attachService;


    public void merge(Integer productId, List<String> newIdList) {
        List<String> oldIdList = productAttachRepository.findAllByProductId(productId);

        if (oldIdList.isEmpty()) {
            oldIdList = new ArrayList<>();
        }
        for (String attachId : oldIdList) {
            if (!newIdList.contains(attachId)) {
                // delete operation {attachId}
                productAttachRepository.deleteProductAndCategory(productId, attachId);
            }
        }


        for (String newItemId : newIdList) {
            if (!oldIdList.contains(newItemId)) {
                // save
                ProductAttachEntity entity = new ProductAttachEntity();
                entity.setProductId(productId);
                entity.setAttachId(newItemId);
                entity.setCreatedDate(LocalDateTime.now());
                productAttachRepository.save(entity);
            }
        }
    }

    public List<AttachDTO> getPhotos(Integer id) {
        List<String> photos = productAttachRepository.findAllByProductId(id);
        List<AttachDTO> attachDTOList = new ArrayList<>();
        for (String attachId : photos) {
            attachDTOList.add(attachService.getDto(attachId));
        }
        return attachDTOList;
    }
}
