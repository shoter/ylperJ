package com.shoter.ylper.core.Demands;

import com.shoter.ylper.core.Cars.CarFeature;
import com.shoter.ylper.core.Repository;

import java.util.List;

public interface DemandRepository extends Repository<Demand> {
    Demand getDemand(long demandId);
    boolean exists(long demandId);
    void remove(long demandId);
    List<Demand> getDemandsForUser(long userId);
    List<CarFeature> getFeaturesForDemand(long demandId);
}
