package shop.uz.service;

import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.uz.dto.CategoryDTO;
import shop.uz.entity.CategoryEntity;
import shop.uz.exceptions.ResourceNotFoundException;
import shop.uz.repository.CategoryRepository;

import java.time.LocalDateTime;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

}
