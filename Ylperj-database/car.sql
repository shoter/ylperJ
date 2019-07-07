Use YlperJ;

CREATE TABLE Cars(
    Id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    CarModelId INT UNSIGNED NOT NULL,
    CreateDate DATETIME,

    PRIMARY KEY(Id),
    FOREIGN KEY(CarModelId) REFERENCES CarModels(Id)
);



