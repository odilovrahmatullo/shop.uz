package shop.uz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.uz.dto.CategoryDTO;
import shop.uz.service.CategoryService;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO category) {
        return ResponseEntity.ok(categoryService.create(category));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getAllCategories(@PathVariable Integer id) {
        return ResponseEntity.ok(categoryService.getAll(id));
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

}
