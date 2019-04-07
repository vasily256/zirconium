/* Организация */
CREATE TABLE IF NOT EXISTS Organization (
    org_id           INTEGER     NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор организации',
    version          INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    name             VARCHAR(50) NOT NULL COMMENT 'Наименование организации',
    full_name        VARCHAR(50) NOT NULL COMMENT 'Полное наименование организации',
    inn              VARCHAR(10) NOT NULL COMMENT 'ИНН, маска [0-9]',
    kpp              VARCHAR(9)  NOT NULL COMMENT 'КПП, маска [0-9]',
    is_active        BOOLEAN     NOT NULL,
    PRIMARY KEY (org_id),
);
COMMENT ON TABLE Organization IS 'Организация';
CREATE INDEX IX_Organization_Org_id ON Organization(org_id);

/* Офис */
CREATE TABLE IF NOT EXISTS Office (
    org_id           INTEGER     NOT NULL COMMENT 'Уникальный идентификатор организации',
    off_id           INTEGER     NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор офиса (1 - головной офис)',
    version          INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    name             VARCHAR(50) NOT NULL COMMENT 'Наименование офиса',
    phone_id         INTEGER              COMMENT 'Уникальный идентификатор телефонного номера',
    is_active        BOOLEAN     NOT NULL,
    PRIMARY KEY (org_id, off_id),
);
COMMENT ON TABLE Office IS 'Офис';
CREATE INDEX IX_Office_Org_id_Off_id ON Office (org_id, off_id);

ALTER TABLE Office ADD FOREIGN KEY (org_id) REFERENCES Organization(org_id);

/* Адрес */
CREATE TABLE IF NOT EXISTS Address (
    org_id           INTEGER     NOT NULL COMMENT 'Уникальный идентификатор организации',
    off_id           INTEGER     NOT NULL COMMENT 'Уникальный идентификатор офиса',
    version          INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    address          VARCHAR(50) NOT NULL COMMENT 'Адрес организации (при off_id = 1) / офиса',
    is_active        BOOLEAN     NOT NULL,
    PRIMARY KEY (org_id, off_id),
);
COMMENT ON TABLE Address IS 'Адрес';
CREATE INDEX IX_Address_Org_id_Off_id ON Office (org_id, off_id);

ALTER TABLE Address ADD FOREIGN KEY (org_id, off_id) REFERENCES Office(org_id, off_id);

/* Пользователь */
CREATE TABLE IF NOT EXISTS User (
    org_id           INTEGER     NOT NULL COMMENT 'Уникальный идентификатор организации',
    off_id           INTEGER     NOT NULL COMMENT 'Уникальный идентификатор офиса',
    usr_id           INTEGER     NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор пользователя',
    version          INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    first_name       VARCHAR(50) NOT NULL COMMENT 'Имя',
    second_name      VARCHAR(50) NOT NULL COMMENT 'Фамилия',
    middle_name      VARCHAR(50) NOT NULL COMMENT 'Отчество',
    position         VARCHAR(50) NOT NULL COMMENT 'Должность',
    phone_id         INTEGER              COMMENT 'Уникальный идентификатор телефонного номера',
    doc_code         VARCHAR(2)  NOT NULL COMMENT 'Код вида документа, маска [0-9]',
    doc_number       VARCHAR(50) NOT NULL COMMENT 'Номер документа',
    doc_date         DATE        NOT NULL COMMENT 'Дата документа',
    citizenship_code VARCHAR(3)  NOT NULL COMMENT 'Код гражданства, маска [0-9]',
    is_identified    BOOLEAN     NOT NULL,
    PRIMARY KEY (org_id, off_id, usr_id),
);
COMMENT ON TABLE User IS 'Пользователь';
CREATE INDEX IX_User_Org_id_Off_id_Usr_id ON User (org_id, off_id, usr_id);

ALTER TABLE User ADD FOREIGN KEY (org_id, off_id) REFERENCES Office(org_id, off_id);

/* Номер телефона */
CREATE TABLE IF NOT EXISTS Phone (
    org_id           INTEGER     NOT NULL COMMENT 'Уникальный идентификатор организации',
    off_id           INTEGER     NOT NULL COMMENT 'Уникальный идентификатор офиса',
    phone_id         INTEGER     NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор телефонного номера',
    version          INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    phone            VARCHAR(15) NOT NULL COMMENT 'Телефонный номер в международном формате, маска [0-9]',
    is_active        BOOLEAN     NOT NULL,
    PRIMARY KEY (org_id, off_id, phone_id),
);
COMMENT ON TABLE Phone IS 'Телефонный номер';
CREATE INDEX IX_Phone_Org_id_Off_id_Phone_id ON Phone (org_id, off_id, phone_id);

ALTER TABLE Phone ADD FOREIGN KEY (org_id, off_id) REFERENCES Office(org_id, off_id);
ALTER TABLE Office ADD FOREIGN KEY (org_id, off_id, phone_id) REFERENCES Phone(org_id, off_id, phone_id);
ALTER TABLE User ADD FOREIGN KEY (org_id, off_id, phone_id) REFERENCES Phone(org_id, off_id, phone_id);

/* Классификатор документов */
CREATE TABLE IF NOT EXISTS Doc (
    code             VARCHAR(2)  NOT NULL COMMENT 'Код вида документа',
    name             VARCHAR(50) NOT NULL COMMENT 'Вид документа',
    PRIMARY KEY (code),
);
COMMENT ON TABLE Doc IS 'Документ';
CREATE INDEX IX_Doc_Code ON Doc(code);

/* Классификатор стран мира */
CREATE TABLE IF NOT EXISTS Country (
    code            VARCHAR(3)  NOT NULL COMMENT 'Код страны',
    name            VARCHAR(50) NOT NULL COMMENT 'Наименование страны',
    PRIMARY KEY (code),
);
COMMENT ON TABLE Country IS 'Страна мира';
CREATE INDEX IX_Country_Code ON Country(code);

ALTER TABLE User ADD FOREIGN KEY (doc_code) REFERENCES Doc(code);
ALTER TABLE User ADD FOREIGN KEY (citizenship_code) REFERENCES Country(code);
