create table stock
(
   id integer not null primary key,
   name varchar(255) not null,
   current_price decimal(12,2) not null,
   updated timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

);