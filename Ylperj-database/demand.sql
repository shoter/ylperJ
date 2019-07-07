USE YlperJ;

CREATE TABLE Demands (
    Id BIGINT NOT NULL AUTO_INCREMENT,
    UserId BIGINT NOT NULL,
    DesiredPickupLocation POINT NOT NULL,
    DesiredStartDateTime DATETIME NOT NULL,
    DesiredDropLocation POINT NOT NULL,
    DesiredDropDateTime DATETIME NOT NULL,

    PRIMARY KEY(Id),
    FOREIGN KEY(UserId) REFERENCES Users(Id)
);

CREATE TABLE DemandFeatures (
    DemandId BIGINT NOT NULL,
    FeatureId INT NOT NULL,

    Primary KEY(DemandId, FeatureId),
    FOREIGN KEY(DemandId) REFERENCES Demands(Id),
    FOREIGN KEY(FeatureId) REFERENCES CarFeatures(Id)
);