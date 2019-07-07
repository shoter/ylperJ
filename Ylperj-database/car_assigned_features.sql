USE YlperJ;

CREATE TABLE CarAssignedFeatures (
    CarId BIGINT UNSIGNED NOT NULL,
    CarFeatureId INT UNSIGNED NOT NULL,

    PRIMARY KEY(CarId, CarFeatureId),
    FOREIGN KEY(CarId) REFERENCES Cars(Id),
    FOREIGN KEY(CarFeatureId) REFERENCES CarFeatures(Id)
);

