CREATE table developers (
	id serial not null primary key,
	first_name varchar(50),
	last_name varchar(50),
	age int,
	gender varchar(10),
	mail varchar(100)
);

CREATE table skills (
	id serial not null primary key,
	industry varchar(50),
	skill_level varchar(50)
);

CREATE table developers_skills (
	developer_id int not null,
	skill_id int not null,
	UNIQUE (developer_id, skill_id),
	constraint developer_id_fk foreign key (developer_id) references developers (id),
	constraint skill_id_fk foreign key (skill_id) references skills (id)
);

CREATE table companies (
	id serial not null primary key,
	name varchar(100),
	description varchar(500),
	number_of_employees int
);

ALTER table developers
	ADD company_id int,
	ADD foreign key (company_id) references companies (id);

CREATE table customers (
	id serial not null primary key,
	name varchar(50),
	business varchar(250)
);

CREATE table projects (
	id serial not null primary key,
	name varchar(100),
	description varchar(500)
);

ALTER TABLE projects
	ADD company_id int,
	ADD foreign key (company_id) references companies (id);

ALTER TABLE projects
	ADD customer_id int,
	ADD foreign key (customer_id) references customers (id);

CREATE TABLE developers_projects (
	developer_id int not null,
	project_id int not null,
	UNIQUE (developer_id, project_id),
	FOREIGN KEY (developer_id) REFERENCES developers (id),
	FOREIGN KEY (project_id) REFERENCES projects (id)
);

ALTER TABLE developers
	ADD salary int;

ALTER TABLE projects
	ADD cost int;

ALTER TABLE projects
	ADD date DATE;

INSERT INTO companies (name, description, number_of_employees)
	 VALUES ('EPAM', 'С 1993 года EPAM помогает мировым лидерам проектировать,
			разрабатывать и внедрять изменяющее мир мировое программное обеспечение.', 11600),
			('SoftServe', 'SoftServe — ведущая ИТ-компания,
			 занимающаяся консалтингом и предоставляющая услуги в сфере цифровых технологий.', 9462),
			('GlobalLogic', 'GlobalLogic, компания группы Hitachi,
			 является лидером в сфере услуг по разработке цифровых продуктов.', 6365),
			('Luxoft', 'Luxoft — подразделение DXC Technology по проектированию,
			 обработке данных и разработке.', 3581);

INSERT INTO skills (id, industry, skill_level)
	 VALUES (11, 'Java', 'Junior'),
			(12, 'Java', 'Middle'),
			(13, 'Java', 'Senior'),
			(21, 'C++', 'Junior'),
			(22, 'C++', 'Middle'),
			(23, 'C++', 'Senior'),
			(31, 'C#', 'Junior'),
			(32, 'C#', 'Middle'),
			(33, 'C#', 'Senior'),
			(41, 'JS', 'Junior'),
			(42, 'JS', 'Middle'),
			(43, 'JS', 'Senior');

INSERT INTO customers (name, business)
	 VALUES ('Ilon Mask', 'Tesla, SpaceX'),
	 		('Jeff Bezos', 'Amazon'),
			('Bill Gates', 'Microsoft');

INSERT INTO developers (first_name, last_name, age, gender, mail, company_id)
	 VALUES ('Ivan', 'Petrov', 37, 'male', 'petrov@gmail.com', 1),
			('Victor', 'Sidorov', 32, 'male', 'sidorov@gmail.com', 2),
			('Dmitriy', 'Ivanov', 28, 'male', 'ivanov@mail.com', 1),
			('Irina', 'Motkova', 25, 'female', 'motkova@i.ua', 3),
			('Roman', 'Dorov', 29, 'male', 'dorov@gmail.com', 4),
			('Sergey', 'Kobzar', 35, 'male', 'kobzar@gmail.com', 2),
			('Olga', 'Orlova', 32, 'female', 'orlova@mail.com', 4);

INSERT INTO developers_skills
	 VALUES (1, 13), (1, 33), (1, 42), (2, 13), (2, 22), (3, 11), (4, 43), (5, 12), (5, 33), (6, 11), (7, 12);

INSERT INTO projects (name, description, company_id, customer_id)
	 VALUES ('Search mobile app', 'Mobile application for product search', 1, 2),
	 		('Delivery app', 'Mobile application for product delivery', 2, 2),
			('Car control panel', 'Software for Model Z', 2, 1),
			('Autopilot', 'Software for Model Z', 3, 1),
			('Mobile Windows app', 'Mobile application for Windows', 4, 3),
			('Favorite books', 'Mobile application for books', 4, 3);

INSERT INTO developers_projects
	VALUES (1, 1), (3, 1), (2, 3), (2, 2), (4, 4), (5, 6), (5, 5), (6, 3), (6, 2), (7, 6), (7, 5);

UPDATE developers SET salary = 6000
	WHERE id IN (1);
UPDATE developers SET salary = 5200
	WHERE id IN (2);
UPDATE developers SET salary = 1300
	WHERE id IN (3);
UPDATE developers SET salary = 4500
	WHERE id IN (4);
UPDATE developers SET salary = 4000
	WHERE id IN (5);
UPDATE developers SET salary = 1500
	WHERE id IN (6);
UPDATE developers SET salary = 3000
	WHERE id IN (7);

UPDATE projects SET cost = 7300
	WHERE name IN ('Search mobile app');
UPDATE projects SET cost = 7000
	WHERE name IN ('Favorite books');
UPDATE projects SET cost = 7000
	WHERE name IN ('Mobile Windows app');
UPDATE projects SET cost = 6700
	WHERE name IN ('Delivery app');
UPDATE projects SET cost = 6700
	WHERE name IN ('Car control panel');
UPDATE projects SET cost = 4500
	WHERE name IN ('Autopilot');

UPDATE projects SET date = '2021-07-10' WHERE id = 1;
UPDATE projects SET date = '2018-01-11' WHERE id = 2;
UPDATE projects SET date = '2021-11-01' WHERE id = 3;
UPDATE projects SET date = '2021-11-21' WHERE id = 4;
UPDATE projects SET date = '2020-10-07' WHERE id = 5;
UPDATE projects SET date = '2022-01-15' WHERE id = 6;

ALTER TABLE companies RENAME COLUMN id TO company_id;
ALTER TABLE companies RENAME COLUMN name TO company_name;
ALTER TABLE customers RENAME COLUMN id TO customer_id;
ALTER TABLE customers RENAME COLUMN name TO customer_name;