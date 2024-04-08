create table if not exists project(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(255),
    budget double
);

create table if not exists researcher(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(255),
    specialization varchar(255)
);

create table if not exists researcher_project(
    projectId INT,
    researcherId INT,
    PRIMARY KEY (projectId, researcherId),
    FOREIGN KEY (projectId) REFERENCES project(id),
    FOREIGN KEY (researcherId) REFERENCES researcher(id)
);