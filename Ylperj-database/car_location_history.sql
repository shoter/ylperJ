USE YlperJ;

CREATE TABLE CarLocationHistories (
    CarId BIGINT  NOT NULL,
    DateTime DATETIME NOT NULL,
    Location POINT NOT NULL,

    FOREIGN KEY(CarId) REFERENCES Cars(Id),
    Index(CarId, DateTime)
)