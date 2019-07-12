package com.shoter.ylper.api.Common;

import com.shoter.ylper.api.Bookings.BookingModel;
import com.shoter.ylper.api.Cars.CarCreateModel;
import com.shoter.ylper.api.Cars.CarFeatureModel;
import com.shoter.ylper.api.Bookings.DemandModel;
import com.shoter.ylper.api.Users.Models.UserModel;
import com.shoter.ylper.core.Bookings.Booking;
import com.shoter.ylper.core.Cars.Car;
import com.shoter.ylper.core.Cars.CarFeature;
import com.shoter.ylper.core.Cars.CarModel;
import com.shoter.ylper.core.Demands.Demand;
import com.shoter.ylper.core.Users.Gender;
import com.shoter.ylper.core.Users.User;
import org.hibernate.Session;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.print.Book;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class EntityFactory {
    private Session session;
    private GeometryFactory geometryFactory;

    @Autowired
    public EntityFactory(Session session)
    {
        this.session = session;
        this.geometryFactory = new GeometryFactory(new PrecisionModel(), 0);
    }

    // it will be easier to understand what entity class we want to create by specifying class of the class that we want to create.
    // It shoud also offer better code-completion experience.
    public User create(Class<User> userClass, UserModel model) throws ParseException {
        Gender gender = session.load(Gender.class, model.getGender());

        User user = new User();
        user.setBirthDay(model.getParsedBirthday());
        user.setName(model.getName());
        user.setUsername(model.getUsername());
        user.setId(model.getId());
        user.setGender(gender);
        user.setCreateDate(new Date());

        return user;
    }

    public Car create(Class<Car> carClass, CarCreateModel model)
    {
        Car car = new Car();
        car.setCreateDate(new Date());
        car.setCarModel(session.load(CarModel.class, model.getCarModelId()));

        if(model.getCarFeaturesIds() != null) {
            Set<CarFeature> carFeatures = new HashSet<CarFeature>();
            for (int featureId : model.getCarFeaturesIds()) {
                carFeatures.add(session.load(CarFeature.class, featureId));
            }
            car.setCarFeatures(carFeatures);
        }

        return car;
    }

    public Demand create(Class<Demand> demandClass, DemandModel model) throws ParseException {
        Demand demand = new Demand();
        demand.setUser(session.load(User.class, model.getUserId()));
        demand.setDesiredDropDateTime(model.getParsedEndDate());
        demand.setDesiredStartDateTime(model.getParsedStartDate());
        demand.setDesiredPickupLocation(geometryFactory.createPoint(new Coordinate(model.getDesiredPickupLocationX(), model.getDesiredPickupLocationY())));
        demand.setDesiredDropLocation(geometryFactory.createPoint(new Coordinate(model.getDesiredDropLocationX(), model.getDesiredDropLocationY())));

        if(model.getDesiredCarFeatures() != null)
        {
            Set<CarFeature> carFeatures = new HashSet<CarFeature>();
            for (CarFeatureModel f : model.getDesiredCarFeatures()) {
                carFeatures.add(session.load(CarFeature.class, f.getId()));
            }
            demand.setDesiredCarFeatures(carFeatures);
        }

        return demand;
    }

    public Booking create(Class<Booking> bookingClass, BookingModel model) throws ParseException {
        Booking booking = new Booking();
        booking.setPickupPosition(geometryFactory.createPoint(new Coordinate(model.getStartPositionX(), model.getStartPositionY())));
        booking.setDropPosition(geometryFactory.createPoint(new Coordinate(model.getEndPositionX(), model.getEndPositionY())));
        booking.setUser(session.load(User.class, model.getUserId()));
        booking.setCar(session.load(Car.class, model.getCarId()));
        booking.setStartDateTime(model.getParsedStartDate());
        booking.setEndDateTime(model.getParsedEndDate());

        return booking;
    }

    public Demand create(Class<Demand> demandClass, Booking booking)
    {
        Demand demand = new Demand();
        demand.setUser(booking.getUser());
        demand.setDesiredDropLocation(booking.getDropPosition());
        demand.setDesiredPickupLocation(booking.getPickupPosition());
        demand.setDesiredStartDateTime(booking.getStartDateTime());
        demand.setDesiredDropDateTime(booking.getEndDateTime());

        if(booking.getCar().getCarFeatures() != null)
        {
            Set<CarFeature> features = new HashSet<CarFeature>();
            for(CarFeature f : booking.getCar().getCarFeatures())
            {
                features.add(f);
            }
            demand.setDesiredCarFeatures(features);
        }

        return demand;
    }

}
