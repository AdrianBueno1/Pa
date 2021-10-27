-- Indexes for primary keys have been explicitly created.

DROP TABLE Sale;
DROP TABLE Session;
DROP TABLE Movie;
DROP TABLE Hall;
DROP TABLE Cinema;
DROP TABLE City;
DROP TABLE User;

CREATE TABLE User (
    id BIGINT NOT NULL AUTO_INCREMENT,
    userName VARCHAR(60) COLLATE latin1_bin NOT NULL,
    password VARCHAR(60) NOT NULL, 
    firstName VARCHAR(60) NOT NULL,
    lastName VARCHAR(60) NOT NULL, 
    email VARCHAR(60) NOT NULL,
    role TINYINT NOT NULL,
    CONSTRAINT UserPK PRIMARY KEY (id),
    CONSTRAINT UserNameUniqueKey UNIQUE (userName)
) ENGINE = InnoDB;

CREATE INDEX UserIndexByUserName ON User (userName);


CREATE TABLE Movie (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(60) NOT NULL,
    summary VARCHAR(2000) NOT NULL,
    duration SMALLINT NOT NULL,
    CONSTRAINT MoviePK PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE INDEX MovieIndexByTitle ON Movie (title);

CREATE TABLE City (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,
    CONSTRAINT CityPK PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE Cinema (
    id BIGINT NOT NULL AUTO_INCREMENT,
    cityId BIGINT NOT NULL,
    name VARCHAR(60) NOT NULL,
    CONSTRAINT CinemaPK PRIMARY KEY (id),
    CONSTRAINT CinemaCityIdFK FOREIGN KEY(cityId)
        REFERENCES City (id)
) ENGINE = InnoDB;

CREATE TABLE Hall (
    id BIGINT NOT NULL AUTO_INCREMENT,
    cinemaId BIGINT NOT NULL,
    name VARCHAR(60) NOT NULL,
    capacity SMALLINT NOT NULL,
    CONSTRAINT HallPK PRIMARY KEY (id),
    CONSTRAINT HallCinemaIdFK FOREIGN KEY(cinemaId)
        REFERENCES Cinema (id)
) ENGINE = InnoDB;


CREATE TABLE Session (
    id BIGINT NOT NULL AUTO_INCREMENT,
    hallId BIGINT NOT NULL,
    movieId BIGINT NOT NULL,
    date DATETIME NOT NULL,
    price DECIMAL(11, 2) NOT NULL,
    availableSeats SMALLINT NOT NULL,
    version BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT SessionPK PRIMARY KEY (id),
    CONSTRAINT SessionHallIdFK FOREIGN KEY(hallId)
        REFERENCES Hall (id),
	CONSTRAINT SessionMovieIdFK FOREIGN KEY(movieId)
        REFERENCES Movie (id)
) ENGINE = InnoDB;


CREATE TABLE Sale (
    id BIGINT NOT NULL AUTO_INCREMENT,
    userId BIGINT NOT NULL,
    sessionId BIGINT NOT NULL,
    seats TINYINT NOT NULL,
    creditcard VARCHAR(16) NOT NULL,
    used BOOLEAN NOT NULL,
	date DATETIME NOT NULL,
    CONSTRAINT SalePK PRIMARY KEY (id),
    CONSTRAINT SaleUserIdFK FOREIGN KEY(userId)
        REFERENCES User (id),
	CONSTRAINT SaleSessionIdFK FOREIGN KEY(sessionId)
        REFERENCES Session (id)
) ENGINE = InnoDB;
