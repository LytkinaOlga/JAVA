# JAVA
1. Нужно создать базу данных CREATE DATABASE `technosiladb`
2. В базе данных создать 3 таблицы 
Таблица usr: CREATE TABLE `usr` ( `id` bigint NOT NULL, `active` bit(1) NOT NULL, `password` varchar(255) DEFAULT NULL,  `username` varchar(255) DEFAULT NULL, PRIMARY KEY (`id`))
Таблица user_role: CREATE TABLE `user_role` (  `user_id` bigint NOT NULL,  `roles` varchar(255) DEFAULT NULL,  KEY  (`user_id`),  CONSTRAINT  FOREIGN KEY (`user_id`) REFERENCES `usr` (`id`))
Таблица product: CREATE TABLE `product` (  `id` bigint NOT NULL,  `description` varchar(255) DEFAULT NULL,  `name` varchar(255) DEFAULT NULL,  `price` double DEFAULT NULL,  `filename` varchar(255) DEFAULT NULL,  PRIMARY KEY (`id`))
Таблица users_products2: CREATE TABLE `users_product2` (  `user_id` bigint NOT NULL,  `product_id` bigint NOT NULL,  PRIMARY KEY (`user_id`,`product_id`),  KEY (`product_id`),  CONSTRAINT  FOREIGN KEY (`user_id`) REFERENCES `usr` (`id`),  CONSTRAINT  FOREIGN KEY (`product_id`) REFERENCES `product` (`id`))

3. Подключить базу данных к идее
4. В проекте в папке resources создаешь файл application.properties
В него вставляешь:

spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3305/TechnoSilaDB?serverTimezone=Europe/Minsk
spring.datasource.username=root
spring.datasource.password=(свой пароль)

upload.path=/C:/Users/Lenovo PC/IdeaProjects/JAVA/TechnoSila/uploads
