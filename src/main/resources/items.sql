INSERT INTO Person(name, age, email) VALUES ('Tom', 25, 'tom@mail.ru');
INSERT INTO Person(name, age, email) VALUES ('Bob', 31, 'bob1@mail.ru');
INSERT INTO person(name, age, email) VALUES ('Bob', 53, 'bob2@mail.ru');
INSERT INTO person(name, age, email) VALUES ('Bob', 20, 'bob3@mail.ru');
INSERT INTO person(name, age, email) VALUES ('Katy', 14, 'katy@mail.ru');

CREATE TABLE Item (
    id int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    person_id int REFERENCES Person(id) ON DELETE SET NULL,
    name varchar(100) NOT NULL UNIQUE
);

INSERT INTO Item(person_id, name) VALUES (3, 'Airpods');
INSERT INTO Item(person_id, name) VALUES (3, 'Playstation');
INSERT INTO Item(person_id, name) VALUES (3, 'TV');