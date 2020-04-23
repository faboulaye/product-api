package com.app.api.product.repostitory;

import com.app.api.product.bean.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

    Product findByCode(String code);
}
