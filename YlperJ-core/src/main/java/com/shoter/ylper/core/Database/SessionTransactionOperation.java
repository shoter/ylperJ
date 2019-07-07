package com.shoter.ylper.core.Database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SessionTransactionOperation extends SessionOperation {
    public SessionTransactionOperation(Session session) {
        super(session);
    }

    @Override
    protected void Start() {
        super.Start();
        session.beginTransaction();
    }

    @Override
    protected void End() {
        session.getTransaction().commit();
        super.End();
    }
}
