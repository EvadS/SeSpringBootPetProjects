package com.se.songs.mapper;

import com.se.songs.entity.User;
import com.se.songs.enums.Role;
import com.se.songs.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;

// TODO: все чтокасается мапперов вынестив отдельный проект
@Mapper
public interface UserMapper {

    @Mappings({
            @Mapping(source = "role.value", target = "role")
    })
    UserDTO mapUserToUserDTO(User user);


    // нужен маппер по каждому елементу
    List<UserDTO> mapUsersToUserDTOs(List<User> users);

    //Mapping from Entity to DTO
    @Mappings({
            @Mapping(source = "userDTO", target = "role", qualifiedByName = "formRole")
    })
    User mapUserDTOtoUser(UserDTO userDTO);

    @Named("formRole")
    default Role formBundesland(UserDTO userDTO) {
        System.out.println(" formBundesland HERE !!!");
        Role roleEnum = null;
        for (Role r : Role.values()) {
            if (r.getValue() == (userDTO.getRole())) {
                roleEnum = r;
            }
        }

        return roleEnum;
    }
}