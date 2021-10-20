package com.se.sample.mappers;

import com.se.sample.constants.GeneralConstants;
import com.se.sample.entity.User;
import com.se.sample.models.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper
public interface UserMapper {

    UserResponse toUserResponse(User user);

    default  String mapLongToString(Long timestamp){
        Date date = new Date();
        date.setTime(timestamp);

        return new SimpleDateFormat(GeneralConstants.dateFormat).format(date);
    }
}
