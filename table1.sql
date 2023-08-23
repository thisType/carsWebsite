use itc_cars;
create table car_details 
(id integer primary key auto_increment,
car_name varchar(100) not null,
brand varchar(20) not null,
model year(4) not null,
category varchar(20) not null,
description varchar(200) not null,
price  decimal(12)not null
);

alter table car_details auto_increment =100000;


