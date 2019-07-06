Use YlperJ;

Create Table Users (
    Id BIGINT unsigned NOT NULL AUTO_INCREMENT,
    Username varchar(50) NOT NULL,
    Name varchar(50) NOT NULL,
    GenderId tinyint unsigned NOT NULL,
    Age tinyint unsigned NOT NULL,

    PRIMARY KEY(Id),
    FOREIGN KEY(GenderId) REFERENCES Genders(Id)
);