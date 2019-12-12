
## Proyecto de escritorio en Java

CRUD desde app consola hacia una base de datos 
mysql test.persona con JDBC Transaccional

Se añade la capa de DAO para manejo de consultas
con JDBC utilizando transaccionalidad.

Connecton
Statement
PrepareStatement
Transaction

show VARIABLES where VARIABLE_NAME IN('hostname', 'port')

select @@version
create database test;
use test;

create table persona(
id_persona int not null auto_increment primary key,
nombre char(45),
apellido char(45),
email char(45),
telefono char(45));


select * from test.persona
select id_persona, nombre, apellido, email, telefono from persona
insert into persona(nombre, apellido, email, telefono) values('Juan', 'Perez', 'jperez@mail.com', '5566778899')
insert into persona(nombre, apellido, email, telefono) values('Karla', 'Gomez', 'kgomez@mail.com', '5544667755')