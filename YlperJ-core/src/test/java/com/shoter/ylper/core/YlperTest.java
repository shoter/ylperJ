package com.shoter.ylper.core;

import org.junit.jupiter.api.BeforeEach;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;

public class YlperTest {
    protected GeometryFactory geometryFactory;

    @BeforeEach
    public void beforeEachTest() {
        geometryFactory = new GeometryFactory(new PrecisionModel(), 0);
    }
}
