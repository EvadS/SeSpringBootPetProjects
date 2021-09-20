package com.se.sample.entity.mapper;


import com.se.sample.entity.dto.UserDto;
import com.se.sample.entity.enums.UserRoles;
import com.se.sample.entity.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


//import com.reactive.io.entity.enums.UserRoles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Collections;
import java.util.HashSet;

@Mapper(imports = {HashSet.class, Collections.class, SimpleGrantedAuthority.class, UserRoles.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true)
    UserDto toDTO(final User user);

    User fromDTO(final UserDto userDto);
}
