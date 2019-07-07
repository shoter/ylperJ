Use YlperJ;

CREATE TABLE Engines (
    Id INT  NOT NULL,
    FuelTypeId TINYINT  NOT NULL,
    Power INT UNSIGNED NOT NULL,
    Name VARCHAR(50) NOT NULL,

    PRIMARY KEY(Id),
    FOREIGN KEY(FuelTypeId) REFERENCES FuelTypes(Id)
);

INSERT INTO Engines(Id, Name, FuelTypeId, Power) VALUES
(1, 'Generic Diesel Engine', 1, 120),
(2, 'Generic Gasoline Engine', 2, 110),
(3, 'Generic Electric Engine', 3, 100);