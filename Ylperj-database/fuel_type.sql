Use YlperJ;

CREATE TABLE FuelTypes (
    Id TINYINT  NOT NULL,
    Name VARCHAR(40) NOT NULL,

    PRIMARY KEY(Id)
);

INSERT INTO FuelTypes VALUES
(1, 'Diesel'),
(2, 'Gasoline'),
(3, 'Electric')