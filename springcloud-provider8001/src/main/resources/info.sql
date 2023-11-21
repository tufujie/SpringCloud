create table payment
(
    id     bigint(20) primary key auto_increment,
    serial varchar(20) comment '序列号'
);

insert into payment(serial)
values ('Jef'),
       ('张三'),
       ('李四');