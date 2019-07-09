package com.shoter.ylper.core.Bookings;

import com.shoter.ylper.core.RepositoryBase;
import org.hibernate.Session;

public class DemandRepositoryImpl extends RepositoryBase<Demand> implements  DemandRepository {
    public DemandRepositoryImpl(Session session) {
        super(session);
    }
}
