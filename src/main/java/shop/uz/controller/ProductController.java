package shop.uz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.uz.dto.ProductDTO;
import shop.uz.service.ProductService;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.create(productDTO));
    }
    @GetMapping("/{parentId}")
    public ResponseEntity<?> getAll(@PathVariable Integer parentId){
        return ResponseEntity.ok(productService.getProductsByCategory(parentId));
    }
    @PutMapping("/delete/{productId}")
    public ResponseEntity<?> delete(@PathVariable Integer productId){
        productService.delete(productId);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }


}
