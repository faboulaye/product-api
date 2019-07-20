package com.app.demopipeline.controller;

import com.app.demopipeline.bean.Product;
import com.app.demopipeline.repostitory.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable String id){
        Optional<Product> stock = repository.findById(id);
        if (!stock.isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(stock.get());
    }

    @PostMapping
    public ResponseEntity<Product> add(@Valid @RequestBody Product product) {
        ;
        return ResponseEntity.ok(repository.save(product));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        repository.deleteById(id);
        return ResponseEntity.ok("Delete successfully");
    }
}
