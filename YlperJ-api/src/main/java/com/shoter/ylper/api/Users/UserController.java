package com.shoter.ylper.api.Users;

import com.shoter.ylper.api.Bookings.BookingModel;
import com.shoter.ylper.api.Bookings.DemandModel;
import com.shoter.ylper.api.Common.ControllerBase;
import com.shoter.ylper.api.Common.EntityFactory;
import com.shoter.ylper.api.Common.ErrorResponse;
import com.shoter.ylper.api.Users.Models.UserModel;
import com.shoter.ylper.core.Bookings.Booking;
import com.shoter.ylper.core.Demands.Demand;
import com.shoter.ylper.core.Results.MethodResult;
import com.shoter.ylper.core.Users.User;
import com.shoter.ylper.core.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.springframework.http.ResponseEntity.status;

@Controller
@RequestMapping( value = "/users")
public class UserController extends ControllerBase {

    private UserService userService;
    private EntityFactory entityFactory;

    @Autowired
    public UserController(UserService userService, EntityFactory entityFactory, Validator validator)
    {
        super(validator);
       this.userService = userService;
       this.entityFactory = entityFactory;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<UserModel> getUsers()
    {
        List<User> users = userService.getUsers();

        List<UserModel> returnVal = new ArrayList<UserModel>(users.size());

        for(User u : users)
            returnVal.add(new UserModel(u));

        return returnVal;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public @ResponseBody ResponseEntity<UserModel> getUser(@PathVariable long userId)
    {
        User user = userService.getUser(userId);

        if(user == null)
            return status(HttpStatus.NOT_FOUND).body(null);

        return status(200).body(new UserModel(user));
    }

    @GetMapping("/{userId}/demands")
    public @ResponseBody ResponseEntity<List<DemandModel>> getDemandsForUser(@PathVariable long userId)
    {
        // TODO: In future here would be also paging.
        if(userService.exists(userId) == false)
            return response(404, null);

        Collection<Demand> demands = userService.getDemands(userId);

        List<DemandModel> demandModels = new ArrayList<DemandModel>(demands.size());

        for(Demand d : demands)
            demandModels.add(new DemandModel(d));

        return ResponseEntity.ok(demandModels);
    }

    @GetMapping("/{userId}/bookings")
    public @ResponseBody ResponseEntity<List<BookingModel>> getBookingsForUser(@PathVariable long userId)
    {
        // TODO: In future here would be also paging.
        if(userService.exists(userId) == false)
            return response(404, null);

        Collection<Booking> bookings = userService.getBookings(userId);

        List<BookingModel> bookingModels = new ArrayList<BookingModel>(bookings.size());

        for(Booking d : bookings)
            bookingModels.add(new BookingModel(d));

        return ResponseEntity.ok(bookingModels);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody ResponseEntity createUser(@RequestBody UserModel userModel)
    {
        Set<ConstraintViolation<UserModel>> violations = validator.validate(userModel);
        if(violations.isEmpty() == false)
        {
            return response400(violations);
        }

        User user = entityFactory.create(User.class, userModel);
        MethodResult result = userService.canAddUser(user);

        if(result.isFailure())
        {
            return status(400).body(new ErrorResponse(result));
        }

        userService.addUser(user);

        return status(200).body(new UserModel(user));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    public @ResponseBody ResponseEntity deleteUser(@PathVariable long userId)
    {
        User user = userService.getUser(userId);

        MethodResult result = userService.canRemoveUser(user);

        if(result.isFailure())
        {
            return status(400).body(new ErrorResponse(result));
        }

        userService.removeUser(user);

        return status(200).body(null);
    }

}
