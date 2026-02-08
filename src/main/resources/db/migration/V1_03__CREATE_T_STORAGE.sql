CREATE SEQUENCE T_STORAGE_SEQ increment by 1 start with 1;

CREATE TABLE T_STORAGE(
    ID BIGINT not null default nextval('T_STORAGE_SEQ'),
    NAME varchar(255) not null,
    USER_ID BIGINT not null,
    DESCRIPTION TEXT,
    ARCHIVED boolean not null default false,
    CREATED_AT TIMESTAMP not null default CURRENT_TIMESTAMP,
    UPDATED_AT TIMESTAMP not null default CURRENT_TIMESTAMP,

    CONSTRAINT STORAGE_PK PRIMARY KEY (ID),

    CONSTRAINT STORAGE_USER_FK
        FOREIGN KEY (USER_ID)
        REFERENCES T_USER(ID)
);