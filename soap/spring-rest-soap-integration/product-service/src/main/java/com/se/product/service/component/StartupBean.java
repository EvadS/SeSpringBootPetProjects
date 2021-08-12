package com.se.product.service.component;

import com.se.product.service.domain.Category;
import com.se.product.service.domain.Product;
import com.se.product.service.domain.Role;
import com.se.product.service.domain.User;
import com.se.product.service.model.enums.RoleName;
import com.se.product.service.model.request.RegistrationRequest;
import com.se.product.service.repository.CategoryRepository;
import com.se.product.service.repository.ProductRepository;
import com.se.product.service.repository.RoleRepository;
import com.se.product.service.repository.UserRepository;
import com.se.product.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StartupBean implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private  final ProductRepository productRepository;

    public StartupBean(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {

        if(roleRepository.findAll().size()<1) {
            createTestUSerAndRole();
        }





        List<User> name = userService.findAll("name");
//
//        productRepository.deleteAllInBatch();
//        categoryRepository.deleteAllInBatch();
//
//        //
//        Product product = new Product();
//        product.setName("product name 1");
//
//
//        //
//        Category category1= new Category();
//        category1.setName("category1");
//        category1.setCode("1111");//
//
//        Category category2= new Category();
//        category2.setName("category2");
//        category2.setCode("2222");
//
//
//        productRepository.save(product);
//
//        categoryRepository.save(category1);
//        categoryRepository.save(category2);
//
//        product.addCategory(category1);
//        productRepository.save(product);
//
//        int a=0;
//
//        Category category3= new Category();
//        category3.setName("category3");
//        category3.setCode("3333");
//
//        categoryRepository.saveAll(Arrays.asList(category1,category2));
//
//
//        Product product = new Product();
//        product.setName("product name 1");
//        product.getCategories().add(category1);
//        product.getCategories().add(category2);
//
//        productRepository.save(product);
//
//        product.addCategory(category3);
//        productRepository.save(product);
//
//        List<Product> products = productRepository.findAll();
//
//        products.stream().forEach(System.out::println);

    }

    private void createTestUSerAndRole() {
        Role role = new Role();
        role.setRole(RoleName.ROLE_USER);
        roleRepository.save(role);

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setEmail("mail@mail.com");
        registrationRequest.setPassword("123456");
        registrationRequest.setUsername("name");

        User user = userService.createUser(registrationRequest);
        userRepository.save(user);


        registrationRequest = new RegistrationRequest();
        registrationRequest.setEmail("mail2@mail.com");
        registrationRequest.setPassword("123456");
        registrationRequest.setUsername("name2");

        user = userService.createUser(registrationRequest);
        userRepository.save(user);

        registrationRequest = new RegistrationRequest();
        registrationRequest.setEmail("mail3@mail.com");
        registrationRequest.setPassword("123456");
        registrationRequest.setUsername("test");


        user = userService.createUser(registrationRequest);
        userRepository.save(user);
    }
}
