package com.se.songs.mapper;

import com.se.songs.dto.AdminUserDTO;
import com.se.songs.entity.AdminUser;
import com.se.songs.enums.AdminUserRole;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import junit.framework.Assert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class AdminUserMapperTest {

    private final AdminUserMapper mapper = Mappers.getMapper(AdminUserMapper.class);

    AdminUserDTO  adminUserDto;
    ;
    AdminUserDTO superAdminUser;
    AdminUserDTO unknownUser;

    @Before
    public void init() {
        superAdminUser = new AdminUserDTO();
        superAdminUser.setId(1L);
        superAdminUser.setAdminUserRole(AdminUserRole.SUPER_ADMIN);
        superAdminUser.setEmail("superAdminUser.mail.com");
        superAdminUser.setPassword("superAdminUser");
        //----------------
        unknownUser = new AdminUserDTO();
        unknownUser.setId(1L);
        unknownUser.setAdminUserRole(AdminUserRole.NOT_SET);
        unknownUser.setEmail("unknownUser.mail.com");
        unknownUser.setPassword("unknownUser");
    }

    @Test
    public void adminDto_to_admin_should_correct(){

        //TODO: before not work
        adminUserDto = new AdminUserDTO();
        adminUserDto.setId(1L);
        adminUserDto.setAdminUserRole(AdminUserRole.ADMIN);
        adminUserDto.setEmail("mail.mail.com");
        adminUserDto.setPassword("pwd");

      AdminUser adminUser =  mapper.toAdminUser(adminUserDto);

      assertTrue(true);

      assertEquals(adminUser.getEmail(), adminUserDto.getEmail());
      assertEquals(adminUser.getPassword(), adminUserDto.getPassword());
      assertEquals(adminUser.getRoleEntity().getName(), adminUserDto.getAdminUserRole().getName());
      assertEquals(adminUser.getId(), adminUserDto.getId());


    }
}