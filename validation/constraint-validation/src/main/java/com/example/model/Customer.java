package com.example.model;

import com.example.constraints.ValueOfEnum;
import com.example.enums.CustomerType;
import com.example.constraints.CustomerTypeSubset;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Customer {
    @NotNull
    @CustomerTypeSubset(anyOf = { CustomerType.NEW, CustomerType.OLD })
    private CustomerType customerTypeOfSubset;

    @ValueOfEnum(enumClass = CustomerType.class)
    private String customerTypeString;

    @NotNull
    int age;
}
