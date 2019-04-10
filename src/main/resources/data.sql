/* Адрес */
INSERT INTO Address (id, version, address)
VALUES (1, 0, 'Россия, г. Новосибирск, ул. Почтовая, 6');
INSERT INTO Address (id, version, address)
VALUES (2, 0, 'Россия, г. Владивосток, ул. Приморская, 5');
INSERT INTO Address (id, version, address)
VALUES (3, 0, 'Россия, г. Москва, ул. Заречная, 1');
INSERT INTO Address (id, version, address)
VALUES (4, 0, 'Китай, г. Шанхай, ул. Цзуй Цзинь, 8');

/* Организация */
INSERT INTO Organization (id, version, address_id, name, full_name, inn, kpp, is_active)
VALUES (1, 0, 1, 'АО НПП Позитрон', 'Акционерное общество НПП Позитрон', '2983185638', '937461004', true);
INSERT INTO Organization (id, version, address_id, name, full_name, inn, kpp, is_active)
VALUES (2, 0, 2, 'АО Развитие', 'Акционерное общество Развитие', '7700441507', '772225140', true);

/* Офис */
INSERT INTO Office (id, version, org_id, address_id, name, is_active)
VALUES (1, 0, 1, 1, 'Научный центр', true);
INSERT INTO Office (id, version, org_id, address_id, name, is_active)
VALUES (2, 0, 1, 2, 'Производственный центр', true);
INSERT INTO Office (id, version, org_id, address_id, name, is_active)
VALUES (3, 0, 2, 3, 'Штаб-квартира', true);
INSERT INTO Office (id, version, org_id, address_id, name, is_active)
VALUES (4, 0, 2, 4, 'Филиал г. Шанхай, ул. Пиньинь', true);

/* Документ */
INSERT INTO Document (id, code, name)
VALUES (4, '10', 'Паспорт иностранного гражданина');
INSERT INTO Document (id, code, name)
VALUES (10, '21', 'Паспорт гражданина Российской Федерации');

/* Страна */
INSERT INTO Country (id, code, name)
VALUES (34, '112', 'Республика Беларусь');
INSERT INTO Country (id, code, name)
VALUES (44, '156', 'Китайская народная республика');
INSERT INTO Country (id, code, name)
VALUES (185, '643', 'Российская Федерация');
INSERT INTO Country (id, code, name)
VALUES (199, '688', 'Республика Сербия');

/* Пользователь */
INSERT INTO User (id, version, office_id, country_id, doc_id, first_name, second_name, middle_name, position, doc_number, doc_date, is_identified)
VALUES (1, 0, 1, 185, 10, 'Федор', 'Николаевич', 'Прокофьев', 'Генеральный директор', '422898333', '2010-01-20', true);
INSERT INTO User (id, version, office_id, country_id, doc_id, first_name, second_name, middle_name, position, doc_number, doc_date, is_identified)
VALUES (2, 0, 1, 185, 10, 'Дмитрий', 'Игоревич', 'Иванов', 'Технический директор', '94738934', '2003-01-20', true);
INSERT INTO User (id, version, office_id, country_id, doc_id, first_name, second_name, middle_name, position, doc_number, doc_date, is_identified)
VALUES (3, 0, 1, 185, 10, 'Вера', 'Александровна', 'Кузнецова', 'Разработчик', '2802154125', '2009-01-30', true);
INSERT INTO User (id, version, office_id, country_id, doc_id, first_name, second_name, middle_name, position, doc_number, doc_date, is_identified)
VALUES (4, 0, 1, 199, 10, 'Владислав', 'Владиславович', 'Стоянович', 'Научный сотрудник', '2424242154', '2005-01-20', true);
INSERT INTO User (id, version, office_id, country_id, doc_id, first_name, second_name, middle_name, position, doc_number, doc_date, is_identified)
VALUES (5, 0, 1, 34, 4, 'Владимир', 'Константинович', 'Константиновский', 'Программист', '235354366', '2009-01-20', true);
INSERT INTO User (id, version, office_id, country_id, doc_id, first_name, second_name, middle_name, position, doc_number, doc_date, is_identified)
VALUES (6, 0, 2, 185, 10, 'Никита', 'Алексеевич', 'Дмитриев', 'Главный инженер', '122354777836', '2014-05-20', true);
INSERT INTO User (id, version, office_id, country_id, doc_id, first_name, second_name, middle_name, position, doc_number, doc_date, is_identified)
VALUES (7, 0, 2, 185, 10, 'Илья', 'Кузьмич', 'Федоров', 'Слесарь', '25625736526', '2009-01-20', true);
INSERT INTO User (id, version, office_id, country_id, doc_id, first_name, second_name, middle_name, position, doc_number, doc_date, is_identified)
VALUES (8, 0, 2, 185, 10, 'Мария', 'Ивановна', 'Сидоренко', 'Заведующая складом', '242245621', '2010-05-25', true);
INSERT INTO User (id, version, office_id, country_id, doc_id, first_name, second_name, middle_name, position, doc_number, doc_date, is_identified)
VALUES (9, 0, 3, 185, 10, 'Иван', 'Петрович', 'Сидоров', 'Генеральный директор', '475465553', '2000-08-17', true);
INSERT INTO User (id, version, office_id, country_id, doc_id, first_name, second_name, middle_name, position, doc_number, doc_date, is_identified)
VALUES (10, 0, 3, 185, 10, 'Ирина', 'Владимировна', 'Карандашева', 'Секретарь', '54091759475', '2002-02-22', true);
INSERT INTO User (id, version, office_id, country_id, doc_id, first_name, second_name, middle_name, position, doc_number, doc_date, is_identified)
VALUES (11, 0, 3, 185, 10, 'Михал', 'Павлович', 'Крылов', 'Менеджер', '290759076', '2004-11-09', true);
INSERT INTO User (id, version, office_id, country_id, doc_id, first_name, second_name, position, is_identified)
VALUES (12, 0, 4, 44, 4, 'Цзиньхуа', 'Ху', 'Технолог', true);
INSERT INTO User (id, version, office_id, country_id, doc_id, first_name, second_name, position, is_identified)
VALUES (13, 0, 4, 44, 4, 'Вэйдун', 'Ли', 'Инженер', true);
INSERT INTO User (id, version, office_id, country_id, doc_id, first_name, second_name, position, is_identified)
VALUES (14, 0, 4, 44, 4, 'Цзиньсун', 'Чжан', 'Механик', true);

