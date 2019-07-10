package com.shoter.ylper.api.Cars;

import com.shoter.ylper.api.Common.ControllerBase;
import com.shoter.ylper.api.Common.EntityFactory;
import com.shoter.ylper.core.Cars.Car;
import com.shoter.ylper.core.Cars.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;

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
        return ResponseEntity.status(200).body(new CarModel(car));
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<CarModel> createCar(@RequestBody CarCreateModel model, BindingResult bindingResult)
    {
        // TODO: Spring has some capability to have automatic validation out of the box. Try to find out how in future.
        if(bindingResult.hasErrors())
        {
            return response400(bindingResult);
        }

        Car car = entityFactory.create(Car.class, model);
        return  null;
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
