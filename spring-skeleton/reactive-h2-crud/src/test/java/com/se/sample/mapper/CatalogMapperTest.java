package com.se.sample.mapper;

import com.se.sample.CatalogueItemGenerator;
import com.se.sample.model.CatalogueItem;
import com.se.sample.model.request.CatalogueRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CatalogMapperTest {

    CatalogMapper mapper =  CatalogMapper.INSTANCE;

    @Test
    void toCatalogueItem() {
        CatalogueRequest catalogueRequest = CatalogueItemGenerator.generateCatalogueRequest();

        CatalogueItem catalogueItem = mapper.toCatalogueItem(catalogueRequest);

        assertNotNull(catalogueItem);
    }
}