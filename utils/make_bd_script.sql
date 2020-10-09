-------------------------------------
-- Ejecutar sentencia X sentencia 
DROP DATABASE
IF
	EXISTS zwsbh_$g846_pdv;
---------
CREATE DATABASE zwsbh_$g846_pdv;
---------
CREATE EXTENSION
IF
	NOT EXISTS "uuid-ossp";
-------------------------------------
CREATE SCHEMA
IF
	NOT EXISTS main;
CREATE TABLE main.factory (
	ID uuid NOT NULL PRIMARY KEY,
	nombre VARCHAR,
	rfc VARCHAR,
	estatus BOOLEAN,
	created_at TIMESTAMP ( 6 ),
	updated_at TIMESTAMP ( 6 ) 
);
CREATE TABLE main.USER (
	ID uuid NOT NULL PRIMARY KEY,
	nombre VARCHAR,
	email VARCHAR,
	PASSWORD VARCHAR,
	estatus BOOLEAN,
	factory_id uuid NOT NULL,
	created_at TIMESTAMP ( 6 ),
	updated_at TIMESTAMP ( 6 ),
	CONSTRAINT fk_factory FOREIGN KEY ( factory_id ) REFERENCES main.factory ( ID ) 
);
CREATE TABLE main.ROLE (
	ID uuid NOT NULL PRIMARY KEY,
	nombre VARCHAR,
	descripcion VARCHAR,
	estatus BOOLEAN,
	created_at TIMESTAMP ( 6 ),
	updated_at TIMESTAMP ( 6 ) 
);
CREATE TABLE main.user_has_role (
	ID uuid NOT NULL PRIMARY KEY,
	estatus BOOLEAN,
	created_at TIMESTAMP ( 6 ),
	updated_at TIMESTAMP ( 6 ),
	user_id uuid NOT NULL,
	role_id uuid NOT NULL,
	CONSTRAINT fk_user_id FOREIGN KEY ( user_id ) REFERENCES main.USER,
	CONSTRAINT fk_role_id FOREIGN KEY ( role_id ) REFERENCES main.ROLE 
);
CREATE TABLE main.contact (
	ID uuid NOT NULL PRIMARY KEY,
	nombre VARCHAR,
	valor VARCHAR,
	estatus BOOLEAN,
	created_at TIMESTAMP ( 6 ),
	updated_at TIMESTAMP ( 6 ) 
);
CREATE TABLE main.user_has_contact (
	ID uuid NOT NULL PRIMARY KEY,
	estatus BOOLEAN,
	created_at TIMESTAMP ( 6 ),
	updated_at TIMESTAMP ( 6 ),
	user_id uuid NOT NULL,
	contact_id uuid NOT NULL,
	CONSTRAINT fk_user_id FOREIGN KEY ( user_id ) REFERENCES main.USER,
	CONSTRAINT fk_contact_id FOREIGN KEY ( contact_id ) REFERENCES main.contact 
);
CREATE TABLE main.factory_has_contact (
	ID uuid NOT NULL PRIMARY KEY,
	estatus BOOLEAN,
	created_at TIMESTAMP ( 6 ),
	updated_at TIMESTAMP ( 6 ),
	factory_id uuid NOT NULL,
	contact_id uuid NOT NULL,
	CONSTRAINT fk_factory_id FOREIGN KEY ( factory_id ) REFERENCES main.factory,
	CONSTRAINT fk_contact_id FOREIGN KEY ( contact_id ) REFERENCES main.contact 
);
CREATE TABLE main.customer (
	ID uuid NOT NULL PRIMARY KEY,
	nombre VARCHAR,
	estatus BOOLEAN,
	created_at TIMESTAMP ( 6 ),
	updated_at TIMESTAMP ( 6 ),
	factory_id uuid NOT NULL,
	CONSTRAINT fk_factory_id FOREIGN KEY ( factory_id ) REFERENCES main.factory 
);
CREATE TABLE main.customer_has_contact (
	ID uuid NOT NULL PRIMARY KEY,
	estatus BOOLEAN,
	created_at TIMESTAMP ( 6 ),
	updated_at TIMESTAMP ( 6 ),
	customer_id uuid NOT NULL,
	contact_id uuid NOT NULL,
	CONSTRAINT fk_customer_id FOREIGN KEY ( customer_id ) REFERENCES main.customer,
	CONSTRAINT fk_contact_id FOREIGN KEY ( contact_id ) REFERENCES main.contact 
);
CREATE TABLE main.tax (
	ID uuid NOT NULL PRIMARY KEY,
	nombre VARCHAR,
	descripcion VARCHAR,
	valor NUMERIC,
	estatus BOOLEAN,
	created_at TIMESTAMP ( 6 ),
	updated_at TIMESTAMP ( 6 ) 
);
CREATE TABLE main.product (
	ID uuid NOT NULL PRIMARY KEY,
	nombre VARCHAR,
	estatus BOOLEAN,
	created_at TIMESTAMP ( 6 ),
	updated_at TIMESTAMP ( 6 ),
	factory_id uuid NOT NULL,
	CONSTRAINT fk_factory_id FOREIGN KEY ( factory_id ) REFERENCES main.factory 
);
CREATE TABLE main.product_has_tax (
	ID uuid NOT NULL PRIMARY KEY,
	estatus BOOLEAN,
	created_at TIMESTAMP ( 6 ),
	updated_at TIMESTAMP ( 6 ),
	product_id uuid NOT NULL,
	tax_id uuid NOT NULL,
	CONSTRAINT fk_product_id FOREIGN KEY ( product_id ) REFERENCES main.product,
	CONSTRAINT fk_tax_id FOREIGN KEY ( tax_id ) REFERENCES main.tax 
);
CREATE TABLE main.presentation (
	ID uuid NOT NULL PRIMARY KEY,
	nombre VARCHAR,
	costo NUMERIC,
	precio_sin_impuestos NUMERIC,
	precio_con_impuestos NUMERIC,
	nombre_normalizado VARCHAR,
	estatus BOOLEAN,
	product_id uuid NOT NULL,
	created_at TIMESTAMP ( 6 ),
	updated_at TIMESTAMP ( 6 ),
	CONSTRAINT fk_product_id FOREIGN KEY ( product_id ) REFERENCES main.product 
);
CREATE TABLE main.sell (
	ID uuid NOT NULL PRIMARY KEY,
	folio NUMERIC,
	total_letra VARCHAR,
	folio_verificador uuid,
	created_at TIMESTAMP ( 6 ),
	updated_at TIMESTAMP ( 6 ),
	customer_id uuid NOT NULL,
CONSTRAINT fk_customer_id FOREIGN KEY ( customer_id ) REFERENCES main.customer 
);

