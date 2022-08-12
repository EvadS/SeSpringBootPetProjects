package com.se.songs.controller;

import com.se.songs.entity.User;
import com.se.songs.mapper.UserMapper;
import com.se.songs.model.UserDTO;
import com.se.songs.model.UserDatabase;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    /**
     * role as a enum item
     * ----
     * name	"Elton Winsley"
     * role	"STAFF"
     * @return
     */

    // role as a enum item
    @GetMapping("/all-int")
    public ResponseEntity getAllUsers() {
        UserDatabase userDatabase = new UserDatabase();
        List<User> allUser = userDatabase.getAllUser();


        return ResponseEntity.ok(allUser);
    }

    /**
     *  role as a enum value
     *  {"name":"Elton Winsley","role":3}
     * @return
     */
    @GetMapping("/all-mapped")
    public ResponseEntity getAllUsersMapped() {
        UserDatabase userDatabase = new UserDatabase();
        List<User> allUser = userDatabase.getAllUser();

        List<UserDTO> userDTOs = userMapper.mapUsersToUserDTOs(allUser);

        return ResponseEntity.ok(userDTOs);
    }

    //input

//    {
//        "name" : "Elton Winsley",
//            "role" : 3
//    }

    // output
//    {
//        "name": "Elton Winsley",
//            "role": "STAFF"
//    }
    @PutMapping("/updateUser")
    public ResponseEntity updateUser(@RequestBody UserDTO userDTO) {
        User user = userMapper.mapUserDTOtoUser(userDTO);
        //saving operation to mock or real database

        return ResponseEntity.ok(user);
    }
}
