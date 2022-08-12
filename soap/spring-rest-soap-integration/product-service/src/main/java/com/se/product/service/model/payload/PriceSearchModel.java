package com.se.product.service.model.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceSearchModel {
   private List<Integer> currenciesList;
   private Double costFrom;
   private Double costTo;
}
