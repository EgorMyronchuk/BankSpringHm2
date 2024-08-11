INSERT INTO employer (id, name, address) VALUES (1, 'TechCorp', '1234 Tech Street');
INSERT INTO employer (id, name, address) VALUES (2, 'HealthInc', '5678 Health Avenue');

INSERT INTO customer (id, name, email, age) VALUES (1, 'John Doe', 'john.doe@example.com', 30);
INSERT INTO customer (id, name, email, age) VALUES (2, 'Jane Smith', 'jane.smith@example.com', 28);

INSERT INTO account (id, number, currency, balance, customer_id) VALUES (1, 'ACC12345', 'USD', 1000.0, 1);
INSERT INTO account (id, number, currency, balance, customer_id) VALUES (2, 'ACC67890', 'EUR', 2000.0, 2);

INSERT INTO customer_employer (customer_id, employer_id) VALUES (1, 1);
INSERT INTO customer_employer (customer_id, employer_id) VALUES (1, 2);
INSERT INTO customer_employer (customer_id, employer_id) VALUES (2, 1);