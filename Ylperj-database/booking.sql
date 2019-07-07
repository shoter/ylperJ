USE YlperJ;

CREATE TABLE Bookings (
    Id BIGINT  NOT NULL AUTO_INCREMENT,
    UserId BIGINT  NOT NULL,
    CarId BIGINT  NOT NULL,
    StartDateTime DATETIME NOT NULL,
    PickupPosition POINT NOT NULL,
    EndDateTime DATETIME,
    DropPosition POINT,

    PRIMARY KEY(Id),
    FOREIGN KEY(UserId) REFERENCES Users(Id),
    FOREIGN KEY(CarId) REFERENCES Cars(Id)
)