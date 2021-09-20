package com.se.sample.mapper;

import com.se.sample.model.UserDao;
import com.se.sample.model.UserResponse;
import com.se.sample.model.enums.Sex;

public class UserMapper {

    public static UserResponse toUserResponse(UserDao userDao) {

        Sex sex = Sex.fromId(userDao.getSex());
        UserResponse response = UserResponse.builder()
                .id(userDao.getId())
                .name(userDao.getName())
                .sex(sex.name())
                .build();

        return response;
    }
}
