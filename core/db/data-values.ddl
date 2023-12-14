INSERT INTO d_currencies (id, code, "name", amount_scale) VALUES(1, 'RUR', 'Rubles', 1);
INSERT INTO d_currencies (id, code, "name", amount_scale) VALUES(2, 'USD', 'Dollars USA', 1);

INSERT INTO d_operates(id, code, "name") VALUES(1, 'ADD', 'Additional');
INSERT INTO d_operates(id, code, "name") VALUES(2, 'SUBSTR', 'Substract');
INSERT INTO d_operates(id, code, "name") VALUES(3, 'TRANSF', 'Transfer');

INSERT INTO d_persons (id, first_name, last_name, middle_name, country_iso_code_2, phone_number, email)
VALUES(1, 'Ilon', 'Mask', 'A', 'US', '1 (111) 222-33-44', 'i.mask@gmail.com');
INSERT INTO d_persons (id, first_name, last_name, middle_name, country_iso_code_2, phone_number, email)
VALUES(2, 'Bill', 'Geits', 'B', 'US', '1 (999) 999-33-44', 'b.geits@gmail.com');

INSERT INTO d_customers(id, person_id, status, date_added)VALUES(12, 1, 'A', '2023-12-01 17:07:06.169');
INSERT INTO d_customers(id, person_id, status, date_added)VALUES(13, 2, 'A', '2023-12-01 17:07:06.169');

INSERT INTO sf_balances (id, customer_id, amount, cur_id, status, date_added, date_modified) VALUES(1, 12, 1000.0000000, 2, 'A', localtimestamp, localtimestamp);
