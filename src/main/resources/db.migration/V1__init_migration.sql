create table role
(
    id         bigserial
        primary key,
    created_at timestamp,
    updated_at timestamp,
    uuid       uuid,
    role_name  varchar(255)
);
create table communication
(
    id                     bigserial
        primary key,
    created_at             timestamp,
    updated_at             timestamp,
    uuid                   uuid,
    contact_as_with_number varchar(255),
    web_site               varchar(255),
    email_address          varchar(255)
);
create table messages
(
    id         bigserial
        primary key,
    created_at timestamp,
    updated_at timestamp,
    uuid       uuid,
    messages   varchar(255),
    title   varchar(255),
    user_id    bigint not null
        constraint FK_ATM_USER_ID
            references atm_user
);


create table atm_user
(
    id               bigserial
        primary key,
    deactivated      bool default false,
    created_at       timestamp,
    updated_at       timestamp,
    uuid             uuid,
    email            varchar(255) unique not null,
    card_holder_name varchar(255) unique not null,
    card_exp_date    timestamp,
    card_cvv         varchar(255),
    password         varchar(255)        not null,
    card_number      varchar(255) unique not null,
    balance          bigint
        constraint balance_value_check CHECK ( balance >= 0),
    role_id          bigint              not null
        constraint FK_ROLE_ID
            references role
);


INSERT INTO communication (id, created_at, uuid, contact_as_with_number, web_site, email_address)
VALUES (1, CURRENT_TIMESTAMP, gen_random_uuid(), '+37411111111', 'www.ejsHelpUs.com', 'ejsAskYourQuestion@ejs.com');


INSERT INTO role (id, created_at, uuid, role_name)
VALUES (1, CURRENT_TIMESTAMP, gen_random_uuid(), 'ROLE_USER');


