package com.se.sample;


//import com.toomuch2learn.reactive.crud.catalogue.model.CatalogueItem;

import com.se.sample.model.CatalogueItem;
import com.se.sample.model.request.CatalogueRequest;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;


//TODO: use faker
public class CatalogueItemGenerator {

    private static Instant now = Instant.now();

    public static CatalogueItem generateCatalogueItem() {
        return generateCatalogueItem(1000l);
    }

    public static CatalogueRequest generateCatalogueRequest() {
        return generateCatalogueRequest(1000l);
    }

    /**
     * Generate sample Catalogue Item which will be used in test classes
     *
     * @return catalogueItem
     */
    private static CatalogueItem generateCatalogueItem(Long id) {
        CatalogueItem item = new CatalogueItem();
        item.setId(id);
        item.setSku("SKU-1234");
        item.setName("Item Name");
        item.setDescription("Item Desc");
        item.setCategory("Books");
        item.setInventory(10);
        item.setPrice(100.0);
        item.setCreatedOn(now);

        return item;
    }


    private static CatalogueRequest generateCatalogueRequest(Long id) {
        CatalogueRequest item = new CatalogueRequest();

        item.setSku("SKU-1234");
        item.setName("Item Name");
        item.setDescription("Item Desc");
        item.setCategory("Books");
        item.setInventory(10);
        item.setPrice(100.0);

        return item;
    }

    public static List<CatalogueItem> generateCatalogueItemsList() {
        return
                LongStream.range(1, 100).mapToObj(value -> {
                    return generateCatalogueItem(value);
                }).collect(Collectors.toList());
    }
}