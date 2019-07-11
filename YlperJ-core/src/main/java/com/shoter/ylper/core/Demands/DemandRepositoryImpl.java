package com.shoter.ylper.core.Demands;

import com.shoter.ylper.core.Cars.Car;
import com.shoter.ylper.core.RepositoryBase;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class DemandRepositoryImpl extends RepositoryBase<Demand> implements  DemandRepository {
    public DemandRepositoryImpl(Session session) {
        super(session);
    }

    public Demand getDemand(long demandId) {
        Query query = session
                .createQuery("Select demand from Demand demand " +
                        "LEFT JOIN FETCH demand.desiredCarFeatures " +
                        "WHERE demand.id=:demandId", Demand.class);
        query.setParameter("demandId", demandId);

        return (Demand) query.getSingleResult();
    }

    public boolean exists(long demandId) {
        Query query = session.createQuery("select 1 from Demand demand where demand.id=:demandId");
        query.setParameter("demandId", demandId);

        return query.uniqueResult() != null;
    }

    public void remove(long demandId) {
        Demand demand = session.load(Demand.class, demandId);
        super.remove(demand);
    }

    public List<Demand> getDemandsForUser(long userId) {
        Query query = session
                .createQuery("Select demand from Demand demand " +
                        "LEFT JOIN FETCH demand.desiredCarFeatures " +
                        "WHERE demand.user.id=:userId", Demand.class);
        query.setParameter("userId", userId);

        return query.list();
    }
}