-- uuid_generate_v4()

INSERT INTO main.factory(id,nombre,rfc,estatus,created_at,updated_at)
VALUES ('3b43cf08-75d1-45f5-a231-98b8bffef84d','Comercial María Félix','RFCMARIAFELIX','t',now(),now());

INSERT INTO main.factory(id,nombre,rfc,estatus,created_at,updated_at)
VALUES ('85d499b8-ea6b-4a96-ab82-b2097facaad2','chinelo.io','RFCHINELOIO','t',now(),now());

INSERT INTO main.role(id,nombre,descripcion,estatus,created_at,updated_at)
VALUES ('487cca01-0f94-42bf-8bd9-39e70b6b1003','ROLE_ADMIN','Role para el administrador del sistema','t',now(),now());

INSERT INTO main.role(id,nombre,descripcion,estatus,created_at,updated_at)
VALUES ('84c4fc7d-d9a7-4d94-a9f3-2608c96fb62e','ROLE_USER','Role para el usuario comun que utiliza el sismtema','t',now(),now());

INSERT INTO main.user(id,nombre,email,password,estatus,factory_id,created_at,updated_at)
VALUES ('f6023bec-77d1-42b0-9f39-e0d7285d2a91','Papeleria','comercialmariafelix@hotmail.com','hlgnJTYg0RT8lq6RRSKPYQ==','t','3b43cf08-75d1-45f5-a231-98b8bffef84d',now(),now());

INSERT INTO main.user(id,nombre,email,password,estatus,factory_id,created_at,updated_at)
VALUES ('9683d0b2-e73e-4611-902d-ae083377e1cb','Marco Antonio Barrios Molina','marcoabm@chinelo.io','hlgnJTYg0RT8lq6RRSKPYQ==','t','85d499b8-ea6b-4a96-ab82-b2097facaad2',now(),now());

INSERT INTO main.user_has_role(id,estatus,user_id,role_id,created_at,updated_at)
VALUES ('cdb891a9-07e3-47fc-b1ae-c7e1d93b6770','t','f6023bec-77d1-42b0-9f39-e0d7285d2a91','84c4fc7d-d9a7-4d94-a9f3-2608c96fb62e',now(),now());

INSERT INTO main.user_has_role(id,estatus,user_id,role_id,created_at,updated_at)
VALUES ('920bc6c5-0b37-4b0a-977a-ea3cde277ea8','t','9683d0b2-e73e-4611-902d-ae083377e1cb','487cca01-0f94-42bf-8bd9-39e70b6b1003',now(),now());

INSERT INTO main.customer(id,nombre,estatus,factory_id,created_at,updated_at)
VALUES ('5f8e7a32-11b8-49c5-a1f2-ac9e556103f0','Publico en General','t','3b43cf08-75d1-45f5-a231-98b8bffef84d',now(),now());

INSERT INTO main.tax(id,nombre,descripcion,valor,estatus,created_at,updated_at)
VALUES ('ebd9d5f0-80da-42dd-b249-9efa5f5c3376','IVA','Impuesto de IVA',16,'t',now(),now());

SELECT * FROM main.user AS u 
JOIN main.user_has_role AS uhr ON uhr.user_id = u.id
JOIN main.role AS r ON r.id = uhr.role_id
JOIN main.factory AS f ON f.id = u.factory_id 

