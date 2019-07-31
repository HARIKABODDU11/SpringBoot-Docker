package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exceptions.UserAlreadyExistsException;
import com.stackroute.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {

    public User saveUser(User user) throws UserAlreadyExistsException;
    public List<User> getAllUsers();
//    public List<User> userByName(String name) throws UserNotFoundException;

    public User updateUser(User user, int id) throws UserNotFoundException;

    public boolean deleteUser(int id) throws UserNotFoundException;
}
