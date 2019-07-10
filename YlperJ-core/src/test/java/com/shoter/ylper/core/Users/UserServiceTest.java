package com.shoter.ylper.core.Users;

import com.mysql.cj.util.StringUtils;
import com.shoter.ylper.core.IntegrationTest;
import com.shoter.ylper.core.Results.MethodResult;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.util.StringHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import java.util.Date;

public class UserServiceTest{

    private User correctUser;
    private UserServiceImpl userService;
    private UserRepository userRepositoryMock;

    @BeforeEach
    public void beforeEachTest() {
        Gender someGender = new Gender();
        someGender.setId((byte)1);
        someGender.setName("Male");

        this.correctUser = new User();
        this.correctUser.setId(1);
        this.correctUser.setCreateDate(new Date());
        this.correctUser.setBirthDay(new Date());
        this.correctUser.setName("alladin");
        this.correctUser.setUsername("all");
        this.correctUser.setGender(someGender);

        this.userRepositoryMock = mock(UserRepository.class);

        when(this.userRepositoryMock.userExist(anyString())).thenReturn(false);
        when(this.userRepositoryMock.hasAnyBookings(anyLong())).thenReturn(false);
        when(this.userRepositoryMock.hasAnyDemands(anyLong())).thenReturn(false);

        this.userService = new UserServiceImpl(this.userRepositoryMock);

    }

    @Test
    public void canAddUser_returnTrue_whenCorrectUser()
    {
        MethodResult result = userService.canAddUser(correctUser);
        assertTrue(result.isSuccess());
    }

    @Test void canAddUser_returnFalse_whenUsernameAlreadyExist()
    {
        when(userRepositoryMock.userExist(anyString())).thenReturn(true);
        MethodResult result = userService.canAddUser(correctUser);
        assertTrue(result.hasError(UserErrors.userWithUsernameExist));
    }

    @Test
    public void canAddUser_returnFalse_whenGenderIsNull()
    {
        this.correctUser.setGender(null);
        MethodResult result = userService.canAddUser(correctUser);
        assertFalse(result.isSuccess());
    }

    @Test
    public void canAddUser_returnFalse_whenGenderIsOutOfBounds()
    {
        this.correctUser.getGender().setId((byte)90);
        MethodResult result = userService.canAddUser(correctUser);
        assertTrue(result.hasError(UserErrors.incorrectGender));
    }

    @Test
    public void canAddUser_returnFalse_whenNameIsNull()
    {
        this.correctUser.setName(null);
        MethodResult result = userService.canAddUser(correctUser);
        assertFalse(result.isSuccess());
    }

    @Test
    public void canAddUser_returnFalse_whenUsernameIsNull()
    {
        this.correctUser.setUsername(null);
        MethodResult result = userService.canAddUser(correctUser);
        assertFalse(result.isSuccess());
    }

    @Test
    public void canAddUser_returnFalse_whenNameIsTooLong()
    {
        this.correctUser.setName(StringHelper.repeat(".", 201));
        MethodResult result = userService.canAddUser(correctUser);
        assertFalse(result.isSuccess());
    }

    @Test
    public void canAddUser_returnTrue_whenNameHasCorrectLength()
    {
        this.correctUser.setName(StringHelper.repeat(".", 200));
        MethodResult result = userService.canAddUser(correctUser);
        assertTrue(result.isSuccess());
    }

    @Test
    public void canAddUser_returnFalse_whenUsernameIsTooLong()
    {
        this.correctUser.setUsername(StringHelper.repeat(".", 51));
        MethodResult result = userService.canAddUser(correctUser);
        assertFalse(result.isSuccess());
    }

    @Test
    public void canAddUser_returnTrue_whenUsernameHasCorrectLength()
    {
        this.correctUser.setUsername(StringHelper.repeat(".", 50));
        MethodResult result = userService.canAddUser(correctUser);
        assertTrue(result.isSuccess());
    }

    @Test
    public void canAddUser_returnFalse_whenCreateDateIsNull()
    {
        this.correctUser.setCreateDate(null);
        MethodResult result = userService.canAddUser(correctUser);
        assertFalse(result.isSuccess());
    }

    @Test
    public void canAddUser_returnFalse_whenBirthdayIsNull()
    {
        this.correctUser.setBirthDay(null);
        MethodResult result = userService.canAddUser(correctUser);
        assertFalse(result.isSuccess());
    }



}