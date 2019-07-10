package com.shoter.ylper.core.Demands;

import com.shoter.ylper.core.RepositoryBase;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class DemandRepositoryImpl extends RepositoryBase<Demand> implements  DemandRepository {
    public DemandRepositoryImpl(Session session) {
        super(session);
    }

    public boolean exists(long demandId) {
        Query query = session.createQuery("select 1 from Demand demand where demand.id=:demandId");
        query.setParameter("demandId", demandId);

        return query.uniqueResult() != null;
    }
}
