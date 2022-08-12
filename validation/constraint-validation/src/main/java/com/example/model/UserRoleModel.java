package com.example.model;

import com.example.constraints.ValueOfUserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserRoleModel {

    @ValueOfUserRole
    private int userRoleCode;

}
