package com.app.demopipeline.utils;

import com.app.demopipeline.bean.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductMock {

    public static List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("123-34-45", "Riz", new BigDecimal("21.5"), "KG"));
        products.add(new Product("623-54-45", "Farine", new BigDecimal("13.5"), "KG"));
        products.add(new Product("603-74-45", "Lait", new BigDecimal("3.5"), "LTR"));
        products.add(new Product("600-74-45", "Cerise", new BigDecimal("2.5"), "LTR"));
        products.add(new Product("663-94-45", "Tomate", new BigDecimal("6.5"), "KG"));
        return products;
    }

}
