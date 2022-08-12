package com.se.maual.pagging.controller;

import com.se.maual.pagging.enums.Direction;
import com.se.maual.pagging.enums.OrderBy;
import com.se.maual.pagging.model.MonstaUsers;
import com.se.maual.pagging.service.MonstaUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Evgeniy Skiba on 20.06.2020
 * @project spring-data-jpa
 */

@RestController
@RequestMapping("/user-management")
public class UserManagementController {
@Autowired

   private MonstaUsersService userService;

    public Page<MonstaUsers> getUsers(String orderBy, String direction, int page, int size) {

        if (!(direction.equals(Direction.ASCENDING.getDirectionCode())
                || direction.equals(Direction.DESCENDING.getDirectionCode()))) {//PaginationSortingException
            throw new RuntimeException("Invalid sort direction");
        }
        if (!(orderBy.equals(OrderBy.ID.getOrderByCode())
                || orderBy.equals(OrderBy.USERID.getOrderByCode()))
                || orderBy.equals(OrderBy.EMAIL.getOrderByCode())) {
            //    throw new RuntimeException("Invalid orderBy condition");
        }
        Page<MonstaUsers> list = userService.getUsers(orderBy, direction, page, size);
        return list;

    }

}
