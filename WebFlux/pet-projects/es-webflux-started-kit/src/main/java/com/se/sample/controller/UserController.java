package com.se.sample.controller;

import com.se.sample.models.request.UserRequest;
import com.se.sample.models.response.UserResponse;
import com.se.sample.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Tag(name = "User Api", description = "the user API with documentation annotations")
public class UserController {

    private UserService userService;
//    @GetMapping("/get/{id}")
//    public Mono<ResponseEntity<Bucket>> getBucketById(@PathVariable(value = "id") String bucketId) {
//        return bucketRepository.findById(bucketId)
//                .map(saveBucket -> ResponseEntity.ok(saveBucket))
//                .defaultIfEmpty(ResponseEntity.notFound().build());
//            .switchIfEmpty(Mono.error(new BucketNotFoundException("Data not found")));
//            .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//    }

    @PostMapping(value = "/", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UserResponse> createEmployee(@RequestBody UserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping(value = "/{id}")
    public Mono<UserResponse> getById(@PathVariable("id") int id) {
        return userService.getById(id);
    }

    @DeleteMapping(value = "/{id}")
    public Mono<String> deleteById(@PathVariable("id") int id) {
        return userService.deleteById(id);
    }
}
