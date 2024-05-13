create TABLE userTable (
                           id INTEGER PRIMARY KEY,
                           userName varchar(50),
                           password varchar(50),
                           role VARCHAR(50),
                           email VARCHAR(50)
);

insert into userTable (id, userName, password, role, email)
    VALUES (1, 'eason', '', 'admin', 'eason@eason.com');

alter table userTable rename to user_table;
alter table user_table alter password set data type VARCHAR(200);

create SEQUENCE user_table_seq as integer INCREMENT 50;