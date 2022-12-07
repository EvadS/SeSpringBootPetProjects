package com.se.sample.appicationprops;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;


@Component
@ConfigurationProperties(prefix = "brand-default-values")
public class BrandDefaultValues {
    private Map<String, Map<String, BrandDefaultValue>> brands;

    public BrandDefaultValues() { }

    public BrandDefaultValues(Map<String, Map<String, BrandDefaultValue>> brands) {
        this.brands = brands;
    }

    public Map<String, Map<String, BrandDefaultValue>> getBrands() {
        return brands;
    }

    public void setBrands(Map<String, Map<String, BrandDefaultValue>> brands) {
        this.brands = brands;
    }

    @PostConstruct
    void init() {
        brands.forEach((brand, properties) -> {
            if (properties != null) {
                properties.forEach((propertyName, defaultValue) ->{
                    if (defaultValue != null) {
                        Optional.of(getValueClass(defaultValue.getType()))
                                .ifPresentOrElse(defaultValue::setTypeClass, () -> {
                                    throw new IllegalArgumentException("Could not assign class to brand default property");
                                });
                        defaultValue.setTypeClass(getValueClass(defaultValue.getType()));
                    }
                });
            }
        });
    }
}
