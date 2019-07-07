Use YlperJ;

CREATE TABLE CarFeatures (
    Id INT  NOT NULL,
    Name VARCHAR(100) NOT NULL,

    PRIMARY KEY(Id)
);

INSERT INTO CarFeatures(Id, Name) VALUES
(1, 'Radio'),
(2, 'Cb Radio'),
(3, 'Built-in GPS'),
(4, 'Leather seats'),
(5, 'Cloth seats');