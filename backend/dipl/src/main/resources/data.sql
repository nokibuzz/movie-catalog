INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

INSERT INTO users(created_date,updated_date,email,password,username,name,surname,avatar_id,banned)
	VALUES ('2020-08-12 20:53:40','2020-08-12 20:53:40','admin@admin.com','$2a$10$OaA41wgghSBe4eC2.Yqyf.l9wjy.QAHkx3SmL1xMFunMjFZh90EQK','admin','Admin','Adminovic',1,false);
INSERT INTO users(created_date,updated_date,email,password,username,name,surname,avatar_id,banned)
	VALUES ('2020-09-02 06:49:04','2020-09-02 06:49:04','petar.nojner@gmail.com','$2a$10$O.NtH1Lf8ZMVop8Jn.oyYeuKF11eJ8bz9YBwPzK1FZ.0oGj.jqGKW','cope','Cope','Copic',2,true);
INSERT INTO users(created_date,updated_date,email,password,username,name,surname,avatar_id,banned)
	VALUES ('2020-09-02 06:50:10','2020-09-02 06:50:10','ana.nojner@gmail.com','$2a$10$RMQczn9tkEZurrG7BcfVN./TSI5YBd1dtxvaxyUAFnXG7tXMsmkei','ana.nojner','Ana','Anic',3,false);
INSERT INTO users(created_date,updated_date,email,password,username,name,surname,avatar_id,banned)
	VALUES ('2020-09-02 06:50:10','2020-09-02 06:50:10','da.ni@gmail.com','$2a$10$v2oATEcI9hsvlkPrR/mm2OdIOVoVBCr..hw1vBg5FVKbbLx/O/vta','dani','Dani','Danic',4,false);
INSERT INTO users(created_date,updated_date,email,password,username,name,surname,avatar_id,banned)
	VALUES ('2020-09-02 06:50:10','2020-09-02 06:50:10','dora@gmail.com','$2a$10$8oMkAbQiUrvyDAKK7ggJz.bZk9hPrisn1RyF8yQo/rCJsKsOr.LEu','dora','Dora','Doric',5,false);

INSERT INTO user_roles VALUES(1,2);
INSERT INTO user_roles VALUES(2,1);
INSERT INTO user_roles VALUES(3,1);
INSERT INTO user_roles VALUES(4,1);
INSERT INTO user_roles VALUES(5,1);

INSERT INTO movie(title,description,duration,year,ranking,genres,trailer_url) 
	VALUES ('Rocky I', 'Action Movie 1',96,'1989','8.1','ACTION,COMEDY','3VUblDwa648');
INSERT INTO movie(title,description,duration,year,ranking,genres,trailer_url) 
	VALUES ('Rocky II', 'Action Movie 2',83,'1989','8.1','ACTION,COMEDY','I2zFQmHWVCI');
INSERT INTO movie(title,description,duration,year,ranking,genres,trailer_url) 
	VALUES ('Rocky III', 'Action Movie 3',102,'1989','8.1','ACTION,DRAMA','gbRDCWKqvEc');
INSERT INTO movie(title,description,duration,year,ranking,genres,trailer_url) 
	VALUES ('Rocky IV', 'Action Movie 4',76,'1989','8.1','ACTION,THRILLER','RrbeDEcgWYs');
INSERT INTO movie(title,description,duration,year,ranking,genres,trailer_url) 
	VALUES ('Rocky V', 'Action Movie 5',36,'1989','8.1','ACTION,HORROR','Q49rdBf1NnM');
	
INSERT INTO actor(name, birthday,roles) 
	VALUES ('Ben Affleck','1969-05-12','Actor,Director');
INSERT INTO actor(name, birthday,roles) 
	VALUES ('Ben Stiller','1973-06-17','Actor');
INSERT INTO actor(name, birthday,roles) 
	VALUES ('Ryan Reynolds','1976-10-23','Actor,Screen writter,Producer');
INSERT INTO actor(name, birthday,roles) 
	VALUES ('Brad Pitt','1973-04-07','Actor,Director');
INSERT INTO actor(name, birthday,roles) 
	VALUES ('Sandra Bullock','1967-06-17','Actor');
INSERT INTO actor(name, birthday,roles) 
	VALUES ('Eva Longoria','1980-10-23','Actor');
	
INSERT INTO movie_actors VALUES(1,1);
INSERT INTO movie_actors VALUES(1,2);
INSERT INTO movie_actors VALUES(1,3);
INSERT INTO movie_actors VALUES(2,1);
INSERT INTO movie_actors VALUES(2,2);
INSERT INTO movie_actors VALUES(3,4);
INSERT INTO movie_actors VALUES(4,2);
INSERT INTO movie_actors VALUES(4,3);
INSERT INTO movie_actors VALUES(4,5);
INSERT INTO movie_actors VALUES(4,6);
INSERT INTO movie_actors VALUES(5,4);
INSERT INTO movie_actors VALUES(5,5);
INSERT INTO movie_actors VALUES(5,6);