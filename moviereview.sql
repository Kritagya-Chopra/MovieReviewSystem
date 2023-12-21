
CREATE TABLE users(
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(20),
last_name VARCHAR(20),
email VARCHAR(40) UNIQUE,
mobile CHAR(12),
birth DATE,
password VARCHAR(100)
);

CREATE TABLE movies(
id INT PRIMARY KEY AUTO_INCREMENT,
title VARCHAR(40) UNIQUE,
rel_date DATE
);

CREATE TABLE reviews(
id INT PRIMARY KEY AUTO_INCREMENT,
movie_id INT,
review VARCHAR(1024),
rating INT,
user_id INT,
modified TIMESTAMP,
FOREIGN KEY (user_id) REFERENCES users(id),
FOREIGN KEY (movie_id) REFERENCES movies(id),
UNIQUE (user_id, movie_id)
);

CREATE TABLE shares(
review_id INT,
user_id INT,
FOREIGN KEY (review_id) REFERENCES reviews(id) ON DELETE CASCADE,
FOREIGN KEY (user_id) REFERENCES users(id),
PRIMARY KEY (review_id,user_id)
);

INSERT INTO users VALUES(default, 'Nilesh', 'Ghule', 'nilesh@gmail.com', '9527331338', '1983-09-28', 'nilesh');
INSERT INTO users VALUES(default, 'Smita', 'Kadam', 'smita@gmail.com', '9822012345', '1980-10-01', 'smita');

INSERT INTO movies VALUES(default, 'Avatar', '2009-02-21');
INSERT INTO movies VALUES(default, 'Titanic', '1997-08-09');
INSERT INTO movies VALUES(default, 'Frozen', '2013-07-24');
INSERT INTO movies VALUES(default, 'The Lord of the Rings', '2003-03-12');
INSERT INTO movies VALUES(default, 'Skyfall', '2012-12-26');
INSERT INTO movies VALUES(default, 'Toy Story', '2010-07-19');
INSERT INTO movies VALUES(default, 'Jurassic Park', '1993-06-16');
INSERT INTO movies VALUES(default, 'Alice in Wonderland', '2010-03-04');
INSERT INTO movies VALUES(default, 'Zootopia', '2016-04-11');
INSERT INTO movies VALUES(default, 'The Dark Knight', '2008-12-16');
