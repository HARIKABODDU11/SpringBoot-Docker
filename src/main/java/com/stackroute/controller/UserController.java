package com.stackroute.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.Result;
import com.stackroute.domain.User;
import com.stackroute.exceptions.UserAlreadyExistsException;
import com.stackroute.exceptions.UserNotFoundException;
import com.stackroute.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("user")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistsException {
            userService.saveUser(user);
            return new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
    }

    @GetMapping("user1")
    public ResponseEntity<?> getAllUsers() {
        ResponseEntity responseEntity;

        try {
            responseEntity = new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
        }
        catch (Exception exception) {

            responseEntity = new ResponseEntity<String>(exception.getMessage(), HttpStatus.CONFLICT);
        }

        return responseEntity;
    }


//
//    @GetMapping("userByName")
//    public ResponseEntity<?> userByName(@RequestParam String name) throws UserNotFoundException{
//        userService.userByName(name);
//        return  new ResponseEntity<String>("user by name", HttpStatus.OK);
//    }



    @PutMapping("user/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable int id) throws UserNotFoundException
    {
        User user1 = userService.updateUser(user,id);
        return new ResponseEntity<User>(user1, HttpStatus.OK);
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) throws UserNotFoundException {
        userService.deleteUser(id);
        return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
    }


    @GetMapping("getLastFmTracks")
    public ResponseEntity<?> getLastFmTracks(@RequestParam String url) throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(url);
        String string = restTemplate.getForObject(url,String.class);
        System.out.println("helo");
        ObjectMapper objectMapper = new ObjectMapper();
        Result result = objectMapper.readValue(string, Result.class);
        List<User> userList = result.results.usermatches.track;
        List<User> savedTrackList = new ArrayList<>();
        for (User user:userList) {
            User user1 = userService.saveUser(user);
            savedTrackList.add(user1);
        }
        return new ResponseEntity<>(savedTrackList,HttpStatus.OK);
    }


}
