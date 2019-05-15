/* Генератор первичного ключа адреса */
CREATE SEQUENCE IF NOT EXISTS Address_sequence START WITH 5;

/* Организация */
CREATE TABLE IF NOT EXISTS Organization (
    id               INTEGER     NOT NULL COMMENT 'Уник. идентификатор организации' PRIMARY KEY,
    version          INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    name             VARCHAR(50) NOT NULL COMMENT 'Наименование организации',
    full_name        VARCHAR(50) NOT NULL COMMENT 'Полное наименование организации',
    inn              VARCHAR(10) NOT NULL COMMENT 'ИНН, маска [0-9]',
    kpp              VARCHAR(9)  NOT NULL COMMENT 'КПП, маска [0-9]',
    address          VARCHAR(50) NOT NULL COMMENT 'Адрес',
    is_active        BOOLEAN     NOT NULL,
);
COMMENT ON TABLE Organization IS 'Организация';
CREATE INDEX IX_Organization_Id ON Organization(id);
CREATE INDEX IX_Organization_Inn ON Organization(inn);
CREATE INDEX IX_Organization_Kpp ON Organization(kpp);

/* Генератор первичного ключа организации */
CREATE SEQUENCE IF NOT EXISTS Organization_sequence START WITH 3;

/* Офис */
CREATE TABLE IF NOT EXISTS Office (
    id               INTEGER     NOT NULL COMMENT 'Уник. идентификатор. офиса' PRIMARY KEY,
    version          INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    org_id           INTEGER     NOT NULL COMMENT 'Уникальный идентификатор организации',
    name             VARCHAR(50)          COMMENT 'Наименование офиса',
    address          VARCHAR(50)          COMMENT 'Адрес',
    is_active        BOOLEAN,
    FOREIGN KEY (org_id) REFERENCES Organization(id),
);
COMMENT ON TABLE Office IS 'Офис';
CREATE INDEX IX_Office_Id ON Office (id);
CREATE INDEX IX_Office_Org_id ON Office (org_id);

/* Генератор первичного ключа офиса */
CREATE SEQUENCE IF NOT EXISTS Office_sequence START WITH 7;

/* Страна мира */
CREATE TABLE IF NOT EXISTS Country (
    id               INTEGER     NOT NULL COMMENT 'Уник. идентификатор страны' PRIMARY KEY,
    code             VARCHAR(3)  NOT NULL COMMENT 'Код страны',
    name             VARCHAR(60) NOT NULL COMMENT 'Наименование страны',
);
COMMENT ON TABLE Country IS 'Страна мира';
CREATE INDEX IX_Country_Id ON Country(id);
CREATE INDEX IX_Country_Code ON Country(code);

/* Генератор первичного ключа страны (на случай актуализации справочника стран мира) */
CREATE SEQUENCE IF NOT EXISTS Country_sequence START WITH 250;

/* Вид документа */
CREATE TABLE IF NOT EXISTS Document_Type (
    id               INTEGER     NOT NULL COMMENT 'Уник. идентификатор вида документа' PRIMARY KEY,
    code             VARCHAR(2)  NOT NULL COMMENT 'Код вида документа',
    name             VARCHAR(95) NOT NULL COMMENT 'Вид документа',
);
COMMENT ON TABLE Document_Type IS 'Документ';
CREATE INDEX IX_Document_Type_Id ON Document_Type(id);
CREATE INDEX IX_Document_Type_Code ON Document_Type(code);

/* Генератор первичного ключа вида документа (на случай актуализации справочника видов документа) */
CREATE SEQUENCE IF NOT EXISTS Document_Type_sequence START WITH 16;

/* Пользователь */
CREATE TABLE IF NOT EXISTS User (
    id               INTEGER     NOT NULL COMMENT 'Уник. идентификатор пользователя' PRIMARY KEY,
    version          INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    office_id        INTEGER     NOT NULL COMMENT 'Уникальный идентификатор офиса',
    country_id       INTEGER              COMMENT 'Уникальный идентификатор гражданства',
    first_name       VARCHAR(50) NOT NULL COMMENT 'Имя',
    second_name      VARCHAR(50)          COMMENT 'Фамилия',
    middle_name      VARCHAR(50)          COMMENT 'Отчество',
    position         VARCHAR(50) NOT NULL COMMENT 'Должность',
    FOREIGN KEY (country_id) REFERENCES Country(id),
    FOREIGN KEY (office_id) REFERENCES Office(id),
);
COMMENT ON TABLE User IS 'Пользователь';
CREATE INDEX IX_User_Id ON User (id);
CREATE INDEX IX_User_Office_id ON User (office_id);
CREATE INDEX IX_User_Country_id ON User (country_id);

