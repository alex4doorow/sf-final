INSERT INTO d_currencies (id, code, "name", amount_scale) VALUES(1, 'RUR', 'Rubles', 1);
INSERT INTO d_currencies (id, code, "name", amount_scale) VALUES(2, 'USD', 'Dollars USA', 1);

INSERT INTO d_persons (id, first_name, last_name, middle_name, country_iso_code_2, phone_number, email) VALUES(1, 'Ilon', 'Mask', 'A', 'US', '1 (111) 222-33-44', 'i.mask@gmail.com');

INSERT INTO d_customers (id, person_id, status, date_added) VALUES(12, 1, 'A', localtimestamp);

INSERT INTO sf_balances (id, customer_id, amount, cur_id, status, date_added, date_modified) VALUES(1, 12, 1000.0000000, 2, 'A', localtimestamp, localtimestamp);
