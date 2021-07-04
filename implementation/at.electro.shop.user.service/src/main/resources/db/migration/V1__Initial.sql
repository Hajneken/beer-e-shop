create table cart
(
    uuid varchar(255) not null,
    primary key (uuid)
);
create table carts_products
(
    carts_uuid   varchar(255) not null,
    products_id varchar(255) not null
);
create table product
(
    id varchar(255) not null,
    primary key (id)
);
create table user_entity
(
    uuid          varchar(255) not null,
    city          varchar(255),
    country       varchar(255),
    door_number   integer      not null,
    floor         varchar(255),
    street        varchar(255),
    street_number varchar(255),
    zip_code      integer      not null,
    first_name    varchar(255),
    last_name     varchar(255),
    cart_uuid     varchar(255),
    primary key (uuid)
);


insert into cart (uuid)
values ('ff80818179cd38450179cd384a450001');
insert into cart (uuid)
values ('ff80818179cd38450179cd384a450002');
insert into cart (uuid)
values ('ff80818179cd38450179cd384a450003');
insert into cart (uuid)
values ('ff80818179cd38450179cd384a450004');
insert into cart (uuid)
values ('ff80818179cd38450179cd384a450005');
insert into cart (uuid)
values ('ff80818179cd38450179cd384a450006');
insert into cart (uuid)
values ('ff80818179cd38450179cd384a450007');
insert into cart (uuid)
values ('ff80818179cd38450179cd384a450008');
insert into cart (uuid)
values ('ff80818179cd38450179cd384a450009');
insert into cart (uuid)
values ('ff80818179cd38450179cd384a450010');
insert into cart (uuid)
values ('ff80818179cd38450179cd384a450011');
insert into cart (uuid)
values ('ff80818179cd38450179cd384a450012');
insert into cart (uuid)
values ('ff80818179cd38450179cd384a450013');
insert into cart (uuid)
values ('ff80818179cd38450179cd384a450014');


insert into user_entity
(city, country, door_number,
 floor, street, street_number,
 zip_code, cart_uuid, first_name,
 last_name, uuid)
values ('vienna',
        'AT',
        5,
        '1. Kellergeschoss',
        'Cumberlandstraße',
        '111',
        '1140',
        'ff80818179cd38450179cd384a450001',
        'Johannes',
        'Preisinger',
        '008865a4-8348-11eb-8dcd-0242ac130003'),
       ('vienna',
        'AT',
        5,
        '1. 28',
        'landstraße',
        '111',
        '1120',
        'ff80818179cd38450179cd384a450002',
        'Himal',
        'puri',
        'd7a8dc0b-c843-4882-8e9d-43dde1dddcd2'),
       ('vienna',
        'AT',
        5,
        '1. KagranPlatzt',
        'Wagram',
        '111',
        '1210',
        'ff80818179cd38450179cd384a450003',
        'Zemanec',
        'Hynek',
        'c64eee9c-a4dc-11eb-bcbc-0242ac130002'),
       ('vienna',
        'AT',
        5,
        '1. Ergeschoss',
        'landstraße',
        '111',
        '1140',
        'ff80818179cd38450179cd384a450004',
        'Christian',
        'Lascsak',
        'b773661f-476f-4869-9072-b33856aac247'),
       ('vienna',
        'AT',
        5,
        '1. Prater',
        'Am Campus',
        '111',
        '1020',
        'ff80818179cd38450179cd384a450005',
        'Angelika',
        'Kapeller',
        '26d7b000-a390-4418-888e-f50108edab5e'),
       ('vienna',
        'AT',
        5,
        '1. 29',
        'Am Tabor',
        '111',
        '1140',
        'ff80818179cd38450179cd384a450006',
        'David',
        'Bekham',
        '2963f79d-3af6-4847-8da3-6321a5ce0fe3'),
       ('vienna',
        'AT',
        5,
        '1. Kellergeschoss',
        'Cumberlandstraße',
        '111',
        '1140',
        'ff80818179cd38450179cd384a450007',
        'Nate',
        'Diaz',
        '04fa9db1-4d5e-4cc0-a313-5ea66f2cd262'),
       ('vienna',
        'AT',
        5,
        '1. Mumbai',
        'India',
        '111',
        '1140',
        'ff80818179cd38450179cd384a450008',
        'Shah rukh',
        'khan',
        '04fa9db1-4d5e-4cc0-a313-5ea66f2cd264'),
       ('vienna',
        'AT',
        5,
        '1. 22',
        'nordbahnstrassed',
        '111',
        '1140',
        'ff80818179cd38450179cd384a450009',
        'Brad',
        'pitt',
        '8b0b2306-b77f-451b-bd07-811f0cf7a101'),
       ('vienna',
        'AT',
        5,
        '12. 22',
        'SudTirolerplatzt',
        '111',
        '1140',
        'ff80818179cd38450179cd384a450010',
        'Angelina',
        'Jolie',
        '2c7d1f88-5c13-4d5b-86c9-87bcf8390bdb'),
       ('vienna',
        'AT',
        5,
        '1. 28',
        'Am Tabor',
        '111',
        '1140',
        'ff80818179cd38450179cd384a450011',
        'lana',
        'rhoades',
        'a757efd0-3a01-41f8-bd48-9aec7c636ce1'),
       ('vienna',
        'AT',
        5,
        '1. 45',
        'KeplerPlatzt',
        '111',
        '1140',
        'ff80818179cd38450179cd384a450012',
        'evangelos.ntentos@univie.ac.at',
        'evangelos.ntentos@univie.ac.at',
        '3bfddb0-aa6c-4b0d-9975-67d5946173cb'),
       ('vienna',
        'AT',
        5,
        '1. 22',
        'Karlsplatzt',
        '111',
        '1140',
        'ff80818179cd38450179cd384a450013',
        'Donald',
        'Trumph',
        '53bfddb0-aa6c-4b0d-9975-67d5946173cd'),
       ('vienna',
        'AT',
        5,
        '1. 33',
        'Stephenplatzt',
        '111',
        '1140',
        'ff80818179cd38450179cd384a450013',
        'Kim',
        'John un',
        '9788c815-52af-433a-8de0-0f1403797bc3');

