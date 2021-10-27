package com.se.sample;

import com.se.sample.configuration.FileStorageProperties;
import com.se.sample.model.CatalogueItem;
import com.se.sample.repository.CatalogueRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.time.Instant;


@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
@OpenAPIDefinition(info = @Info(title = "APIs", version = "1.0", description = "Documentation APIs v1.0"))
public class ReactiveH2CrudApplication  implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ReactiveH2CrudApplication.class, args);
    }

    @Autowired
    CatalogueRepository repo;
    @Override
    public void run(String... args) throws Exception {
        CatalogueItem item = new CatalogueItem();
        item.setSku("sku");
        item.setName("name");
        item.setDescription("Description");

        item.setPrice(0.0);
        item.setInventory(0);
        item.setCreatedOn(Instant.now());
        item.setUpdatedOn(Instant.now());

        repo.save(item);

    }
}
