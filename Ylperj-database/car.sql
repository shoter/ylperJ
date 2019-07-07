Use YlperJ;

CREATE TABLE Cars(
    Id BIGINT  NOT NULL AUTO_INCREMENT,
    CarModelId INT  NOT NULL,
    CreateDate DATETIME,

    PRIMARY KEY(Id),
    FOREIGN KEY(CarModelId) REFERENCES CarModels(Id)
);



