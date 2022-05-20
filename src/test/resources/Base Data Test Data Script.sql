INSERT INTO library_test.role(role) VALUES('user'),('admin'),('manager');
INSERT INTO library_test.user_statuses(status) VALUES('active'),('blocked'),('delete');
INSERT INTO library_test.users(id_status, id_role, password, first_name, last_name, email, count_violations)
VALUES(3, 1, 'F662834B04A38856A3F36BD7BA99E5C3', 'Singer', 'Bob', 'admin_library@gmail.com', 0);

INSERT INTO library_test.users(id_status, id_role, password, first_name, last_name, email, count_violations)
VALUES(3, 1, 'F662834B04A38856A3F36BD7BA99E5C3', 'Steven', 'Guttenberg', 'admin_library@gmail.com', 0);

INSERT INTO library_test.users(id_status, id_role, password, first_name, last_name, email, count_violations)
VALUES(3, 1, 'F662834B04A38856A3F36BD7BA99E5C3', 'Carey', 'Mahoney', 'admin_library@gmail.com', 0);

INSERT INTO library_test.order_statuses(status) VALUES('opened'),('approved'),('rejected'),('arrived'),('closed');

INSERT INTO library_test.library_statuses(status) VALUES('opened'),('closed');

INSERT INTO library_test.libraries(city, street, id_status)
VALUES('Минск', 'пр. Независимости 116', 1),
      ('Гомель', 'пл. Ленина 1', 1),
      ('Брест', 'ул. Героев обороны Брестской крепости 60', 2);

INSERT INTO library_test.card_statuses(status) VALUES('open'),('closed');

INSERT INTO library_test.books(shelf, title, quantity, borrow, publisher, year, isbn, id_library, description)
VALUES('1-S4', 'История Рима от основания города Т1', 8, 0, 'Наука', 1989, '5-02-008995-8', 1,
       'Основное произведение Тита Ливия, один из самых известных и фундаментальных трудов по истории Древнего Рима. Охватывает периоды от разрушения Трои до 9 года до н. э. (до смерти Друза Старшего включительно).');

INSERT INTO library_test.books(shelf, title, quantity, borrow, publisher, year, isbn, id_library, description)
VALUES('1-S4', 'Гарри Поттер и философский камень', 8, 0, 'Росмэн', 1999, '5-353-00308-X', 1,
       'Описание книги Гарри Поттер и философский камень');

INSERT INTO library_test.books(shelf, title, quantity, borrow, publisher, year, isbn, id_library, description)
VALUES('1-S4', 'Гарри Поттер и тайная комната', 8, 0, 'Росмэн', 2001, '5-8451-0947-7', 1,
       'Описание книги Гарри Поттер и тайная комната');

INSERT INTO library_test.books(shelf, title, quantity, borrow, publisher, year, isbn, id_library, description)
VALUES('1-S4', 'Гарри Поттер и узник Азкабана', 8, 0, 'Росмэн', 2002, '5-353-00370-5', 1,
       'Описание книги Гарри Поттер и узник Азкабана');

INSERT INTO library_test.books(shelf, title, quantity, borrow, publisher, year, isbn, id_library, description)
VALUES('1-S4', 'Гарри Поттер и Кубок огня', 8, 0, 'Росмэн', 2003, '5-353-00579-5', 1,
       'Описание книги Гарри Поттер и Кубок огня');

INSERT INTO library_test.books(shelf, title, quantity, borrow, publisher, year, isbn, id_library, description)
VALUES('1-S4', 'Гарри Поттер и Орден Феникса', 8, 0, 'Росмэн', 2004, '5-353-01435-9', 1,
       'Описание книги Гарри Поттер и Орден Феникса');

INSERT INTO library_test.books(shelf, title, quantity, borrow, publisher, year, isbn, id_library, description)
VALUES('1-S4', 'Гарри Поттер и Принц-Полукровка', 8, 0, 'Росмэн', 2005, '5-353-02187-8', 1,
       'Описание книги Гарри Поттер и Принц-Полукровка');

INSERT INTO library_test.books(shelf, title, quantity, borrow, publisher, year, isbn, id_library, description)
VALUES('1-S4', 'Гарри Поттер и Дары Смерти', 8, 0, 'Росмэн', 2007, '978-5-353-02907-6', 1,
       'Описание книги Гарри Поттер и Дары Смерти');

INSERT INTO library_test.authors(name) VALUES('Тит Ливий');

INSERT INTO library_test.authors(name) VALUES('Дж. К. Роулинг');

