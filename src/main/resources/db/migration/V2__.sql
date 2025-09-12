CREATE TABLE pilot
(
    id         BIGINT  NOT NULL,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    age        INTEGER NOT NULL,
    CONSTRAINT pk_pilot PRIMARY KEY (id)
);

ALTER TABLE aircraft
    ADD pilot_id BIGINT;

ALTER TABLE aircraft
    ADD CONSTRAINT FK_AIRCRAFT_ON_PILOT FOREIGN KEY (pilot_id) REFERENCES pilot (id);

ALTER TABLE aircraft
    DROP COLUMN pilot;