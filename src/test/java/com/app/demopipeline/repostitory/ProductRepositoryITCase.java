package com.app.demopipeline.repostitory;

import com.app.demopipeline.bean.Product;
import com.app.demopipeline.utils.ProductMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductRepositoryITCase {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private MongoTemplate template;

    @Before
    public void before() {
        ProductMock.getProducts().forEach(product -> template.save(product));
    }

    @After
    public void after() {
        template.findAllAndRemove(new Query(), Product.class);
    }

    @Test
    public void testFindByCodeWhenProductNotExist() {
        Assert.assertNull(repository.findByCode("XXX-XX-XXX"));
    }

    @Test
    public void testFindByCodeWhenProductExist() {
        String expectedCode = "123-34-45";
        Product product = repository.findByCode(expectedCode);
        Assert.assertEquals(expectedCode, product.getCode());
        Assert.assertEquals("Riz", product.getLabel());
    }
}