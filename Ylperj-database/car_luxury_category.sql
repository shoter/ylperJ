USE YlperJ;

CREATE TABLE CarLuxuryCategories (
    Id TINYINT  NOT NULL,
    Name VARCHAR(50) NOT NULL,

    PRIMARY KEY(Id)
);

INSERT INTO CarLuxuryCategories(Id, Name) VALUES
(1, 'Cheap'),
(2, 'Standard'),
(3, 'Luxurious');