package com.se.sample.model.response;

import com.se.sample.model.request.BookRequest;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class BookResponse {
    private  String id;
    private BookRequest bookRequest;
}