INSERT INTO library_test.authors_has_book(id_author, id_book) VALUES((SELECT id_authors FROM authors WHERE name='Тит Ливий'), 1);

INSERT INTO library_test.authors_has_book(id_author, id_book) VALUES((SELECT id_authors FROM authors WHERE name='Дж. К. Роулинг'), 2);

INSERT INTO library_test.authors_has_book(id_author, id_book) VALUES((SELECT id_authors FROM authors WHERE name='Дж. К. Роулинг'), 3);

INSERT INTO library_test.authors_has_book(id_author, id_book) VALUES((SELECT id_authors FROM authors WHERE name='Дж. К. Роулинг'), 4);

INSERT INTO library_test.authors_has_book(id_author, id_book) VALUES((SELECT id_authors FROM authors WHERE name='Дж. К. Роулинг'), 5);

INSERT INTO library_test.authors_has_book(id_author, id_book) VALUES((SELECT id_authors FROM authors WHERE name='Дж. К. Роулинг'), 6);

INSERT INTO library_test.authors_has_book(id_author, id_book) VALUES((SELECT id_authors FROM authors WHERE name='Дж. К. Роулинг'), 7);

INSERT INTO library_test.authors_has_book(id_author, id_book) VALUES((SELECT id_authors FROM authors WHERE name='Дж. К. Роулинг'), 8);

INSERT INTO library_test.genres(genre) VALUES('Исторические');

INSERT INTO library_test.genres(genre) VALUES('Фантастика');

INSERT INTO library_test.genres_has_book(id_genre, id_book) VALUES((SELECT id_genres FROM genres WHERE genre='Исторические'), 1);

INSERT INTO library_test.genres_has_book(id_genre, id_book) VALUES((SELECT id_genres FROM genres WHERE genre='Фантастика'), 2);

INSERT INTO library_test.genres_has_book(id_genre, id_book) VALUES((SELECT id_genres FROM genres WHERE genre='Фантастика'), 3);

INSERT INTO library_test.genres_has_book(id_genre, id_book) VALUES((SELECT id_genres FROM genres WHERE genre='Фантастика'), 4);

INSERT INTO library_test.genres_has_book(id_genre, id_book) VALUES((SELECT id_genres FROM genres WHERE genre='Фантастика'), 5);

INSERT INTO library_test.genres_has_book(id_genre, id_book) VALUES((SELECT id_genres FROM genres WHERE genre='Фантастика'), 6);

INSERT INTO library_test.genres_has_book(id_genre, id_book) VALUES((SELECT id_genres FROM genres WHERE genre='Фантастика'), 7);

insert into library_test.wish_books(id_books, id_users) values(1, 1);

insert into library_test.wish_books(id_books, id_users) values(2, 1);

insert into library_test.wish_books(id_books, id_users) values(3, 1);

insert into library_test.wish_books(id_books, id_users) values(4, 1);

INSERT INTO library_test.orders(id_book, id_user, id_admin, comment, id_library, id_status)
values(1, 1, 0, '-', (SELECT id_library from libraries where city='Минск'), (SELECT id_status from order_statuses where status='opened'));

INSERT INTO library_test.orders(id_book, id_user, id_admin, comment, id_library, id_status)
values(2, 2, 1, '-', (SELECT id_library from libraries where city='Минск'), (SELECT id_status from order_statuses where status='opened'));

INSERT INTO library_test.orders(id_book, id_user, id_admin, comment, id_library, id_status)
values(3, 1, 0, '-', (SELECT id_library from libraries where city='Минск'), (SELECT id_status from order_statuses where status='opened'));

INSERT INTO loan_cards(id_users, taking_book, deadline, type_use, id_book, id_status, id_library)
values(1, '2022-01-01', '2022-02-02', 'at home', 1, (SELECT id_status FROM card_statuses WHERE status='open'), (SELECT id_library FROM libraries WHERE city='Минск'));

INSERT INTO loan_cards(id_users, taking_book, deadline, type_use, id_book, id_status, id_library)
values(1, '2022-01-01', '2022-02-02', 'at home', 2, (SELECT id_status FROM card_statuses WHERE status='open'), (SELECT id_library FROM libraries WHERE city='Минск'));

INSERT INTO loan_cards(id_users, taking_book, deadline, type_use, id_book, id_status, id_library)
values(1, '2022-01-01', '2022-02-02', 'at home', 3, (SELECT id_status FROM card_statuses WHERE status='open'), (SELECT id_library FROM libraries WHERE city='Минск'));
