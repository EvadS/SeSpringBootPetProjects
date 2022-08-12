package com.se.specification.example.controller.mapper;

/**
 * @author Evgeniy Skiba on 20.06.2020
 * @project spring-data-jpa
 */

import com.se.specification.example.controller.dto.UserDto;
import com.se.specification.example.domain.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

//    @Mappings({
//            @Mapping(target="lastName", source="user.email")
//            //,@Mapping(target="firstName", source="user.firstName")
//    })
    UserDto map(User user);

    List<UserDto> map(List<User> users);
}
