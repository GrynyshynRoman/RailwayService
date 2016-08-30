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
  waiting_Time  INT,
  PRIMARY KEY (wayStation_ID),
  FOREIGN KEY (route_ID) REFERENCES routes (route_ID),
  FOREIGN KEY (station_ID) REFERENCES stations (station_ID)
);
DROP TABLE way_stations;

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
  FOREIGN KEY (train_ID) REFERENCES trains (train_ID)
);
;
UPDATE carriages SET reservedSeats=reservedSeats+1 WHERE train_ID=? AND carriageNumber=?;


CREATE TABLE USERS (
  user_ID   INT         NOT NULL AUTO_INCREMENT,
  login     VARCHAR(50) NOT NULL UNIQUE,
  firstName VARCHAR(50) NOT NULL,
  lastName  VARCHAR(50) NOT NULL,
  password  VARCHAR(50) NOT NULL,
  PRIMARY KEY (user_ID)

);
CREATE TABLE ROLES (
  role_ID INT         NOT NULL AUTO_INCREMENT,
  login   VARCHAR(50) NOT NULL UNIQUE,
  role    VARCHAR(20) NOT NULL,
  PRIMARY KEY (role_ID),
  FOREIGN KEY (login) REFERENCES users (login)
);
CREATE TABLE TICKETS (
  ticket_ID      INT      NOT NULL AUTO_INCREMENT,
  user_ID        INT      NOT NULL,
  train_ID       INT      NOT NULL,
  carriageNumber INT      NOT NULL,
  depStation_ID  INT      NOT NULL,
  depTime        DATETIME NOT NULL,
  destStation_ID INT      NOT NULL,
  destTime       DATETIME NOT NULL,
  price          DOUBLE   NOT NULL,
  PRIMARY KEY (ticket_ID),
  FOREIGN KEY (user_ID) REFERENCES users (user_ID),
  FOREIGN KEY (train_ID) REFERENCES trains (train_ID),
  FOREIGN KEY (depStation_ID) REFERENCES stations (station_ID),
  FOREIGN KEY (destStation_ID) REFERENCES stations (station_ID)
);

# -------------------------------------------------Useful queries-----------------------------------------------------


UPDATE roles
SET role = 'admin'
WHERE login = 'admin';

SELECT *
FROM railway.users;

DELETE FROM way_stations
WHERE route_ID = ?;

SELECT *
FROM stations;


SELECT *
FROM RAILWAY.ROUTES;

SELECT *
FROM way_stations;

SELECT *
FROM trains;

SELECT *
FROM carriages;

SELECT *
FROM users;
DROP TABLE users;

SELECT *
FROM routes
  JOIN way_stations ON routes.route_ID = way_stations.route_ID;
SELECT *
FROM stations;
# WHERE ((departStation_ID = ? AND departTime = ?) OR (station_ID=? AND departTime=? ))AND (destStation_ID = ? OR station_ID = ?);

SELECT routes.route_ID
FROM routes
  JOIN way_stations ON routes.route_ID = way_stations.route_ID
WHERE (routes.departStation_ID = 32 OR way_stations.station_ID = 32) AND
      (routes.destStation_ID = 38 OR way_stations.station_ID = 38)
#       AND
#       ((DATE(routes.departTime) BETWEEN 20160827 AND (20160827 + hour(23) + minute(59))) OR
#        (DATE((way_stations.arrival_Time) BETWEEN 20160827 AND (20160827 + hour(23) + minute(59)))));

SELECT routes.route_ID
FROM routes
  JOIN way_stations ON routes.route_ID = way_stations.route_ID
WHERE (routes.departStation_ID = 32 AND routes.destStation_ID = 38) OR
      (routes.departStation_ID = 32 AND way_stations.station_ID = 38) OR
      (way_stations.station_ID = 32 AND way_stations.station_ID = 38) OR
      (way_stations.station_ID = 32 AND routes.destStation_ID = 38);
#       AND
#       ((DATE(routes.departTime) BETWEEN 20160827 AND (20160827 + hour(23) + minute(59))) OR
#        (DATE((way_stations.arrival_Time) BETWEEN 20160827 AND (20160827 + hour(23) + minute(59)))));

SELECT *
FROM routes
  JOIN way_stations ON routes.route_ID = way_stations.route_ID;

SELECT routes.route_ID
FROM routes
  JOIN way_stations ON routes.route_ID = way_stations.route_ID;

SELECT *
FROM trains
  JOIN carriages ON trains.train_ID = carriages.train_ID
WHERE trains.train_ID = ? AND carriages.reservedSeats < carriages.totalSeats;

SELECT *
FROM trains
  JOIN carriages ON trains.train_ID = carriages.train_ID
  JOIN routes ON trains.route_ID = routes.route_ID
  JOIN way_stations ON routes.route_ID = way_stations.route_ID
  JOIN stations ON way_stations.station_ID = stations.station_ID;

SELECT routes.departTime
FROM routes
WHERE routes.route_ID = 15 AND departStation_ID = 30;

SELECT depart_Time
FROM way_stations
WHERE route_ID = 15 AND station_ID = 32;

SELECT *
FROM carriages
WHERE train_ID = ? AND reservedSeats < totalSeats;

SELECT *
FROM way_stations;


INSERT INTO tickets (user_ID, train_ID, carriageNumber, depStation_ID, depTime, destStation_ID, destTime, price)
VALUES (?, ?, ?, ?, ?, ?, ?, ?)

SELECT *
FROM tickets
WHERE ticket_ID = ?

UPDATE tickets
SET ticket_ID = ?, user_ID = ?, train_ID = ?, carriageNumber = ?, depStation_ID = ?, depTime = ?, destStation_ID = ?,
  destTime    = ?, price = ?
WHERE ticket_ID = ?

DELETE FROM tickets
WHERE ticket_ID = ?

SELECT *
FROM tickets;
