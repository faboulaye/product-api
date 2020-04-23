package com.app.api.product.repostitory;

import com.app.api.product.bean.Product;
import com.app.api.product.utils.ProductMock;
import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@Configuration
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

    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
        mongo.setBindIp("localhost");
        MongoClient mongoClient = mongo.getObject();
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, "embeded_db");
        return mongoTemplate;
    }
}