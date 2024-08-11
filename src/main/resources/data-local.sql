INSERT INTO employer (id,name, address) VALUES (1, 'TechCorp', '1234 Tech Street');
INSERT INTO employer (id, name, address) VALUES (2, 'HealthInc', '5678 Health Avenue');

INSERT INTO customer (id, name, email, age) VALUES (3 ,'John Doe', 'john.doe@example.com', 30);
INSERT INTO customer (id, name, email, age) VALUES (4 ,'Jane Smith', 'jane.smith@example.com', 28);

INSERT INTO account (id, number, currency, balance, customer_id) VALUES (5 ,'ACC12345', 'USD', 1000.0, 3);
INSERT INTO account (id, number, currency, balance, customer_id) VALUES (6, 'ACC67890', 'EURO', 2000.0, 4);

INSERT INTO customer_employer (customer_id , employer_id) VALUES (3 , 1);
INSERT INTO customer_employer (customer_id , employer_id) VALUES (4 , 2);

