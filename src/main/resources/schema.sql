/* Организация */
CREATE TABLE IF NOT EXISTS Organization (
    id               INTEGER     NOT NULL COMMENT 'Уникальный идентификатор организации' PRIMARY KEY AUTO_INCREMENT,
    version          INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    name             VARCHAR(50) NOT NULL COMMENT 'Наименование организации',
    full_name        VARCHAR(50) NOT NULL COMMENT 'Полное наименование организации',
    inn              VARCHAR(10) NOT NULL COMMENT 'ИНН, маска [0-9]',
    kpp              VARCHAR(9)  NOT NULL COMMENT 'КПП, маска [0-9]',
    is_active        BOOLEAN     NOT NULL,
);
COMMENT ON TABLE Organization IS 'Организация';
CREATE INDEX IX_Organization_Id ON Organization(id);

/* Офис */
CREATE TABLE IF NOT EXISTS Office (
    id               INTEGER     NOT NULL COMMENT 'Уник. идент. офиса (1 - головной офис)' PRIMARY KEY AUTO_INCREMENT,
    version          INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    org_id           INTEGER     NOT NULL COMMENT 'Уникальный идентификатор организации',
    name             VARCHAR(50) NOT NULL COMMENT 'Наименование офиса',
    address          VARCHAR(50) NOT NULL COMMENT 'Адрес',
    is_active        BOOLEAN     NOT NULL,
);
COMMENT ON TABLE Office IS 'Офис';
CREATE INDEX IX_Office_Id ON Office (id);

ALTER TABLE Office ADD FOREIGN KEY (org_id) REFERENCES Organization(id);

/* Пользователь */
CREATE TABLE IF NOT EXISTS User (
    id               INTEGER     NOT NULL COMMENT 'Уникальный идентификатор пользователя' PRIMARY KEY AUTO_INCREMENT,
    version          INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    office_id        INTEGER     NOT NULL COMMENT 'Уникальный идентификатор офиса',
    first_name       VARCHAR(50) NOT NULL COMMENT 'Имя',
    second_name      VARCHAR(50)          COMMENT 'Фамилия',
    middle_name      VARCHAR(50)          COMMENT 'Отчество',
    position         VARCHAR(50) NOT NULL COMMENT 'Должность',
    doc_code         VARCHAR(2)           COMMENT 'Код вида документа, маска [0-9]',
    doc_number       VARCHAR(50)          COMMENT 'Номер документа',
    doc_date         DATE                 COMMENT 'Дата документа',
    citizenship_code VARCHAR(3)  NOT NULL COMMENT 'Код гражданства, маска [0-9]',
    is_identified    BOOLEAN     NOT NULL,
);
COMMENT ON TABLE User IS 'Пользователь';
CREATE INDEX IX_User_Id ON User (id);

ALTER TABLE User ADD FOREIGN KEY (office_id) REFERENCES Office(id);

/* Номер телефона */
CREATE TABLE IF NOT EXISTS Phone (
    id               INTEGER     NOT NULL COMMENT 'Уникальный идентиф. телефонного номера' PRIMARY KEY AUTO_INCREMENT,
    version          INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    phone            VARCHAR(15) NOT NULL COMMENT 'Телефонный номер в международном формате, маска [0-9]',
);
COMMENT ON TABLE Phone IS 'Телефонный номер';
CREATE INDEX IX_Phone_Id ON Phone (id);

/* Join-таблица для связи Office и Phone */
CREATE TABLE IF NOT EXISTS Office_Phone (
    office_id        INTEGER     NOT NULL COMMENT 'Уникальный идентификатор офиса',
    phone_id         INTEGER     NOT NULL COMMENT 'Уникальный идентификатор телефонного номера',
    PRIMARY KEY (office_id, phone_id),
);
COMMENT ON TABLE Office_Phone IS 'Join-таблица для связи Office и Phone';
CREATE INDEX IX_Phone_Office_Id ON Office_Phone (office_id);

ALTER TABLE Office_Phone ADD FOREIGN KEY (phone_id) REFERENCES Phone(id);
ALTER TABLE Office_Phone ADD FOREIGN KEY (office_id) REFERENCES Office(id);

/* Join-таблица для связи User и Phone */
CREATE TABLE IF NOT EXISTS User_Phone (
    user_id          INTEGER     NOT NULL COMMENT 'Уникальный идентификатор пользователя',
    phone_id         INTEGER     NOT NULL COMMENT 'Уникальный идентификатор телефонного номера',
    PRIMARY KEY (user_id, phone_id),
);
COMMENT ON TABLE User_Phone IS 'Join-таблица для связи User и Phone';
CREATE INDEX IX_Phone_User_Id ON User_Phone (user_id);

ALTER TABLE User_Phone ADD FOREIGN KEY (phone_id) REFERENCES Phone(id);
ALTER TABLE User_Phone ADD FOREIGN KEY (user_id) REFERENCES User(id);

/* Классификатор документов */
CREATE TABLE IF NOT EXISTS Document (
    code             VARCHAR(2)  NOT NULL COMMENT 'Код вида документа',
    name             VARCHAR(50) NOT NULL COMMENT 'Вид документа',
    PRIMARY KEY (code),
);
COMMENT ON TABLE Document IS 'Документ';
CREATE INDEX IX_Doc_Code ON Document(code);

ALTER TABLE User ADD FOREIGN KEY (doc_code) REFERENCES Document(code);

/* Классификатор стран мира */
CREATE TABLE IF NOT EXISTS Country (
    code             VARCHAR(3)  NOT NULL COMMENT 'Код страны',
    name             VARCHAR(50) NOT NULL COMMENT 'Наименование страны',
    PRIMARY KEY (code),
);
COMMENT ON TABLE Country IS 'Страна мира';
CREATE INDEX IX_Country_Code ON Country(code);

ALTER TABLE User ADD FOREIGN KEY (citizenship_code) REFERENCES Country(code);
