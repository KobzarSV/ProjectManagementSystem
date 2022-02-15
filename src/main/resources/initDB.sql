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

ALTER TABLE projects
	ADD date DATE;