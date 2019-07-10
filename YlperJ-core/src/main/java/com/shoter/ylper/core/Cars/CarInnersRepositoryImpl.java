package com.shoter.ylper.core.Cars;

import org.hibernate.Session;

import java.util.List;

public class CarInnersRepositoryImpl implements  CarInnersRepository {
    private Session session;
    public CarInnersRepositoryImpl(Session session)
    {
        this.session = session;
    }
    public List<CarModel> getModels() {
        return session.createQuery("from CarModel model " +
                "join fetch model.engine engine " +
                "join fetch engine.fuelType " +
                "join fetch model.luxuryCategory " +
                "join fetch model.designerCompany", CarModel.class).list();
    }

    public List<Engine> getEngines( ) {
        return session.createQuery("from Engine engine " +
                "join fetch engine.fuelType", Engine.class).list();
    }

    public List<CarLuxuryCategory> getLuxuryCategories() {
        return session.createQuery("from CarLuxuryCategory", CarLuxuryCategory.class).list();
    }

    public List<CarCompany> getCarCompanies() {
        return session.createQuery("from CarCompany", CarCompany.class).list();
    }
}
