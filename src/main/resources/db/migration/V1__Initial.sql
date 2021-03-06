CREATE SCHEMA IF NOT EXISTS PUBLIC;

CREATE TABLE IF NOT EXISTS CLIENT (
  ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  FIRST_NAME VARCHAR(255),
  LAST_NAME VARCHAR(255),
  USER_NAME VARCHAR(255),
  EMAIL VARCHAR(255),
  ADDRESS VARCHAR(255),
  COUNTRY VARCHAR(255)
                  );

INSERT INTO CLIENT (FIRST_NAME, LAST_NAME, USER_NAME, EMAIL, ADDRESS, COUNTRY) VALUES ('John', 'Doe', 'john1', 'john1@gmail.com', 'Tamme st 1', 'EST');
INSERT INTO CLIENT (FIRST_NAME, LAST_NAME, USER_NAME, EMAIL, ADDRESS, COUNTRY) VALUES ('Jane', 'Doe', 'jane', 'jane@gmail.com', 'Koidu st 2', 'EST');
INSERT INTO CLIENT (FIRST_NAME, LAST_NAME, USER_NAME, EMAIL, ADDRESS, COUNTRY) VALUES ('John', 'Smith', 'john2', 'john2@gmail.com', 'Old st', 'GBR');


CREATE TABLE IF NOT EXISTS COUNTRY (
  ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  ISO3_VALUE VARCHAR(255),
  NAME VARCHAR(255),

);

INSERT INTO COUNTRY (ISO3_VALUE, NAME) VALUES ('EST', 'Estonia');
INSERT INTO COUNTRY (ISO3_VALUE, NAME) VALUES ('GBR', 'United Kingdom');
INSERT INTO COUNTRY (ISO3_VALUE, NAME) VALUES ('LVA', 'Latvia');