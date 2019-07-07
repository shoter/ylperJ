Use YlperJ;

CREATE TABLE CarCompanies (
    Id INT  NOT NULL,
    Name VARCHAR(100) NOT NULL,

    PRIMARY KEY(Id)
);

INSERT INTO CarCompanies(Id, Name) VALUES
(1, 'Skoda'),
(2, 'Volvo'),
(3, 'Toyota'),
(4, 'Fiat');