package com.se.songs.mapper;

import com.se.songs.dto.AdminUserDTO;
import com.se.songs.entity.AdminUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface AdminUserMapper {


    @Mappings({
            @Mapping(source = "adminUserDTO.adminUserRole", target = "roleEntity")
    })

    AdminUser toAdminUser(AdminUserDTO adminUserDTO);
}
