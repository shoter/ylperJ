package com.shoter.ylper.core.Users;

import com.shoter.ylper.core.IntegrationTest;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GenderTest extends IntegrationTest {
    @Test
    public void database_shouldcontain_threegenders()
    {
        session.beginTransaction();

        List<Gender> result = session.createQuery("from Gender").list();
        assertEquals(3, result.size());
    }
}
