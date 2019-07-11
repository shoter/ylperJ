package com.shoter.ylper.api.Cars;

import com.shoter.ylper.api.Common.ControllerBase;
import com.shoter.ylper.api.Common.EntityFactory;
import com.shoter.ylper.api.Users.Models.UserModel;
import com.shoter.ylper.core.Cars.Car;
import com.shoter.ylper.core.Cars.CarLocationHistory;
import com.shoter.ylper.core.Cars.CarService;
import com.shoter.ylper.core.Results.MethodResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/cars")
public class CarController extends ControllerBase {

    private CarService carService;
    private EntityFactory entityFactory;

    @Autowired
    public CarController(CarService carService, EntityFactory entityFactory, Validator validator)
    {
        super(validator);
        this.carService = carService;
        this.entityFactory = entityFactory;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<CarShortModel> getCars()
    {
        List<Car> cars = carService.getCars();

        List<CarShortModel> returnVal = new ArrayList<CarShortModel>(cars.size());


        for(Car c : cars)
            returnVal.add(new CarShortModel(c));

        return returnVal;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{carId}")
    public @ResponseBody ResponseEntity<CarModel> getCar(@PathVariable long carId)
    {
        Car car = carService.getFullCar(carId);

        if(car == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        CarLocationHistory location = carService.getLastCarPosition(carId);

        return ResponseEntity.status(200).body(new CarModel(car, location));
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<CarModel> createCar(@RequestBody CarCreateModel model, BindingResult bindingResult)
    {
        // TODO: Spring has some capability to have automatic validation out of the box. Try to find out how in future.
        Set<ConstraintViolation<CarCreateModel>> violations = validator.validate(model);
        if(violations.isEmpty() == false)
        {
            return response400(violations);
        }

        Car car = entityFactory.create(Car.class, model);

        MethodResult result = carService.canAddCar(car);
        if(result.isFailure())
        {
            return response400(result);
        }

        carService.addCar(car);
        carService.insertNewCarPosition(car.getId(), model.getX(), model.getY());

        car = carService.getFullCar(car.getId());
        CarLocationHistory location = carService.getLastCarPosition(car.getId());

        return response(200, new CarModel(car, location));
    }

    @PostMapping("/{carId}/locations")
    public ResponseEntity insertPosition(@PathVariable long carId, @RequestBody CarNewLocationModel location)
    {
        Set<ConstraintViolation<CarNewLocationModel>> violations = validator.validate(location);
        if(violations.isEmpty() == false)
        {
            return response400(violations);
        }

        if(carService.exists(carId) == false)
            return response(404, "Car not found!");

        carService.insertNewCarPosition(carId, location.getX(), location.getY());

        return response(200, null);
    }

    @RequestMapping(method= RequestMethod.DELETE, value = "/{carId}")
    public ResponseEntity deleteCar(@PathVariable long carId)
    {
        if(carService.exists(carId) == false)
        {
            return response(404, null);
        }

        carService.removeCar(carId);

        return response(200, null);
    }
}
