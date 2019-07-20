package com.app.demopipeline.repostitory;

import com.app.demopipeline.bean.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    @MockBean
    private MongoTemplate template;

    @Test
    public void testFindByCodeWhenProductNotExist() {
        Query query = new Query();
        query.addCriteria(Criteria.where("code").is("XXX-XX-XXX"));
        Mockito.when(template.findOne(query, Product.class)).thenReturn(null);

        Assert.assertNull(repository.findByCode("XXX-XX-XXX"));
    }
}