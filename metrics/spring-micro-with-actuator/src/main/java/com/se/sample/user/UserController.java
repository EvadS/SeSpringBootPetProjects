package com.se.sample.user;


import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/user")
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final List<User> userListV1 = new ArrayList<User>(
            Arrays.asList(new User("moe"), new User("larry"), new User("curly"))
    );

    // constructor injector for exposing metrics at Actuator /prometheus
    public UserController(MeterRegistry registry) {

        Gauge.builder("usercontroller.usercount", fetchUserCount()).
                tag("version", "v1").
                description("usercontroller descrip").
                register(registry);
    }

    public Supplier<Number> fetchUserCount() {
        return () -> userListV1.size();
    }

    @Timed(value = "user.get.time", description = "time to retrieve users", percentiles = {0.5, 0.9})
    @Operation(summary = "get list of users")
    @ApiResponse(responseCode = "200")
    @GetMapping(produces = "application/vnd.user.v1+json")
    public Iterable<User> findAllUsers() {
        logger.trace("doing findAllUsers");
        logger.debug("doing findAllUsers");
        logger.info("doing findAllUsers");
        logger.error("doing findAllUsers");
        return userListV1;
    }

    @Operation(summary = "delete last user")
    @ApiResponse(responseCode = "200")
    @DeleteMapping(produces = "application/vnd.user.v1+json")
    public void deleteUser() {
        logger.debug("called deleteUser");
        if (userListV1.size() > 0) {
            userListV1.remove(userListV1.size() - 1);
        }
    }

/*
    @Operation(summary = "get list of users")
    @ApiResponse(responseCode = "200")
    @GetMapping(produces = "application/vnd.user.v2+json")
    public Iterable<Userv2> findAllUsersv2() {
        Iterable<Userv2> list = Arrays.asList(new Userv2("moe","fine"),new Userv2("larry","fine"));
        return list;
    }
*/


}
