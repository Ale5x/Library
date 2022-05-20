INSERT INTO library.role(role) VALUES('user'),('admin'),('manager');
INSERT INTO library.user_statuses(status) VALUES('active'),('blocked'),('delete');
INSERT INTO library.users(id_status, id_role, password, first_name, last_name, email, count_violations) VALUES(3, 1, 'F662834B04A38856A3F36BD7BA99E5C3', 'General', 'Manager', 'admin_library@gmail.com', 0);

INSERT INTO library.order_statuses(status) VALUES('opened'),('approved'),('rejected'),('arrived'),('closed');

INSERT INTO library.library_statuses(status) VALUES('opened'),('closed');
INSERT INTO library.libraries(city, street, id_status)
	VALUES('Минск', 'пр. Независимости 116', 1),
		  ('Гомель', 'пл. Ленина 1', 1),
		  ('Брест', 'ул. Героев обороны Брестской крепости 60', 2);

INSERT INTO library.card_statuses(status) VALUES('open'),('closed');

INSERT INTO library.books(shelf, title, quantity, borrow, publisher, year, isbn, id_library, description)
    VALUES('1-S4', 'История Рима от основания города Т1', 8, 0, 'Наука', 1989, '5-02-008995-8', 1,
           'Основное произведение Тита Ливия, один из самых известных и фундаментальных трудов по истории Древнего Рима. Охватывает периоды от разрушения Трои до 9 года до н. э. (до смерти Друза Старшего включительно).');

INSERT INTO library.authors(name) VALUES('Тит Ливий');

INSERT INTO library.genres(genre) VALUES('Исторические'), ('Фантастика'), ('Детектив'), ('Приключения'), ('Научная');

INSERT INTO library.genres_has_book(id_genre, id_book) VALUES((SELECT id_genres FROM genres WHERE genre='Исторические'), 1);

INSERT INTO library.authors_has_book(id_author, id_book) VALUES((SELECT id_authors FROM authors WHERE name='Тит Ливий'), 1);
