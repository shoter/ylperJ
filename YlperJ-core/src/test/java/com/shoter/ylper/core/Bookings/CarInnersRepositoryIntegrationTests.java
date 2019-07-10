package com.shoter.ylper.core.Bookings;

import com.shoter.ylper.core.Cars.CarInnersRepository;
import com.shoter.ylper.core.Cars.CarInnersRepositoryImpl;
import com.shoter.ylper.core.IntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CarInnersRepositoryIntegrationTests extends IntegrationTest {
    private CarInnersRepository carInnersRepository;

    @Override
    @BeforeEach
    public void beforeEachTest() {
        super.beforeEachTest();

        carInnersRepository = new CarInnersRepositoryImpl(session);
    }

    @Test
    public void getModels_smokeTest() {
        carInnersRepository.getModels();
    }

    @Test
    public void getEngines_smokeTest() {
        carInnersRepository.getEngines();
    }

    @Test
    public void getLuxuryCategories_smokeTest() {
        carInnersRepository.getLuxuryCategories();
    }
}
