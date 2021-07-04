CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 1 INCREMENT BY 1;

CREATE TABLE inventory
(
    id            INT         NOT NULL PRIMARY KEY,
    inventory_uuid VARCHAR(36) NOT NULL,
    vendor        VARCHAR(36) NOT NULL,
    UNIQUE (id),
    UNIQUE (inventory_uuid)
);

CREATE TABLE product
(
    id             INT          NOT NULL PRIMARY KEY,
    uuid           VARCHAR(36)  NOT NULL,
    name           VARCHAR(36)  NOT NULL,
    brand          VARCHAR(36)  NOT NULL,
    img            VARCHAR(150) NOT NULL,
    rating         DECIMAL      NOT NULL,
    ratings_count   INTEGER      NOT NULL,
    description    VARCHAR(300) NOT NULL,
    alcohol_percent DECIMAL      NOT NULL,
    price          DECIMAL      NOT NULL,
    currency       VARCHAR(5)   NOT NULL,
    modified_at    TIMESTAMP    NOT NULL,
    inventory_id    INT          NOT NULL,
    UNIQUE (id),
    UNIQUE (uuid),
    CONSTRAINT fk_inventory
        FOREIGN KEY (inventory_id)
            REFERENCES inventory (id)
);

INSERT INTO inventory (id, inventory_uuid, vendor)
VALUES (nextval('HIBERNATE_SEQUENCE'),
        '8fd31a0b-1ffb-4e17-8a90-645f1110855b',
        'd7a8dc0b-c843-4882-8e9d-43dde1dddcd2');

INSERT INTO inventory (id, inventory_uuid, vendor)
VALUES (nextval('HIBERNATE_SEQUENCE'),
        '5c42d6c2-f965-485a-9db3-fff14bf5a9c6',
        'c64eee9c-a4dc-11eb-bcbc-0242ac130002');

INSERT INTO inventory (id, inventory_uuid, vendor)
VALUES (nextval('HIBERNATE_SEQUENCE'),
        'a6853ff5-ff0f-4920-95d9-9678ddd64f84',
        'b773661f-476f-4869-9072-b33856aac247');

INSERT INTO inventory (id, inventory_uuid, vendor)
VALUES (nextval('HIBERNATE_SEQUENCE'),
        '1eff7512-9525-4809-8751-5a6e5709458d',
        '26d7b000-a390-4418-888e-f50108edab5e');

INSERT INTO inventory (id, inventory_uuid, vendor)
VALUES (nextval('HIBERNATE_SEQUENCE'),
        '20ba0d1b-f7a2-4292-830c-9625c3183d5c',
        '2963f79d-3af6-4847-8da3-6321a5ce0fe3');

INSERT INTO product (id,
                     uuid,
                     name,
                     brand,
                     img,
                     rating,
                     ratings_count,
                     description,
                     alcohol_percent,
                     price,
                     currency,
                     modified_at,
                     inventory_id)

VALUES (nextval('HIBERNATE_SEQUENCE'),
        '321f737d-f8dd-4c9d-aca8-b8c6164a1dd1',
        'Totally good beer',
        'Wasted Grizzly',
        'https://cdn.homebrewersassociation.org/wp-content/uploads/irish-red.jpg',
        4.6,
        5,
        'If a smashed Kashmir IPA tries to seduce a polar bear beer defined by a razor blade beer, then a Coors near an Ellis Island IPA self-flagellates. Brownie bonbon biscuit fruitcake icing lollipop dessert sweet roll.',
        7.3,
        13.8,
        'EUR',
        '2021-01-08 04:05:06',
        1);

INSERT INTO product (id,
                     uuid,
                     name,
                     brand,
                     img,
                     rating,
                     ratings_count,
                     description,
                     alcohol_percent,
                     price,
                     currency,
                     modified_at,
                     inventory_id)

VALUES (nextval('HIBERNATE_SEQUENCE'),
        '4c68e51d-04d5-4c3d-b943-79e7fcaea92f',
        'Not so good beer',
        'Wasted Grizzly',
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRouHw7wONoTr2TgEYAeBtIzDbjP7C9PgC05A&usqp=CAU',
        4.6,
        5,
        'If a smashed Kashmir IPA tries to seduce a polar bear beer defined by a razor blade beer, then a Coors near an Ellis Island IPA self-flagellates. Brownie bonbon biscuit fruitcake icing lollipop dessert sweet roll.',
        7.3,
        20.72,
        'EUR',
        '2021-01-08 04:05:06',
        2);

INSERT INTO product (id,
                     uuid,
                     name,
                     brand,
                     img,
                     rating,
                     ratings_count,
                     description,
                     alcohol_percent,
                     price,
                     currency,
                     modified_at,
                     inventory_id)

VALUES (nextval('HIBERNATE_SEQUENCE'),
        '8de5155a-e36b-4b3b-83d2-631e07df9078',
        'Sewage pipe',
        'Imperial Stout',
        'https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/how-to-make-green-beer-1610743049.jpg',
        3.6,
        5,
        'A grizzly beer accidentally buys an expensive drink for a Pilsner Urquell. A precise Busch reads a magazine, or a mitochondrial sake bomb avoids contact with the Amarillo Pale Ale. Marzipan bonbon cake chocolate sugar plum lollipop gummi bears dragée jelly beans.',
        7.3,
        20.28,
        'EUR',
        '2021-01-08 04:05:06',
        3);

INSERT INTO product (id,
                     uuid,
                     name,
                     brand,
                     img,
                     rating,
                     ratings_count,
                     description,
                     alcohol_percent,
                     price,
                     currency,
                     modified_at,
                     inventory_id)

VALUES (nextval('HIBERNATE_SEQUENCE'),
        'dfc6d5d8-3d15-4d8a-a29a-01f5894e122e',
        'Nice nice',
        'Funny Tornado',
        'https://www.julieseatsandtreats.com/wp-content/uploads/2019/02/How-to-Make-Green-Beer.jpg',
        4.6,
        5,
        'If a smashed Kashmir IPA tries to seduce a polar bear beer defined by a razor blade beer, then a Coors near an Ellis Island IPA self-flagellates. Brownie bonbon biscuit fruitcake icing lollipop dessert sweet roll.',
        7.3,
        14.28,
        'EUR',
        '2021-01-08 04:05:06',
        3);

INSERT INTO product (id,
                     uuid,
                     name,
                     brand,
                     img,
                     rating,
                     ratings_count,
                     description,
                     alcohol_percent,
                     price,
                     currency,
                     modified_at,
                     inventory_id)

VALUES (nextval('HIBERNATE_SEQUENCE'),
        'fe1ad394-4d78-493d-b1cc-902ddafb7d3d',
        'Pink Glasses',
        'Funny Tornado',
        'https://drinksfeed.com/wp-content/blogs.dir/1/files/2019/09/pink-beer-in-glass.png',
        4.6,
        5,
        'If a smashed Kashmir IPA tries to seduce a polar bear beer defined by a razor blade beer, then a Coors near an Ellis Island IPA self-flagellates. Brownie bonbon biscuit fruitcake icing lollipop dessert sweet roll.',
        7.3,
        14.28,
        'EUR',
        '2021-01-08 04:05:06',
        4);