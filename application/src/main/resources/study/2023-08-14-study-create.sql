CREATE DATABASE honey_mate_dev char set utf8mb4 collate utf8mb4_unicode_ci;

CREATE TABLE honey_mate_dev.study (
      id INT AUTO_INCREMENT PRIMARY KEY,
      title VARCHAR(100) NOT NULL,
      subtitle VARCHAR(255),
      description TEXT,
      location VARCHAR(100) NOT NULL,
      running_time VARCHAR(50) NOT NULL,
      running_period VARCHAR(50) NOT NULL,
      number_of_recruits INTEGER NOT NULL,
      created_at TIMESTAMP NOT NULL,
      modified_at TIMESTAMP NOT NULL
);