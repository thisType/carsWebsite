use itc_cars;
create table  images
(id integer primary key auto_increment,
carId integer,
img mediumblob,
constraint foregnC  foreign key (carID) references car_details(id));

alter table images auto_increment =100;

