DELETE FROM votes;
DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
DELETE FROM menu;
DELETE FROM restaurants;

INSERT INTO users (NAME, EMAIL, PASSWORD) VALUES
  ('Admin', 'admin@gmail.com', 'admin'),
  ('UserAAA', 'userAAA@yandex.ru', 'password'),
  ('UserBBB', 'userBBB@mail.ru', 'password'),
  ('UserCCC', 'userCCC@gmail.com', 'password');

INSERT INTO user_roles (ROLE, USER_ID) VALUES
  ('ROLE_ADMIN', 1000),
  ('ROLE_USER', 1001),
  ('ROLE_USER', 1002),
  ('ROLE_USER', 1003);

INSERT INTO restaurants (NAME) VALUES
  ('Zefir'),
  ('Magistrat'),
  ('Magesta');

INSERT INTO menu (RESTAURANT_ID, DATE) VALUES
  (1000, '2018-04-22'),
  (1001, '2018-04-22'),
  (1002, '2018-04-22'),
  (1000, '2018-04-23'),
  (1001, '2018-04-23'),
  (1002, '2018-04-23');

INSERT INTO meals (NAME, PRICE, MENU_ID) VALUES
  ('Dish1 r1 22', 15000, 1000),
  ('Dish2 r1 22', 20000, 1000),
  ('Dish3 r1 22', 25000, 1000),
  ('Dish1 r2 22', 27000, 1001),
  ('Dish2 r2 22', 29000, 1001),
  ('Dish3 r2 22', 38000, 1001),
  ('Dish1 r3 22', 31500, 1002),
  ('Dish2 r3 22', 14000, 1002),
  ('Dish3 r3 22', 26000, 1002),
  ('Dish1 r1 23', 23000, 1003),
  ('Dish2 r1 23', 33000, 1003),
  ('Dish3 r1 23', 31000, 1003),
  ('Dish1 r2 23', 14500, 1004),
  ('Dish1 r3 23', 50000, 1005),
  ('Dish1 r3 23', 46000, 1005),
  ('Dish2 r3 23', 12000, 1005);

INSERT INTO VOTES (USER_ID, RESTAURANT_ID, DATE) VALUES
  (1001, 1001, '2018-04-22'),
  (1002, 1002, '2018-04-22'),
  (1003, 1002, '2018-04-22'),
  (1001, 1000, '2018-04-23'),
  (1002, 1000, '2018-04-23'),
  (1003, 1001, '2018-04-23');