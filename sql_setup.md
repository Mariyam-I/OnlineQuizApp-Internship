-- ====================================================
#  Online Quiz Application - Database Setup
-- ====================================================

# This script will:

1) Create the database quizdb

2) Create all tables (users, quizzes, questions, results)

3) Insert three quizzes with 10 questions each

### You can run it directly in MySQL Workbench to set up everything.

========================================================================================================================

### -- 1. Create Database
DROP DATABASE IF EXISTS quizdb;
CREATE DATABASE quizdb;
USE quizdb;


========================================================================================================================

### -- 2. Create Tables

-- Users Table
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    is_admin BOOLEAN DEFAULT FALSE
);

-- Quizzes Table
CREATE TABLE quizzes (
    quiz_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT
);

-- Questions Table
CREATE TABLE questions (
    question_id INT AUTO_INCREMENT PRIMARY KEY,
    quiz_id INT NOT NULL,
    question_text TEXT NOT NULL,
    option_a VARCHAR(255),
    option_b VARCHAR(255),
    option_c VARCHAR(255),
    option_d VARCHAR(255),
    correct_option CHAR(1),
    FOREIGN KEY (quiz_id) REFERENCES quizzes(quiz_id) ON DELETE CASCADE
);

-- Results Table
CREATE TABLE results (
    result_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    quiz_id INT NOT NULL,
    score INT,
    total_questions INT,
    attempt_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (quiz_id) REFERENCES quizzes(quiz_id) ON DELETE CASCADE
);

========================================================================================================================

### -- 3. Insert Sample Quizzes


-- Java Basics Quiz
INSERT INTO quizzes (quiz_id, title, description) VALUES
(1, 'Java Basics', 'Test your knowledge of core Java concepts');

-- OOP Concepts Quiz
INSERT INTO quizzes (quiz_id, title, description) VALUES
(2, 'OOP Concepts', 'Check your understanding of Object-Oriented Programming');

-- SQL Fundamentals Quiz
INSERT INTO quizzes (quiz_id, title, description) VALUES
(3, 'SQL Fundamentals', 'Basic questions on SQL and databases');



========================================================================================================================

### -- 4. Insert Questions

========================================================================================================================
-- Java Basics Quiz Questions
========================================================================================================================

INSERT INTO questions (quiz_id, question_text, option_a, option_b, option_c, option_d, correct_option) VALUES
(1, 'Which keyword is used to inherit a class in Java?', 'implement', 'extends', 'inherits', 'super', 'B'),
(1, 'Which of the following is not a Java primitive type?', 'int', 'float', 'String', 'boolean', 'C'),
(1, 'What does JVM stand for?', 'Java Virtual Machine', 'Java Verified Mode', 'Just Virtual Memory', 'Joint Virtual Module','A'),
(1, 'Which method is the entry point for a Java program?', 'start()', 'main()', 'run()', 'init()', 'B'),
(1, 'Which of these is used to handle exceptions in Java?', 'goto', 'try-catch', 'final', 'error-catch', 'B'),
(1, 'Which of these cannot be used for a variable name in Java?', 'identifier', 'keyword', 'variable', 'none of these', 'B'),
(1, 'Which of these is a wrapper class in Java?', 'int', 'Integer', 'float', 'char', 'B'),
(1, 'Which package contains the Random class?', 'java.util', 'java.lang', 'java.io', 'java.net', 'A'),
(1, 'Which of these statements is true about constructors?', 'They can be abstract', 'They can return values', 'They are invoked when an object is created', 'They must have a void return type', 'C'),
(1, 'Which keyword is used to prevent inheritance of a class?', 'static', 'final', 'const', 'private', 'B');



========================================================================================================================
-- OOP Concepts Quiz Questions
========================================================================================================================

INSERT INTO questions (quiz_id, question_text, option_a, option_b, option_c, option_d, correct_option) VALUES
(2, 'Which concept allows objects to take many forms?', 'Inheritance', 'Polymorphism', 'Encapsulation', 'Abstraction', 'B'),
(2, 'What is encapsulation?', 'Hiding data', 'Inheriting classes', 'Overloading methods', 'Running objects', 'A'),
(2, 'Which keyword is used for inheritance in Java?', 'implements', 'inherits', 'extends', 'super', 'C'),
(2, 'What is an abstract class?', 'A class with no methods', 'A class that cannot be instantiated', 'A class with only private methods', 'A class with main method', 'B'),
(2, 'Which of the following is method overloading?', 'Same method name, different parameters', 'Same method name, same parameters', 'Different method name', 'Overriding main method', 'A'),
(2, 'Which of the following is method overriding?', 'Subclass redefining a superclass method', 'Same method with different name', 'Multiple methods in a class', 'None of these', 'A'),
(2, 'Which access modifier makes a variable accessible only within its class?', 'private', 'public', 'protected', 'default', 'A'),
(2, 'Which of these is an example of polymorphism?', 'Constructor', 'Abstract class', 'Interface', 'Method overloading', 'D'),
(2, 'What is the purpose of the super keyword?', 'Access superclass members', 'Call main method', 'Hide data', 'None of these', 'A'),
(2, 'Which OOP concept is demonstrated by interfaces?', 'Inheritance', 'Polymorphism', 'Abstraction', 'Encapsulation', 'C');



========================================================================================================================
-- SQL Fundamentals Quiz Questions
========================================================================================================================

INSERT INTO questions (quiz_id, question_text, option_a, option_b, option_c, option_d, correct_option) VALUES
(3, 'Which SQL statement is used to fetch data from a table?', 'SELECT', 'FETCH', 'GET', 'SHOW', 'A'),
(3, 'Which SQL keyword is used to remove all records from a table?', 'DELETE', 'DROP', 'TRUNCATE', 'REMOVE', 'C'),
(3, 'Which SQL statement adds a new record?', 'INSERT INTO', 'ADD RECORD', 'UPDATE', 'CREATE', 'A'),
(3, 'Which clause is used to filter results?', 'WHERE', 'HAVING', 'ORDER BY', 'GROUP BY', 'A'),
(3, 'Which keyword is used to sort query results?', 'SORT BY', 'ORDER BY', 'GROUP BY', 'FILTER', 'B'),
(3, 'Which statement is used to modify existing records?', 'MODIFY', 'CHANGE', 'UPDATE', 'ALTER', 'C'),
(3, 'What is the primary key in SQL?', 'Unique identifier for table rows', 'A foreign key', 'Column with NULL values', 'Table name', 'A'),
(3, 'Which of these is used to combine rows from two or more tables?', 'JOIN', 'UNION', 'MERGE', 'LINK', 'A'),
(3, 'Which SQL function is used to count rows?', 'SUM()', 'COUNT()', 'TOTAL()', 'NUMBER()', 'B'),
(3, 'Which keyword is used to remove a table from the database?', 'DELETE TABLE', 'DROP TABLE', 'REMOVE TABLE', 'TRUNCATE TABLE', 'B');
