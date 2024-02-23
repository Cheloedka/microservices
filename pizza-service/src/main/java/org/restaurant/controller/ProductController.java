package org.restaurant.controller;

import lombok.RequiredArgsConstructor;
import org.restaurant.dto.ProductDto;
import org.restaurant.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("")
    public List<ProductDto> getMenu() {

        return productService.getMenu();
    }

    @PostMapping("/new")
    public ResponseEntity<String> newProduct(@RequestBody ProductDto dto) {
        productService.newProduct(dto);
        return ResponseEntity.ok("New product has been created");
    }
}
