

create table if not exists BANK_ACCOUNT
(
    ID     INTEGER not null,
    AMOUNT INTEGER not null,
    constraint "bank-account_pk"
        primary key (ID)
);

create table if not exists CLIENT
(
    ID    INTEGER     not null,
    NAME  VARCHAR(64) not null,
    PHONE VARCHAR(13)
);

create unique index BANK_ACCOUNT_ID_UINDEX
    on CLIENT (ID);

create unique index PRIMARY_KEY_CLIENT
    on CLIENT (ID);

alter table CLIENT
    add constraint BANK_ACCOUNT_PK
        primary key (ID);

create table if not exists CLIENT_ACCOUNT
(
    ID     INTEGER not null,
    AMOUNT INTEGER not null,
    OWNER  INTEGER not null,
    constraint CLIENT_ACCOUNT_CLIENT_ID_FK
        foreign key (OWNER) references CLIENT
            on update cascade on delete cascade
);

create unique index CLIENT_ACCOUNT_ID_UINDEX
    on CLIENT_ACCOUNT (ID);

create unique index PRIMARY_KEY_C
    on CLIENT_ACCOUNT (ID);

alter table CLIENT_ACCOUNT
    add constraint CLIENT_ACCOUNT_PK
        primary key (ID);

create table if not exists BANK_LOG
(
    ID                    INTEGER   not null auto_increment,
    OPERATION_TYPE        INTEGER   not null,
    OPERATION_DESCRIPTION VARCHAR   not null,
    RECIPIENT             INTEGER,
    SENDER                INTEGER,
    DATE_AND_TIME         TIMESTAMP not null,
    constraint BANK_LOG_PK
        primary key (ID),
    constraint BANK_LOG_CLIENT_ACCOUNT_ID_FK
        foreign key (RECIPIENT) references CLIENT_ACCOUNT,
    constraint BANK_LOG_CLIENT_ACCOUNT_ID_FK_2
        foreign key (SENDER) references CLIENT_ACCOUNT
);


INSERT INTO PUBLIC.BANK_ACCOUNT (ID, AMOUNT) VALUES (1, 123456789);

INSERT INTO PUBLIC.CLIENT (ID, NAME, PHONE) VALUES (1, 'Client1', '0671234567');
INSERT INTO PUBLIC.CLIENT (ID, NAME, PHONE) VALUES (2, 'Client2', '0971234567');
INSERT INTO PUBLIC.CLIENT (ID, NAME, PHONE) VALUES (3, 'Client3', '0631234567');
INSERT INTO PUBLIC.CLIENT (ID, NAME, PHONE) VALUES (4, 'Client4', '0931234567');
INSERT INTO PUBLIC.CLIENT (ID, NAME, PHONE) VALUES (5, 'Client5', '0951234567');

INSERT INTO PUBLIC.CLIENT_ACCOUNT (ID, AMOUNT, OWNER) VALUES (1234500001, 345678, 1);
INSERT INTO PUBLIC.CLIENT_ACCOUNT (ID, AMOUNT, OWNER) VALUES (1234500002, 5678, 2);
INSERT INTO PUBLIC.CLIENT_ACCOUNT (ID, AMOUNT, OWNER) VALUES (1234500003, 7890, 3);
INSERT INTO PUBLIC.CLIENT_ACCOUNT (ID, AMOUNT, OWNER) VALUES (1234500004, 90678, 4);
INSERT INTO PUBLIC.CLIENT_ACCOUNT (ID, AMOUNT, OWNER) VALUES (1234500005, 50000, 5);
INSERT INTO PUBLIC.CLIENT_ACCOUNT (ID, AMOUNT, OWNER) VALUES (1234600001, 6789, 1);
INSERT INTO PUBLIC.CLIENT_ACCOUNT (ID, AMOUNT, OWNER) VALUES (1234600002, 10000, 2);
INSERT INTO PUBLIC.CLIENT_ACCOUNT (ID, AMOUNT, OWNER) VALUES (1234600004, 78500, 4);
INSERT INTO PUBLIC.CLIENT_ACCOUNT (ID, AMOUNT, OWNER) VALUES (1234700001, 100000, 1);
