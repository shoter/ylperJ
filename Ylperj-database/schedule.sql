USE YlperJ;

CREATE TABLE Schedules (
    Id BIGINT  NOT NULL AUTO_INCREMENT,
    UserId BIGINT  NOT NULL,
    CarId BIGINT  NOT NULL,
    DesiredStartDateTime DATETIME NOT NULL,
    DesiredPickupPosition POINT NOT NULL,
    DesiredEndDateTime DATETIME,
    DesiredDropPosition POINT,

    PRIMARY KEY(Id),
    FOREIGN KEY(UserId) REFERENCES Users(Id),
    FOREIGN KEY(CarId) REFERENCES Cars(Id)
)