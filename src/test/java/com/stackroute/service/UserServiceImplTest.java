package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exceptions.UserNotFoundException;
import com.stackroute.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    User user;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userService).build();
        user = new User(1, "harika", "boddu", 22);
    }

    @Test
    public void saveTrackTest() throws Exception {
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userService.saveUser(user);
        Assert.assertEquals(user, savedUser);

        //verify here verifies that userRepository save method is only called once
        verify(userRepository, times(1)).save(Mockito.any(User.class));
        verify(userRepository, times(1)).existsById(1);
        verifyNoMoreInteractions(userRepository);
    }




    @Test
    public void getAllUsersTest()
    {
        List<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);
        List<User> retrievedUsers = userService.getAllUsers();
        Assert.assertEquals(userList,retrievedUsers);
        verify(userRepository,times(1)).findAll();
        verifyNoMoreInteractions(userRepository);
    }
    @Test
    public void updateServiceTest() throws UserNotFoundException
    {
        Optional<User> optionalUser = Optional.of(user);
        when(userRepository.findById(1)).thenReturn(optionalUser);
        when(userRepository.save(user)).thenReturn(user);
        User updatedUser = userService.updateUser(user,1);
        Assert.assertEquals(user,updatedUser);
        //verify here verifies that userRepository save method is only called once
        verify(userRepository,times(1)).save(Mockito.any(User.class));
        verify(userRepository,times(1)).findById(1);
        verifyNoMoreInteractions(userRepository);
    }


    @Test
    public void deleteUserTest() throws UserNotFoundException
    {
        Optional<User> optionalUser = Optional.of(user);
        when(userRepository.findById(1)).thenReturn(optionalUser);
        Boolean result = userService.deleteUser(1);
        Assert.assertTrue(result);
        //verify here verifies that userRepository save method is only called once
        verify(userRepository,times(1)).delete(Mockito.any(User.class));
        verify(userRepository,times(1)).findById(1);
        verifyNoMoreInteractions(userRepository);
    }

}