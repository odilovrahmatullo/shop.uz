package shop.uz.service;

import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import shop.uz.dto.CategoryDTO;
import shop.uz.entity.CategoryEntity;
import shop.uz.exceptions.ResourceNotFoundException;
import shop.uz.repository.CategoryRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AttachService attachService;

    public CategoryDTO create(CategoryDTO category) {
        isExist(category);
        CategoryEntity entity = new CategoryEntity();
        entity.setNameUz(category.getNameUz());
        entity.setNameRu(category.getNameRu());
        entity.setNameEn(category.getNameEn());
        entity.setIconId(category.getIconId());
        entity.setOrderName(category.getOrderName());
        entity.setVisible(Boolean.TRUE);
        entity.setCreatedDate(LocalDateTime.now());
        if(category.getParentId()!=null){
            entity.setParentId(category.getParentId());
        }
        categoryRepository.save(entity);
        return toDTO(entity);
    }

    private CategoryDTO toDTO(CategoryEntity entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setNameUz(entity.getNameUz());
        dto.setNameRu(entity.getNameRu());
        dto.setNameEn(entity.getNameEn());
        dto.setIcon(attachService.getDto(entity.getIconId()));
        dto.setOrderName(entity.getOrderName());
        dto.setParentId(entity.getParentId());
        return dto;
    }

    private void isExist(CategoryDTO category) {
        Optional<CategoryEntity> entity = categoryRepository.isExist(category.getNameUz(), category.getNameRu(),category.getNameEn());
        if(entity.isPresent()){
            throw new ResourceNotFoundException("Category already exist");
        }
    }


    public List<CategoryDTO> getAll(Integer id) {
        List<CategoryEntity> entities = categoryRepository.findAllByVisibleTrue(id);
        List<CategoryDTO> dtos = new ArrayList<>();
        for(CategoryEntity entity : entities){
            dtos.add(toDTO(entity));
        }
        return dtos;
    }

    public void delete(Integer id) {
         categoryRepository.deleteCategory(id);
    }
}
