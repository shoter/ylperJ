package com.shoter.ylper.core.Cars;

import com.shoter.ylper.core.Cars.CarLuxuryCategory;
import com.shoter.ylper.core.Cars.CarModel;
import com.shoter.ylper.core.Cars.Engine;

import java.util.List;

public interface CarInnersRepository {
    List<CarModel> getModels();
    List<Engine> getEngines();
    List<CarLuxuryCategory> getLuxuryCategories();
    List<CarCompany> getCarCompanies();
}
