package com.shoter.ylper.core.Users;

import com.shoter.ylper.core.IntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceIntegrationTest extends IntegrationTest {

    private UserService userService;

    @Override
    @BeforeEach
    public void beforeEachTest() {
        super.beforeEachTest();

        userService = new UserServiceImpl(session);
    }

    @Test
    public void getUser_shouldReturnUser_afterAddUser()
    {
        User correctUser = new User();

        correctUser.setId(1);
        correctUser.setCreateDate(new Date());
        correctUser.setBirthDay(new Date());
        correctUser.setName("alladin");
        correctUser.setUsername("all");
        correctUser.setGender(session.load(Gender.class, (byte)1));

        userService.addUser(correctUser);
        User addedUser = userService.getUser(correctUser.getId());

        assertEquals(correctUser.getName(), addedUser.getName());
        assertEquals(correctUser.getUsername(), addedUser.getUsername());
        assertEquals(correctUser.getBirthDay(), addedUser.getBirthDay());
        assertEquals(correctUser.getGender().getId(), addedUser.getGender().getId());
        assertEquals(correctUser.getCreateDate(), addedUser.getCreateDate());
    }
}
