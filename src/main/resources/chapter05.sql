CREATE DATABASE `chapter05` DEFAULT CHARACTER SET utf8;
USE `chapter05`;
CREATE TABLE `book`(
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(128) DEFAULT NULL,
    `author` varchar(64)  DEFAULT NULL,
    PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into `book`(`id`,`name`,`author`)values (3,'三国演义','罗贯中'),(4,'水浒传','施耐庵');
insert into `book`(`id`,`name`,`author`)values (1,'book1','author1'),(2,'book2','author2');

CREATE TABLE `admin`(
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(128) DEFAULT NULL,
    `password` varchar(64)  DEFAULT NULL,
    PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into `admin`(`id`,`name`,`password`)values (1,'user1','password1'),(2,'user2','password2');

CREATE TABLE `people`(
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(128) DEFAULT NULL,
    `solar_calendar_birthday` varchar(64)  DEFAULT NULL,
    `lunar_calendar_birthday` varchar(64)  DEFAULT NULL,
    PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into `people`(`id`,`name`,`solar_calendar_birthday`,`lunar_calendar_birthday`)values (1,'陈俊杰','1991-10-24','九月十七'),(2,'李清丽','1966-12-08','十二月十七');