/* Генератор первичного ключа пользователя */
CREATE SEQUENCE IF NOT EXISTS User_sequence START WITH 16;

/* Документ пользователя */
CREATE TABLE IF NOT EXISTS Document (
    user_id          INTEGER     NOT NULL COMMENT 'Уникальный идентификатор пользователя' PRIMARY KEY,
    version          INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    doc_type_id      INTEGER              COMMENT 'Уникальный идентификатор вида документа',
    doc_number       VARCHAR(50)          COMMENT 'Номер документа',
    doc_date         DATE                 COMMENT 'Дата документа',
    is_identified    BOOLEAN,
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (doc_type_id) REFERENCES Document_Type(id),
);
COMMENT ON TABLE Document IS 'Документ';
CREATE INDEX IX_Document_User_id ON Document(user_id);
CREATE INDEX IX_Document_Doc_type_id ON Document(doc_type_id);

/* Номер телефона */
CREATE TABLE IF NOT EXISTS Phone (
    id               INTEGER     NOT NULL COMMENT 'Уник. идентификатор тел. номера' PRIMARY KEY,
    version          INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    phone            VARCHAR(15) NOT NULL COMMENT 'Телефонный номер в международном формате, маска [0-9]',
);
COMMENT ON TABLE Phone IS 'Телефонный номер';
CREATE INDEX IX_Phone_Id ON Phone (id);

/* Генератор первичного ключа номера телефона */
CREATE SEQUENCE IF NOT EXISTS Phone_sequence START WITH 17;

/* Join-таблица для связи Organization и Phone */
CREATE TABLE IF NOT EXISTS Organization_Phone (
    org_id           INTEGER     NOT NULL COMMENT 'Уникальный идентификатор организации',
    phone_id         INTEGER     NOT NULL COMMENT 'Уникальный идентификатор телефонного номера',
    PRIMARY KEY (org_id, phone_id),
    FOREIGN KEY (org_id) REFERENCES Organization(id),
    FOREIGN KEY (phone_id) REFERENCES Phone(id),
);
COMMENT ON TABLE Organization_Phone IS 'Join-таблица для связи Organization и Phone';
CREATE INDEX IX_Phone_Organization_Id ON Organization_Phone (org_id);
CREATE INDEX IX_Organization_Phone_Id ON Organization_Phone (phone_id);

/* Join-таблица для связи Office и Phone */
CREATE TABLE IF NOT EXISTS Office_Phone (
    office_id        INTEGER     NOT NULL COMMENT 'Уникальный идентификатор офиса',
    phone_id         INTEGER     NOT NULL COMMENT 'Уникальный идентификатор телефонного номера',
    PRIMARY KEY (office_id, phone_id),
    FOREIGN KEY (office_id) REFERENCES Office(id),
    FOREIGN KEY (phone_id) REFERENCES Phone(id),
);
COMMENT ON TABLE Office_Phone IS 'Join-таблица для связи Office и Phone';
CREATE INDEX IX_Phone_Office_Id ON Office_Phone (office_id);
CREATE INDEX IX_Office_Phone_Id ON Office_Phone (phone_id);

/* Join-таблица для связи User и Phone */
CREATE TABLE IF NOT EXISTS User_Phone (
    user_id          INTEGER     NOT NULL COMMENT 'Уникальный идентификатор пользователя',
    phone_id         INTEGER     NOT NULL COMMENT 'Уникальный идентификатор телефонного номера',
    PRIMARY KEY (user_id, phone_id),
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (phone_id) REFERENCES Phone(id),
);
COMMENT ON TABLE User_Phone IS 'Join-таблица для связи User и Phone';
CREATE INDEX IX_Phone_User_Id ON User_Phone (user_id);
CREATE INDEX IX_User_Phone_Id ON User_Phone (phone_id);
