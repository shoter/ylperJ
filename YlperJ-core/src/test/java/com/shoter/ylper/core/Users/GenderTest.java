package com.shoter.ylper.core.Users;

import com.shoter.ylper.core.IntegrationTest;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;


public class GenderTest extends IntegrationTest {
    @Test
    public void database_shouldcontain_threegenders()
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Gender> result = session.createQuery("from Gender").list();
        assertEquals(3, result.size());
    }
}
