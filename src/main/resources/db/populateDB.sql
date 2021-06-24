DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (user_id, datetime, description, calories)
VALUES (100000, '2011-06-22 19:10:25-07', 'Завтрак', 1000),
       (100000, '2012-06-20 19:10:25-07', 'Ланч', 1200),
       (100001, '2013-06-22 19:10:25-07', 'Завтрак', 500);
