package org.toomuch2learn.crud.catalogue.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class Error {

    @NonNull
    private int code;
    @NonNull
    private String message;
    @NonNull
    private String description;
}
