package org.toomuch2learn.crud.catalogue.model;

import lombok.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class CatalogueItem {

    private Long id;

    @NonNull private String sku;
    @NonNull private String name;
    @NonNull private String description;
    @NonNull private String category;
    @NonNull private Double price;
    @NonNull private Integer inventory;
    @NonNull private Date createdOn;

    private Date updatedOn;
}
