package com.se.sample;

import com.se.sample.repository.ProductRepository;
import com.se.sample.service.ProductService;
import com.se.sample.service.impl.ProductServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ActiveProfiles({"test"})
@ContextConfiguration(classes = {ProductServiceImpl.class})
class EsWebfluxStartedKitApplicationTests {

    @MockBean
    ProductRepository productRepository;


    @MockBean
    ProductValidator productValidator;

    ProductService productService;

    @Test
    void contextLoads() {
        Assert.assertTrue(true);
    }
}
