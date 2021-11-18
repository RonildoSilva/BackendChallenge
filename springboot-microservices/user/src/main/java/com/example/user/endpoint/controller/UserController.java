package com.example.user.endpoint.controller;


import com.example.domain.model.User;
import com.example.user.endpoint.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin
@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/api/v1/admin/")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "Welcome to this API")
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping("")
    public ResponseEntity<String> home() {
        return new ResponseEntity<>("Welcome to this API",HttpStatus.OK);
    }

    @ApiOperation(value = "Get a pagination of all users from database")
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping("users")
    public ResponseEntity<Iterable<User>> getAllUsers(Pageable pageable) {
        return new ResponseEntity<>(userService.list(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Get a user by an existing id")
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping("user/{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a user by an existing id")
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping("user/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        if(userService.findById(id).equals(null)){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        else {
            try {
                userService.deleteById(id);
                return new ResponseEntity<Void>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }
        }
    }

    @ApiOperation(value = "Update a user by an existing id")
    @PutMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping("user/update")
    public ResponseEntity<Void> updateById(@Valid @RequestBody User user) {
        if(userService.findById(user.getId()).equals(null)){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        else{
            try {
                userService.update(user);
                return new ResponseEntity<Void>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }
        }
    }

    @ApiOperation(value = "Get a user by an existing login")
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping("user/login/{login}")
    public ResponseEntity<User> findByLogin(@PathVariable("login") String login) {
        return new ResponseEntity<>(userService.findByLogin(login), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new user")
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping("user")
    public ResponseEntity<User> createItem(@Valid @RequestBody User user) {
        try{
            return new ResponseEntity<User>(userService.add(user), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<User>(new User(), HttpStatus.BAD_REQUEST);
        }

    }




}
