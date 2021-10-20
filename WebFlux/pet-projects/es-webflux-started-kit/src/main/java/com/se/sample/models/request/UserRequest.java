package com.se.sample.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserRequest {

    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
}
