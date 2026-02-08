CREATE SEQUENCE T_PRODUCT_SEQ increment by 1 start with 1;

CREATE TABLE T_PRODUCT(
    ID BIGINT not null default nextval('T_PRODUCT_SEQ'),
    BARCODE varchar(32) not null unique,
    NAME varchar(255) not null,
    BRAND varchar(255),
    QUANTITY_VALUE DECIMAL(10,2),
    QUANTITY_UNIT varchar(16),
    IMAGE_URL TEXT,
    CATEGORY varchar(100),
    LAST_FETCHED_AT TIMESTAMP,
    SOURCE varchar(50) not null default 'OPEN_FOOD_FACTS',
    CREATED_AT TIMESTAMP not null default CURRENT_TIMESTAMP,
    UPDATED_AT TIMESTAMP not null default CURRENT_TIMESTAMP,

    CONSTRAINT PRODUCT_PK PRIMARY KEY (ID)
);
