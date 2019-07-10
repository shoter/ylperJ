package com.shoter.ylper.api.Common;

import com.shoter.ylper.api.Cars.CarCreateModel;
import com.shoter.ylper.api.Cars.CarShortModel;
import com.shoter.ylper.api.Users.Models.UserModel;
import com.shoter.ylper.core.Cars.Car;
import com.shoter.ylper.core.Cars.CarFeature;
import com.shoter.ylper.core.Cars.CarModel;
import com.shoter.ylper.core.Users.Gender;
import com.shoter.ylper.core.Users.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class EntityFactory {
    private Session session;

    @Autowired
    public EntityFactory(Session session)
    {
        this.session = session;
    }

    // it will be easier to understand what entity class we want to create by specifying class of the class that we want to create.
    public User create(Class<User> userClass, UserModel model)
    {
        Gender gender = session.load(Gender.class, model.getGender());

        User user = new User();
        user.setBirthDay(model.getBirthday());
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

        Set<CarFeature> carFeatures = new HashSet<CarFeature>();
        for(int featureId : model.getCarFeaturesIds())
        {
            carFeatures.add(session.load(CarFeature.class, featureId));
        }
        car.setCarFeatures(carFeatures);

        return car;

    }

}