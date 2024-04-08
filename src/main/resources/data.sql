INSERT INTO project (name, budget) VALUES ('Project Alpha', 50000.00);
INSERT INTO project (name, budget) VALUES ('Project Beta', 100000.00);
INSERT INTO project (name, budget) VALUES ('Project Gamma', 150000.00);
INSERT INTO project (name, budget) VALUES ('Project Delta', 75000.00);

INSERT INTO researcher (name, specialization) VALUES ('Marie Curie', 'Radioactivity');
INSERT INTO researcher (name, specialization) VALUES ('Albert Einstein', 'Relativity');
INSERT INTO researcher (name, specialization) VALUES ('Isaac Newton', 'Classical Mechanics');
INSERT INTO researcher (name, specialization) VALUES ('Niels Bohr', 'Quantum Mechanics');

INSERT INTO researcher_project (researcherId, projectId) VALUES (1, 1);
INSERT INTO researcher_project (researcherId, projectId) VALUES (1, 2);
INSERT INTO researcher_project (researcherId, projectId) VALUES (2, 2);
INSERT INTO researcher_project (researcherId, projectId) VALUES (3, 3);
INSERT INTO researcher_project (researcherId, projectId) VALUES (3, 4);
INSERT INTO researcher_project (researcherId, projectId) VALUES (4, 4);

