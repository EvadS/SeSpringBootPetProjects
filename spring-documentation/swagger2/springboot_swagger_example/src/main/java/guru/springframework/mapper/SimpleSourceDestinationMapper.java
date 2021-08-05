package guru.springframework.mapper;

import guru.springframework.domain.Product;
import guru.springframework.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author Evgeniy Skiba on 23.06.2020
 * @project spring-boot-web
 */
@Mapper
public interface  SimpleSourceDestinationMapper {

    @Mappings({
            @Mapping(target="description", source = "description"),
            @Mapping(target="imageUrl", source="imageUrl")
    })
    Product map (ProductDto product);
}