/* Телефон */
INSERT INTO Phone (id, version, phone)
VALUES (1, 0, '77903332211');
INSERT INTO Phone (id, version, phone)
VALUES (2, 0, '79343330001');
INSERT INTO Phone (id, version, phone)
VALUES (3, 0, '72223777711');
INSERT INTO Phone (id, version, phone)
VALUES (4, 0, '78492371111');
INSERT INTO Phone (id, version, phone)
VALUES (5, 0, '78444442662');
INSERT INTO Phone (id, version, phone)
VALUES (6, 0, '78492373333');
INSERT INTO Phone (id, version, phone)
VALUES (7, 0, '86445551530');
INSERT INTO Phone (id, version, phone)
VALUES (8, 0, '78492371577');
INSERT INTO Phone (id, version, phone)
VALUES (9, 0, '78444440845');
INSERT INTO Phone (id, version, phone)
VALUES (10, 0, '72776446650');
INSERT INTO Phone (id, version, phone)
VALUES (11, 0, '71296305493');
INSERT INTO Phone (id, version, phone)
VALUES (12, 0, '74153396765');
INSERT INTO Phone (id, version, phone)
VALUES (13, 0, '72153071780');
INSERT INTO Phone (id, version, phone)
VALUES (14, 0, '75307133330');
INSERT INTO Phone (id, version, phone)
VALUES (15, 0, '86663753620');

/* Организация - телефон */
INSERT INTO Organization_Phone (org_id, phone_id)
VALUES (1, 1);
INSERT INTO Organization_Phone (org_id, phone_id)
VALUES (1, 2);
INSERT INTO Organization_Phone (org_id, phone_id)
VALUES (2, 3);

/* Офис - телефон */
INSERT INTO Office_Phone (office_id, phone_id)
VALUES (1, 1);
INSERT INTO Office_Phone (office_id, phone_id)
VALUES (2, 4);
INSERT INTO Office_Phone (office_id, phone_id)
VALUES (3, 5);
INSERT INTO Office_Phone (office_id, phone_id)
VALUES (3, 6);
INSERT INTO Office_Phone (office_id, phone_id)
VALUES (4, 7);

/* Пользователь - телефон */
INSERT INTO User_Phone (user_id, phone_id)
VALUES (1, 1);
INSERT INTO User_Phone (user_id, phone_id)
VALUES (1, 2);
INSERT INTO User_Phone (user_id, phone_id)
VALUES (2, 8);
INSERT INTO User_Phone (user_id, phone_id)
VALUES (3, 9);
INSERT INTO User_Phone (user_id, phone_id)
VALUES (4, 10);
INSERT INTO User_Phone (user_id, phone_id)
VALUES (5, 9);
INSERT INTO User_Phone (user_id, phone_id)
VALUES (6, 11);
INSERT INTO User_Phone (user_id, phone_id)
VALUES (8, 12);
INSERT INTO User_Phone (user_id, phone_id)
VALUES (9, 3);
INSERT INTO User_Phone (user_id, phone_id)
VALUES (10, 3);
INSERT INTO User_Phone (user_id, phone_id)
VALUES (10, 5);
INSERT INTO User_Phone (user_id, phone_id)
VALUES (11, 13);
INSERT INTO User_Phone (user_id, phone_id)
VALUES (11, 14);
INSERT INTO User_Phone (user_id, phone_id)
VALUES (12, 15);
