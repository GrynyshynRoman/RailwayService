CREATE DATABASE IF NOT EXISTS RAILWAY;
USE RAILWAY;
CREATE TABLE STATIONS (
  station_ID INT NOT NULL AUTO_INCREMENT,
  name       VARCHAR(50),
  city       VARCHAR(50),
  state      VARCHAR(50),
  country    VARCHAR(50),
  PRIMARY KEY (station_ID)
);
CREATE TABLE ROUTES (
  route_ID         INT NOT NULL AUTO_INCREMENT,
  departStation_ID INT NOT NULL,
  departTime       DATETIME,
  destStation_ID   INT NOT NULL,
  destTime         DATETIME,
  PRIMARY KEY (route_ID),
  FOREIGN KEY (departStation_ID) REFERENCES STATIONS (station_ID),
  FOREIGN KEY (destStation_ID) REFERENCES STATIONS (station_ID)
);

CREATE TABLE WAY_STATIONS (
  wayStation_ID INT NOT NULL AUTO_INCREMENT,
  route_ID      INT NOT NULL,
  station_ID    INT NOT NULL,
  arrival_Time  DATETIME,
  depart_Time   DATETIME,
  waiting_Time  TIME,
  PRIMARY KEY (wayStation_ID),
  FOREIGN KEY (route_ID) REFERENCES routes (route_ID),
  FOREIGN KEY (station_ID) REFERENCES stations (station_ID)
);

CREATE TABLE TRAINS (
  train_ID INT NOT NULL AUTO_INCREMENT,
  route_ID INT NOT NULL,
  PRIMARY KEY (train_ID),
  FOREIGN KEY (route_ID) REFERENCES routes (route_ID)
);

CREATE TABLE CARRIAGES (
  carriage_ID    INT NOT NULL AUTO_INCREMENT,
  train_ID       INT NOT NULL,
  carriageNumber INT NOT NULL,
  type           ENUM ('common', 'reservedSeat', 'coupe'),
  totalSeats     INT NOT NULL,
  reservedSeats  INT NOT NULL,
  PRIMARY KEY (carriage_ID),
  FOREIGN KEY (train_ID) REFERENCES trains(train_ID)
);

CREATE TABLE USERS(
  user_ID INT NOT NULL AUTO_INCREMENT,
  login VARCHAR(50) NOT NULL UNIQUE ,
  firstName VARCHAR(50) NOT NULL ,
  lastName VARCHAR(50) NOT NULL ,
  password VARCHAR(50) NOT NULL ,
  PRIMARY KEY (user_ID)

);



SELECT *
FROM RAILWAY.ROUTES;

SELECT *
FROM way_stations;

SELECT *
FROM trains;

SELECT * FROM carriages;

SELECT *
FROM users;
DROP TABLE users;