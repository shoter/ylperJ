package com.shoter.ylper.api.Cars;

import com.shoter.ylper.core.Cars.*;
import com.shoter.ylper.core.Cars.CarModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarInnersController {

    private CarInnersRepository carInnersRepository;

    public CarInnersController(CarInnersRepository carInnersRepository)
    {
        this.carInnersRepository = carInnersRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/carModels")
    public @ResponseBody List<CarModelModel> getModels()
    {
        List<CarModel> models = carInnersRepository.getModels();

        List<CarModelModel> retVal = new ArrayList<CarModelModel>(models.size());

        for(CarModel m : models)
        {
            retVal.add(new CarModelModel(m));
        }

        return retVal;
    }

    @GetMapping("/engines")
    public @ResponseBody List<EngineModel> getEngines()
    {
        List<Engine> engines = carInnersRepository.getEngines();
        List<EngineModel> engineModels = new ArrayList<EngineModel>(engines.size());

        for(Engine e : engines)
            engineModels.add(new EngineModel(e));

        return engineModels;
    }

    @GetMapping("/luxuryCategories")
    public @ResponseBody List<CarLuxuryCategoryModel> getCarLuxuries()
    {
        List<CarLuxuryCategory> luxuries = carInnersRepository.getLuxuryCategories();
        List<CarLuxuryCategoryModel> retVal = new ArrayList<CarLuxuryCategoryModel>(luxuries.size());

        for(CarLuxuryCategory l : luxuries)
            retVal.add(new CarLuxuryCategoryModel(l));

        return retVal;
    }

    @GetMapping("/carCompanies")
            public @ResponseBody List<CarCompanyModel> getCarCompanies()
    {
        List<CarCompany> companies = carInnersRepository.getCarCompanies();
        List<CarCompanyModel> companyModels = new ArrayList<CarCompanyModel>(companies.size());

        for(CarCompany c : companies)
            companyModels.add(new CarCompanyModel(c));

        return companyModels;
    }
}
