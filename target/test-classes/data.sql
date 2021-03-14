DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
  id bigint NOT NULL,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  department VARCHAR(250) DEFAULT NULL,
  email VARCHAR(250) DEFAULT NULL,
  address VARCHAR(250) DEFAULT NULL
);

INSERT INTO employee (id, first_name, last_name, department,email,address) VALUES (1001, 'Veer', 'kakarla','Java','Veer@gmail.com','Hyderabad-INDIA');
INSERT INTO employee (id, first_name, last_name, department,email,address) VALUES (1002, 'Raju', 'kakarla','Java-Spring','Raju@gmail.com','Delhi-INDIA');
INSERT INTO employee (id, first_name, last_name, department,email,address) VALUES (1003, 'Veera', 'kakarla','Java-SpringBoot','Veera@gmail.com','Bangalore-INDIA');
