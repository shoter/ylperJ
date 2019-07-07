package com.shoter.ylper.core.Database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SessionOperation {

    protected Session session;
    public SessionOperation(Session session) {
        this.session = session;
    }

    protected void Start()
    {
    }

    protected void Execute()
    {

    }

    public final void Run()
    {
        Start();
        Execute();
        End();
    }

    protected void End() {
    }
}
