use itc_cars;

create table purchases(
id integer primary key auto_increment,
userId integer not null references users (id),
carId integer not null references car_details (id),
datePurchase datetime default current_timestamp);



