Use YlperJ;

Create Table Users (
    Id BIGINT  NOT NULL AUTO_INCREMENT,
    Username varchar(50) NOT NULL,
    Name varchar(50) NOT NULL,
    GenderId tinyint  NOT NULL,
    Age tinyint unsigned NOT NULL,
    CreateDate DATETIME,

    PRIMARY KEY(Id),
    FOREIGN KEY(GenderId) REFERENCES Genders(Id)
);