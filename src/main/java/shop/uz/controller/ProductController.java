package shop.uz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.uz.service.ProductService;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;
}