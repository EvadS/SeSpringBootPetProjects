package com.se.sample.service;

import com.se.sample.exception.ResourceNotFoundException;
import com.se.sample.model.CatalogueItem;
import com.se.sample.repository.CatalogueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;



@Service
public class CatalogueCrudService {


    @Autowired
    private CatalogueRepository catalogueRepository;


    public List<CatalogueItem> getCatalogueItems() {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");

        return catalogueRepository.findAll(sort);
    }

    public CatalogueItem getCatalogueItem( String skuNumber) throws ResourceNotFoundException {
        return getCatalogueItemBySku(skuNumber);
    }

    public Long addCatalogItem(CatalogueItem catalogueItem) {
        catalogueItem.setCreatedOn(new Date());

        return catalogueRepository.save(catalogueItem).getId();
    }

    public void updateCatalogueItem(CatalogueItem catalogueItem) throws ResourceNotFoundException{

        CatalogueItem catalogueItemfromDB = getCatalogueItemBySku(catalogueItem.getSku());

        catalogueItemfromDB.setName(catalogueItem.getName());
        catalogueItemfromDB.setDescription(catalogueItem.getDescription());
        catalogueItemfromDB.setPrice(catalogueItem.getPrice());
        catalogueItemfromDB.setInventory(catalogueItem.getInventory());
        catalogueItemfromDB.setUpdatedOn(new Date());

        catalogueRepository.save(catalogueItemfromDB);
    }

    public void deleteCatalogueItem(CatalogueItem catalogueItem) {
        catalogueRepository.delete(catalogueItem);
    }

    private CatalogueItem getCatalogueItemBySku(String skuNumber) throws ResourceNotFoundException {
        CatalogueItem catalogueItem = catalogueRepository.findBySku(skuNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Catalogue Item not found for the provided SKU :: %s" , skuNumber)));

        return catalogueItem;
    }
}
