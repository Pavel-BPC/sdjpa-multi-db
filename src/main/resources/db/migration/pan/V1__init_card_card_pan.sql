DROP TABLE IF EXISTS credit_card_pan;

create TABLE credit_card_pan (
    id bigint not null auto_increment,
    credit_card_number varchar(20),
    primary key (id)
);