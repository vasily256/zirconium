/* Адрес */
CREATE TABLE IF NOT EXISTS Address (
    id               INTEGER     NOT NULL COMMENT 'Уникальный идентификатор адреса' PRIMARY KEY AUTO_INCREMENT,
    version          INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    address          VARCHAR(50) NOT NULL COMMENT 'Адрес',
);
COMMENT ON TABLE Address IS 'Адрес';
CREATE INDEX IX_Address_Id ON Address(id);

/* Организация */
CREATE TABLE IF NOT EXISTS Organization (
    id               INTEGER     NOT NULL COMMENT 'Уник. идентификатор организации' PRIMARY KEY AUTO_INCREMENT,
    version          INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    address_id       INTEGER     NOT NULL COMMENT 'Уникальный идентификатор адреса',
    name             VARCHAR(50) NOT NULL COMMENT 'Наименование организации',
    full_name        VARCHAR(50) NOT NULL COMMENT 'Полное наименование организации',
    inn              VARCHAR(10) NOT NULL COMMENT 'ИНН, маска [0-9]',
    kpp              VARCHAR(9)  NOT NULL COMMENT 'КПП, маска [0-9]',
    is_active        BOOLEAN     NOT NULL,
    FOREIGN KEY (address_id) REFERENCES Address(id),
);
COMMENT ON TABLE Organization IS 'Организация';
CREATE INDEX IX_Organization_Id ON Organization(id);
CREATE INDEX IX_Organization_Inn ON Organization(inn);
CREATE INDEX IX_Organization_Kpp ON Organization(kpp);

/* Офис */
CREATE TABLE IF NOT EXISTS Office (
    id               INTEGER     NOT NULL COMMENT 'Уник. идентификатор. офиса' PRIMARY KEY AUTO_INCREMENT,
    version          INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    org_id           INTEGER     NOT NULL COMMENT 'Уникальный идентификатор организации',
    address_id       INTEGER     NOT NULL COMMENT 'Уникальный идентификатор адреса',
    name             VARCHAR(50) NOT NULL COMMENT 'Наименование офиса',
    is_active        BOOLEAN     NOT NULL,
    FOREIGN KEY (org_id) REFERENCES Organization(id),
    FOREIGN KEY (address_id) REFERENCES Address(id),
);
COMMENT ON TABLE Office IS 'Офис';
CREATE INDEX IX_Office_Id ON Office (id);
CREATE INDEX IX_Office_Org_id ON Office (org_id);

/* Классификатор документов */
CREATE TABLE IF NOT EXISTS Document (
    id               INTEGER     NOT NULL COMMENT 'Уник. идентификатор вида документа' PRIMARY KEY AUTO_INCREMENT,
    code             VARCHAR(2)  NOT NULL COMMENT 'Код вида документа',
    name             VARCHAR(95) NOT NULL COMMENT 'Вид документа',
    PRIMARY KEY (id),
);
COMMENT ON TABLE Document IS 'Документ';
CREATE INDEX IX_Doc_Id ON Document(id);

/* Классификатор стран мира */
CREATE TABLE IF NOT EXISTS Country (
    id               INTEGER     NOT NULL COMMENT 'Уник. идентификатор страны' PRIMARY KEY AUTO_INCREMENT,
    code             VARCHAR(3)  NOT NULL COMMENT 'Код страны',
    name             VARCHAR(60) NOT NULL COMMENT 'Наименование страны',
    PRIMARY KEY (id),
);
COMMENT ON TABLE Country IS 'Страна мира';
CREATE INDEX IX_Country_Id ON Country(id);

/* Пользователь */
CREATE TABLE IF NOT EXISTS User (
    id               INTEGER     NOT NULL COMMENT 'Уник. идентификатор пользователя' PRIMARY KEY AUTO_INCREMENT,
    version          INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    office_id        INTEGER     NOT NULL COMMENT 'Уникальный идентификатор офиса',
    country_id        INTEGER              COMMENT 'Уникальный идентификатор гражданства',
    doc_id           INTEGER              COMMENT 'Уникальный идентификатор вида документа',
    first_name       VARCHAR(50) NOT NULL COMMENT 'Имя',
    second_name      VARCHAR(50)          COMMENT 'Фамилия',
    middle_name      VARCHAR(50)          COMMENT 'Отчество',
    position         VARCHAR(50) NOT NULL COMMENT 'Должность',
    doc_number       VARCHAR(50)          COMMENT 'Номер документа',
    doc_date         DATE                 COMMENT 'Дата документа',
    is_identified    BOOLEAN     NOT NULL,
    FOREIGN KEY (doc_id) REFERENCES Document(id),
    FOREIGN KEY (country_id) REFERENCES Country(id),
    FOREIGN KEY (office_id) REFERENCES Office(id),
);
COMMENT ON TABLE User IS 'Пользователь';
CREATE INDEX IX_User_Id ON User (id);
CREATE INDEX IX_User_Office_id ON User (office_id);
CREATE INDEX IX_User_Country_id ON User (country_id);
CREATE INDEX IX_User_Doc_id ON User (doc_id);

/* Номер телефона */
CREATE TABLE IF NOT EXISTS Phone (
    id               INTEGER     NOT NULL COMMENT 'Уник. идентификатор тел. номера' PRIMARY KEY AUTO_INCREMENT,
    version          INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    phone            VARCHAR(15) NOT NULL COMMENT 'Телефонный номер в международном формате, маска [0-9]',
);
COMMENT ON TABLE Phone IS 'Телефонный номер';
CREATE INDEX IX_Phone_Id ON Phone (id);

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
