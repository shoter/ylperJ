package com.shoter.ylper.core.Demands;

import com.shoter.ylper.core.Repository;

public interface DemandRepository extends Repository<Demand> {
    boolean exists(long demandId);
}
