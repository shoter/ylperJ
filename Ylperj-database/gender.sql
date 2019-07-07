USE YlperJ;

CREATE TABLE Genders (
    Id TINYINT  NOT NULL PRIMARY KEY,
    Name varchar(15) NOT NULL
);

Insert Into Genders VALUES
(1, "Male"),
(2, "Female"),
(3, "Other");

