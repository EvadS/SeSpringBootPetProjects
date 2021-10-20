package com.se.sample.models.mapper;


import com.se.sample.models.UserDto;
import com.se.sample.models.enums.UserRoles;
import com.se.sample.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.HashSet;

@Mapper(imports = {HashSet.class, Collections.class, SimpleGrantedAuthority.class, UserRoles.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true)
    UserDto toDTO(final User user);

    User fromDTO(final UserDto userDto);
}
