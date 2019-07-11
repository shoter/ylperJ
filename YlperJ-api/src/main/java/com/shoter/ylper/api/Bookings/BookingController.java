package com.shoter.ylper.api.Bookings;

import com.shoter.ylper.api.Common.ControllerBase;
import com.shoter.ylper.api.Common.EntityFactory;
import com.shoter.ylper.api.Users.Models.UserModel;
import com.shoter.ylper.core.Bookings.Booking;
import com.shoter.ylper.core.Bookings.BookingService;
import com.shoter.ylper.core.Results.MethodResult;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Controller
@RequestMapping(value = "/bookings")
public class BookingController extends ControllerBase {
    private EntityFactory entityFactory;
    private BookingService bookingService;

    public BookingController(Validator validator, EntityFactory entityFactory, BookingService bookingService) {
        super(validator);
        this.entityFactory = entityFactory;
        this.bookingService =bookingService;
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity<BookingModel> createBooking(@RequestBody BookingModel model)
    {
        Set<ConstraintViolation<BookingModel>> violations = validator.validate(model);
        if(violations.isEmpty() == false)
        {
            return response400(violations);
        }

        Booking booking = entityFactory.create(Booking.class, model);

        MethodResult result = bookingService.canAdd(booking);

        if(result.isFailure())
            return response400(result);

        bookingService.add(booking);

        return ResponseEntity.ok(new BookingModel(bookingService.getBooking(booking.getId())));

    }

}
