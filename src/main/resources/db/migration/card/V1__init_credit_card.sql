DROP TABLE IF EXISTS credit_card;

create TABLE credit_card (
    id bigint not null auto_increment,
    cvv varchar(20),
    expiration_date varchar(20),
    primary key (id)
);