package com.se.product.service.component;

import com.se.product.service.domain.Cat;
import com.se.product.service.domain.Owner;
import com.se.product.service.domain.Product;
import com.se.product.service.domain.specification.Specs;
import com.se.product.service.domain.specification.SpecsProduct;
import com.se.product.service.repository.CatRepository;
import com.se.product.service.repository.CategoryRepository;
import com.se.product.service.repository.OwnerRepository;
import com.se.product.service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class StartupBean implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    @Autowired
    CatRepository catRepository;
    @Autowired
    OwnerRepository ownerRepository;

    public StartupBean(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        pushIfEmpty();


//        Specification<Cat> catSpec = Specs.hasOwnerName("owner_cat_1_cat_2");
//        List<Cat> all = catRepository.findAll(catSpec);

        Specification<Product> productSpec = SpecsProduct.testJoin("string9");
        List productsList = productRepository.findAll(productSpec);


        System.out.println("*********");
        productsList.stream().forEach(System.out::println);
        System.out.println("*********");


        int a = 0;
    }

    private void pushIfEmpty() {
        if (catRepository.count() < 1) {
            Cat cat = new Cat("cat 1");
            Cat cat2 = new Cat("cat 2");
            Cat cat3 = new Cat("cat 3");
            Cat cat4 = new Cat("cat 4");
            Cat cat5 = new Cat("cat 5");
            catRepository.saveAll(Arrays.asList(cat, cat2, cat3, cat4, cat5));

            Owner owner = new Owner("owner_cat_1_cat_2");
            owner.getCats().add(cat);
            owner.getCats().add(cat2);

            Owner owner2 = new Owner("owner_cat_2_cat_3");
            owner2.getCats().add(cat3);
            owner2.getCats().add(cat4);

            Owner owner3 = new Owner("owner_cat_4");
            owner3.getCats().add(cat4);

            ownerRepository.saveAll(Arrays.asList(owner, owner2, owner3));

        }

    }

}
