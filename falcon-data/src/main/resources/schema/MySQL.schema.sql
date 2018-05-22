# noinspection SqlNoDataSourceInspectionForFile

CREATE DATABASE IF NOT EXISTS falcon_db
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;

USE falcon_db;

CREATE TABLE IF NOT EXISTS devices (
  id         VARCHAR(32)   NOT NULL,
  model      VARCHAR(32)   NOT NULL,
  protocol   VARCHAR(32)   NOT NULL,
  gmt_create DATETIME      NOT NULL,
  gmt_update DATETIME      NOT NULL,
  attributes VARCHAR(4096) NOT NULL,
  PRIMARY KEY (id)
)
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci
  ENGINE = InnoDB
  PARTITION BY KEY (id)
  PARTITIONS 1024;

CREATE TABLE IF NOT EXISTS models (
  name               VARCHAR(32)   NOT NULL,
  protocol           VARCHAR(32)   NOT NULL,
  supported_commands VARCHAR(1024) NOT NULL,
  attributes         VARCHAR(4096) NOT NULL,
  CONSTRAINT uk UNIQUE (name)
)
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS positions (
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
)
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci
  ENGINE = InnoDB
  PARTITION BY KEY (device_id)
  PARTITIONS 1024;
ALTER TABLE positions
  ADD INDEX idx_1(device_id, `time`, located);

CREATE TABLE IF NOT EXISTS events (
  device_id   VARCHAR(32)   NOT NULL,
  `type`      INTEGER       NOT NULL,
  position_id BIGINT(20)    NOT NULL,
  time        DATETIME      NOT NULL,
  attributes  VARCHAR(4096) NOT NULL
)
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci
  ENGINE = InnoDB
  PARTITION BY KEY (device_id)
  PARTITIONS 1024;
ALTER TABLE events
  ADD INDEX idx_1(device_id, `time`, `type`);
ALTER TABLE events
  ADD INDEX idx_2(device_id, `position_id`);
