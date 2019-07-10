package com.shoter.ylper.core.Demands;

import com.shoter.ylper.core.Demands.*;
import com.shoter.ylper.core.IntegrationTest;
import com.shoter.ylper.core.Results.MethodResult;
import com.shoter.ylper.core.Users.User;
import com.shoter.ylper.core.Users.UserRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class DemandRepositoryIntegrationTest extends IntegrationTest {
    // We should have car and user in test data.
    private final long testUserId = 1;

    private Demand correctDemand;
    private DemandService demandService;

    @Override
    @BeforeEach
    public void beforeEachTest() {
        super.beforeEachTest();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2019);
        cal.set(Calendar.MONTH, 7);
        cal.set(Calendar.DAY_OF_MONTH, 3);

        correctDemand = new Demand();
        correctDemand.setDesiredStartDateTime(cal.getTime());
        cal.set(Calendar.DAY_OF_MONTH, 4);
        correctDemand.setDesiredDropDateTime(cal.getTime());
        correctDemand.setDesiredPickupLocation(geometryFactory.createPoint(new Coordinate(0,0)));
        correctDemand.setDesiredDropLocation(geometryFactory.createPoint(new Coordinate(1,1)));
        correctDemand.setUser(session.load(User.class, testUserId));

        demandService = new DemandServiceImpl(
                new DemandRepositoryImpl(session),
                new UserRepositoryImpl(session));
    }

    @Test
    public void canAdd_shouldReturnSuccess_whenCorrectDemand()
    {
        MethodResult result = demandService.canAdd(correctDemand);
        assertTrue(result.isSuccess());
    }

    @Test void add_smokeTest_whenCorrectDemand()
    {
        demandService.add(correctDemand);
    }

    @Test
    public void canAdd_shouldReturnError_whenUserDoesNotExist()
    {
        correctDemand.setUser(session.load(User.class, (long)-12345));

        MethodResult result = demandService.canAdd(correctDemand);
        assertTrue(result.hasError(DemandErrors.userDoeNotExist));
    }

    @Test
    public void canAdd_shouldReturnError_whenDropTimeBeforeStart()
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2017);
        correctDemand.setDesiredDropDateTime(cal.getTime());

        MethodResult result = demandService.canAdd(correctDemand);

        assertTrue(result.hasError(DemandErrors.dropTimeBeforeStart));
    }

    @Test
    public void canRemove_shouldReturnError_whenDemandDoesNotExist()
    {
        MethodResult result = demandService.canRemove((long)-12345);
        assertTrue(result.hasError(DemandErrors.demandDoesNotExist));
    }

    @Test
    public void canRemove_shouldReturnSuccess_forNewlyAddedDemand()
    {
        demandService.add(correctDemand);
        assertTrue(demandService.canRemove(correctDemand.getId()).isSuccess());
    }
}
