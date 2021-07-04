CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 1 INCREMENT BY 1;

CREATE TABLE shipment
(
    id                   INT            NOT NULL PRIMARY KEY,
    uuid                 VARCHAR(36)    NOT NULL,
    firstname            VARCHAR(36)    NOT NULL,
    lastname             VARCHAR(36)    NOT NULL,
    email                VARCHAR(36)    NOT NULL,
    street               VARCHAR(36)    NOT NULL,
    streetnumber         VARCHAR(36)    NOT NULL,
    doornumber           VARCHAR(36)    NOT NULL,
    floor                VARCHAR(36)    NOT NULL,
    city                 VARCHAR(36)    NOT NULL,
    zipcode              VARCHAR(36)    NOT NULL,
    country              VARCHAR(36)    NOT NULL,
    product              VARCHAR(36)    NOT NULL,
    status               VARCHAR(36)    NOT NULL,
    UNIQUE (id),
    UNIQUE (uuid)
);

INSERT INTO shipment (id, uuid, firstname, lastname, email, street, streetnumber,
                      doornumber, floor, city, zipcode, country, product, status)
VALUES (nextval('HIBERNATE_SEQUENCE'),
        '008865a4-8348-11eb-8dcd-0242ac130003',
        'Jack',
        'Black',
        'jack.black@gmail.com',
        'Street',
        '1',
        '12',
        '4',
        'Vienna',
        '1030',
        'AT',
        '4c68e51d-04d5-4c3d-b943-8903rc0ea92f',
        'OrderConfirmation');