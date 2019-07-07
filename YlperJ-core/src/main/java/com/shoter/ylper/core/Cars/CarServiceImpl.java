package com.shoter.ylper.core.Cars;

import com.shoter.ylper.core.Database.SessionOperation;
import com.shoter.ylper.core.Results.MethodResult;
import com.shoter.ylper.core.ServiceBase;
import com.shoter.ylper.core.Users.User;
import org.hibernate.Session;

public class CarServiceImpl extends ServiceBase implements CarService {
    protected CarServiceImpl(Session session) {
        super(session);
    }

    public MethodResult canAddCar(Car car) {
        return null;
    }

    public void addCar(Car car) {

    }

    public MethodResult canRemoveCar(Car car) {
        return null;
    }

    public void removeCar(Car car) {

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
