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

UPDATE projects SET date = '2021-07-10' WHERE id = 1;
UPDATE projects SET date = '2018-01-11' WHERE id = 2;
UPDATE projects SET date = '2021-11-01' WHERE id = 3;
UPDATE projects SET date = '2021-11-21' WHERE id = 4;
UPDATE projects SET date = '2020-10-07' WHERE id = 5;
UPDATE projects SET date = '2022-01-15' WHERE id = 6;