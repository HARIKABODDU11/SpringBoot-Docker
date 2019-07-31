package com.stackroute.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.User;

import com.stackroute.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import static org.mockito.ArgumentMatchers.any;//



//@RunWith(SpringRunner.class)
//@WebMvcTest
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private User user;
    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();
    }

    @Test
    public void saveUser() throws Exception {
        User user= new User(1,"harika","boddu",21);
        when(userService.saveUser(any())).thenReturn(user);
        mockMvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isCreated());
        verify(userService,times(1)).saveUser(Mockito.any(User.class));
        verifyNoMoreInteractions(userService);
    }
    @Test
    public void getAllUsersTest() throws Exception
    {
        List<User> users = new ArrayList<>();
        User user1 = new User(1,"harika","boddu",21);
        User user2 = new User(2,"hema","betina",21);
        users.add(user1);
        users.add(user2);
        when(userService.getAllUsers()).thenReturn(users);
        mockMvc.perform(get("/api/v1/user1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
        verify(userService, times(1)).getAllUsers();
        verifyNoMoreInteractions(userService);
    }





    @Test
    public void updateUser() throws Exception
    {
        User user1 = new User(11,"harika","boddu",21);
        User user2 = new User(1,"satya","prathipati",22);
        when(userService.updateUser(user1,1)).thenReturn(user2);
        mockMvc.perform(put("/api/v1/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user1)))
                .andExpect(status().isOk());
       // verify(userService, times(2)).updateUser(Mockito.any(User.class),1);//
        verifyNoMoreInteractions(userService);
    }

@Test
public void deleteTrackTest() throws Exception
{
    when(userService.deleteUser(1)).thenReturn(true);
    mockMvc.perform(delete("/api/v1/user/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    verify(userService, times(1)).deleteUser(1);
    verifyNoMoreInteractions(userService);
}


    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }



}