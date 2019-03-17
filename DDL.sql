USE s185144;
CREATE TABLE users_mandatory2(
user_id TINYINT,  /*should be AUTO_INCREMENT*/
userName VARCHAR(20), 
ini VARCHAR(4), 
PRIMARY KEY (user_id)
);

CREATE TABLE roles_aflevering_2(
 user_id TINYINT,
 role VARCHAR(20),
 FOREIGN KEY (user_id) REFERENCES users_mandatory2(user_id)
 ON DELETE CASCADE);
 DROP TABLE roles_aflevering_2;


/*######################################
4 tables structure
########################################*/
CREATE TABLE users_mandatory2(
user_id TINYINT,  /*should be AUTO_INCREMENT*/
userName VARCHAR(20), 
ini VARCHAR(4), 
active boolean,
PRIMARY KEY (user_id)
);

CREATE TABLE admins_mandatory2(
user_id TINYINT,
PRIMARY KEY (user_id),
FOREIGN KEY (user_id) REFERENCES users_mandatory2(user_id)
ON DELETE CASCADE
);

CREATE TABLE customers_mandatory2(
user_id TINYINT,
PRIMARY KEY (user_id),
FOREIGN KEY (user_id) REFERENCES users_mandatory2(user_id)
ON DELETE CASCADE
);

CREATE TABLE guests_mandatory2(
user_id TINYINT,
PRIMARY KEY (user_id),
FOREIGN KEY (user_id) REFERENCES users_mandatory2(user_id)
ON DELETE CASCADE
);

/*######################################
Inserting test data
########################################*/
INSERT INTO users_mandatory2 (user_id, userName, ini, active)
values (1, 'John', 'JO', 1);
INSERT INTO customers_mandatory2 values (1);
SELECT user_id FROM customers_mandatory2 WHERE user_id=1;
/*######################################
Selecting only admins and customers
########################################*/

SELECT * FROM users_mandatory2 
JOIN customers_mandatory2
ON users_mandatory2.user_id = customers_mandatory2.user_id;

SELECT * FROM users_mandatory2 
JOIN admins_mandatory2
ON users_mandatory2.user_id = admins_mandatory2.user_id;

/*########################################
Teardown
##########################################*/
DELETE FROM users_mandatory2 WHERE user_id=1;

DELETE FROM admins_mandatory2;
DELETE FROM customers_mandatory2;
DELETE FROM guests_mandatory2;
DROP TABLE users_mandatory2;
DROP TABLE admins_mandatory2;
DROP TABLE customers_mandatory2;
DROP TABLE guests_mandatory2;







/*##################################### 
Old structure from CDIO_1
#######################################*/
CREATE TABLE roles_aflevering_2(
roles_id TINYINT AUTO_INCREMENT,
 user_id TINYINT,
 role VARCHAR(20),
 PRIMARY KEY (roles_id),
 FOREIGN KEY (user_id) REFERENCES users_cdio(user_id));
 
 
 
 INSERT INTO users_cdio 
 values(12,'John', 'JO', '1234567890', '123456', 1);
 
 INSERT INTO roles_cdio (user_id, role)
 values(12, 'user');
 
 
 
SELECT * FROM users_cdio;
SELECT * FROM roles_cdio;