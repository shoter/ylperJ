Use YlperJ;

Create Table CarModels (
    Id INT  NOT NULL AUTO_INCREMENT,
    DesignerCompanyId INT  NOT NULL,
    EngineId INT  NOT NULL,
    LuxuryCategoryId TINYINT  NOT NULL,
    Name VARCHAR(100) NOT NULL,
    FuelConsumption DECIMAL(5,2) NOT NULL,

    PRIMARY KEY(Id),
    FOREIGN KEY(DesignerCompanyId) REFERENCES CarCompanies(Id),
    FOREIGN KEY(EngineId) REFERENCES Engines(Id),
    FOREIGN KEY(LuxuryCategoryId) REFERENCES CarLuxuryCategories(Id)
);

INSERT INTO CarModels(DesignerCompanyId, EngineId, LuxuryCategoryId, Name, FuelConsumption) VALUES
(1,1,1,'Cheap Skoda Diesel car',102),
(2,1,1,'Cheap Volvo Diesel car',103),
(3,1,1,'Cheap Toyota Diesel car',104),
(4,1,1,'Cheap Fiat Diesel car',105),
(1,1,2,'Standard Skoda Diesel car',103),
(2,1,2,'Standard Volvo Diesel car',105),
(3,1,2,'Standard Toyota Diesel car',107),
(4,1,2,'Standard Fiat Diesel car',109),
(1,1,3,'Luxurious Skoda Diesel car',104),
(2,1,3,'Luxurious Volvo Diesel car',107),
(3,1,3,'Luxurious Toyota Diesel car',110),
(4,1,3,'Luxurious Fiat Diesel car',113),
(1,2,1,'Cheap Skoda Gasoline car',104),
(2,2,1,'Cheap Volvo Gasoline car',106),
(3,2,1,'Cheap Toyota Gasoline car',108),
(4,2,1,'Cheap Fiat Gasoline car',110),
(1,2,2,'Standard Skoda Gasoline car',106),
(2,2,2,'Standard Volvo Gasoline car',110),
(3,2,2,'Standard Toyota Gasoline car',114),
(4,2,2,'Standard Fiat Gasoline car',118),
(1,2,3,'Luxurious Skoda Gasoline car',108),
(2,2,3,'Luxurious Volvo Gasoline car',114),
(3,2,3,'Luxurious Toyota Gasoline car',120),
(4,2,3,'Luxurious Fiat Gasoline car',126),
(1,3,1,'Cheap Skoda Electric car',106),
(2,3,1,'Cheap Volvo Electric car',109),
(3,3,1,'Cheap Toyota Electric car',112),
(4,3,1,'Cheap Fiat Electric car',115),
(1,3,2,'Standard Skoda Electric car',109),
(2,3,2,'Standard Volvo Electric car',115),
(3,3,2,'Standard Toyota Electric car',121),
(4,3,2,'Standard Fiat Electric car',127),
(1,3,3,'Luxurious Skoda Electric car',112),
(2,3,3,'Luxurious Volvo Electric car',121),
(3,3,3,'Luxurious Toyota Electric car',130),
(4,3,3,'Luxurious Fiat Electric car',139);