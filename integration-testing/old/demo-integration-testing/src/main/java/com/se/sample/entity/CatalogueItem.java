package com.se.sample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class CatalogueItem {

    private Long id;
    private String sku;
    private String name;
}
