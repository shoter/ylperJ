package com.shoter.ylper.core.Cars;

import com.shoter.ylper.core.Database.SessionOperation;
import com.shoter.ylper.core.Database.SessionTransactionOperation;
import com.shoter.ylper.core.Results.MethodResult;
import com.shoter.ylper.core.ServiceBase;
import com.shoter.ylper.core.Users.Genders;
import com.shoter.ylper.core.Users.User;
import org.hibernate.Session;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class CarServiceImpl extends ServiceBase implements CarService {
    protected CarServiceImpl(Session session) {
        super(session);
    }

    public MethodResult canAddCar(Car car) {
        MethodResult result = new MethodResult();

        // TODO: Check if car model exists for given id
        // TODO: Check if car features exists for given ids

        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        result.addError(violations);

        return result;
    }

    public void addCar(final Car car) {
        new SessionTransactionOperation(session)
        {
            @Override
            protected void Execute() {
                this.session.save(car);
            }
        }.Run();
    }

    public MethodResult canRemoveCar(Car car) {
        //TODO: Check if car exists
        return new MethodResult();
    }

    public void removeCar(final Car car) {
        new SessionTransactionOperation(session)
        {
            @Override
            protected void Execute() {
                this.session.delete(car);
            }
        }.Run();
    }

    public Car getCar(final long id) {
        final Car[] cars = new Car[1];
        new SessionOperation(session)
        {
            @Override
            protected void Execute() {
                cars[0] = (Car) this.session.get(Car.class, id);
            }
        }.Run();

        return cars[0];
    }
}
