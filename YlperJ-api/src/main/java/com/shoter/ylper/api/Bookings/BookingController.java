package com.shoter.ylper.api.Bookings;

import com.shoter.ylper.api.Common.ControllerBase;
import com.shoter.ylper.api.Common.EntityFactory;
import com.shoter.ylper.api.Users.Models.UserModel;
import com.shoter.ylper.core.Bookings.Booking;
import com.shoter.ylper.core.Bookings.BookingService;
import com.shoter.ylper.core.Bookings.FindCarResult;
import com.shoter.ylper.core.Demands.Demand;
import com.shoter.ylper.core.Demands.DemandService;
import com.shoter.ylper.core.Results.MethodResult;
import org.geolatte.geom.Geometry;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/bookings")
public class BookingController extends ControllerBase {
    private EntityFactory entityFactory;
    private BookingService bookingService;
    private DemandService demandService;

    public BookingController(Validator validator, EntityFactory entityFactory, BookingService bookingService, DemandService demandService) {
        super(validator);
        this.entityFactory = entityFactory;
        this.demandService = demandService;
        this.bookingService =bookingService;
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity<BookingModel> createBooking(@RequestBody BookingModel model) throws ParseException {
        Set<ConstraintViolation<BookingModel>> violations = validator.validate(model);
        if(violations.isEmpty() == false)
        {
            return response400(violations);
        }

        Booking booking = entityFactory.create(Booking.class, model);

        Demand demand = entityFactory.create(Demand.class, booking);
        if(demandService.canAdd(demand).isSuccess())
            demandService.add(demand);

        MethodResult result = bookingService.canAdd(booking);

        if(result.isFailure())
            return response400(result);

        bookingService.add(booking);

        return ResponseEntity.ok(new BookingModel(bookingService.getBooking(booking.getId())));
    }

    @PostMapping("/find")
    public @ResponseBody ResponseEntity<List<FindCarResultModel>> findCar(@RequestBody FindCarRequest request) throws ParseException {
        Set<ConstraintViolation<FindCarRequest>> violations = validator.validate(request);
        if(violations.isEmpty() == false)
        {
            return response400(violations);
        }

        GeometryFactory gf = new GeometryFactory(new PrecisionModel(), 0);
        Point p = gf.createPoint(new Coordinate(request.getSearchX(), request.getSearchY()));

        List<FindCarResult> result = bookingService.findProperCar(
                request.getParsedStartTime(), request.getParsedEndTime(),
                request.getLuxuryCategoryId(),
                request.getCarFeatureIds(),
               p
        );

        List<FindCarResultModel> ret = new ArrayList<FindCarResultModel>(result.size());

        for(FindCarResult r : result)
            ret.add(new FindCarResultModel(r));

        String a = "";

        a += request.getStartTime().toString() + " - " + request.getEndTime().toString();

        return response(200, a);
    }



}
