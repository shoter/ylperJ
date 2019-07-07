USE YlperJ;

CREATE TABLE CarAssignedFeatures (
    CarId BIGINT  NOT NULL,
    CarFeatureId INT  NOT NULL,

    PRIMARY KEY(CarId, CarFeatureId),
    FOREIGN KEY(CarId) REFERENCES Cars(Id),
    FOREIGN KEY(CarFeatureId) REFERENCES CarFeatures(Id)
);

