package com.app.demopipeline.repostitory;

import com.app.demopipeline.bean.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

    Product findByCode(String code);
}
