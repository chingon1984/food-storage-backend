CREATE SEQUENCE T_USER_SEQ increment by 1 start with 1;

CREATE TABLE T_USER (
  ID BIGINT not null default nextval('T_USER_SEQ'),
  NAME varchar(100) not null unique,
  EMAIL varchar(255) not null unique,
  PASSWORD_HASH varchar(255) not null,
  SEC_ROLE varchar(255) not null default 'USER' CHECK (SEC_ROLE IN ('USER', 'ADMIN')),
  VERIFIED  boolean not null default false,
  CREATED_AT timestamp not null DEFAULT CURRENT_TIMESTAMP,
  UPDATED_AT timestamp not null DEFAULT CURRENT_TIMESTAMP,

  CONSTRAINT USER_PK PRIMARY KEY (ID)
);