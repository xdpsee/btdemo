CREATE SCHEMA falcon_db;
USE falcon_db;

CREATE TABLE devices (
  id         VARCHAR(32)   NOT NULL,
  model      VARCHAR(32)   NOT NULL,
  protocol   VARCHAR(32)   NOT NULL,
  gmt_create DATETIME      NOT NULL,
  gmt_update DATETIME      NOT NULL,
  attributes VARCHAR(4096) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE models (
  name               VARCHAR(32)   NOT NULL,
  protocol           VARCHAR(32)   NOT NULL,
  supported_commands VARCHAR(1024) NOT NULL,
  attributes         VARCHAR(4096) NOT NULL,
  CONSTRAINT uk UNIQUE (name)
);

CREATE TABLE positions (
  id         BIGINT(20)    NOT NULL,
  device_id  VARCHAR(32)   NOT NULL,
  `time`     DATETIME      NOT NULL,
  located    TINYINT       NOT NULL,
  latitude   DOUBLE        NOT NULL,
  longitude  DOUBLE        NOT NULL,
  altitude   DOUBLE        NOT NULL,
  speed      DOUBLE        NOT NULL,
  course     DOUBLE        NOT NULL,
  accuracy   DOUBLE        NOT NULL,
  outdated   TINYINT       NOT NULL,
  network    VARCHAR(512)  NOT NULL,
  attributes VARCHAR(4096) NOT NULL
);

CREATE TABLE IF NOT EXISTS events (
  device_id   VARCHAR(32)   NOT NULL,
  `type`      INTEGER       NOT NULL,
  position_id BIGINT(20)    NOT NULL,
  time        DATETIME      NOT NULL,
  attributes  VARCHAR(4096) NOT NULL
);
