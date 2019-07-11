package com.shoter.ylper.core.Users;

import com.shoter.ylper.core.Bookings.BookingRepository;
import com.shoter.ylper.core.Demands.DemandRepository;
import com.shoter.ylper.core.Results.MethodResult;
import org.hibernate.internal.util.StringHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Calendar;
import java.util.Date;

public class UserServiceTest{

    private User correctUser;
    private UserServiceImpl userService;
    private UserRepository userRepositoryMock;
    private DemandRepository demandRepositoryMock;
    private BookingRepository bookingRepositoryMock;

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
        this.demandRepositoryMock = mock(DemandRepository.class);
        this.bookingRepositoryMock = mock(BookingRepository.class);

        when(this.userRepositoryMock.userExists(anyString())).thenReturn(false);
        when(this.userRepositoryMock.hasAnyBookings(anyLong())).thenReturn(false);
        when(this.userRepositoryMock.hasAnyDemands(anyLong())).thenReturn(false);

        this.userService = new UserServiceImpl(this.userRepositoryMock, this.demandRepositoryMock, this.bookingRepositoryMock);

    }

    @Test
    public void canAddUser_returnTrue_whenCorrectUser()
    {
        MethodResult result = userService.canAddUser(correctUser);
        assertTrue(result.isSuccess());
    }

    @Test void canAddUser_returnFalse_whenUsernameAlreadyExist()
    {
        when(userRepositoryMock.userExists(anyString())).thenReturn(true);
        MethodResult result = userService.canAddUser(correctUser);
        assertTrue(result.hasError(UserErrors.userWithUsernameExist));
    }

    @Test void canAddUser_returnFalse_whenBirthdayIsInFuture()
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 1);
        correctUser.setBirthDay(cal.getTime());
        MethodResult result = userService.canAddUser(correctUser);
        assertTrue(result.hasError(UserErrors.birthdayCannotBeInFuture));
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
