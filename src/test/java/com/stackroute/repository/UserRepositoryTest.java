package com.stackroute.repository;

import com.stackroute.domain.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    User user;

    @Before
    public void setUp() {
        user = new User(1, "harika", "boddu", 22);
    }

    @After
    public void tearDown() {

        userRepository.deleteAll();
    }

    @Test
    public void testSaveUser() {
        userRepository.save(user);
        User fetchTrack = userRepository.findById(this.user.getId()).get();
        Assert.assertEquals(1, fetchTrack.getId());
    }



}