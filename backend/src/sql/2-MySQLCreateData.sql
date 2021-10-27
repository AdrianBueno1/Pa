-- ----------------------------------------------------------------------------
-- Put here INSERT statements for inserting data required by the application
-- in the "pa-project" database.
-------------------------------------------------------------------------------
INSERT INTO City (name) VALUES ('A Coruña');
INSERT INTO City (name) VALUES ('Vilagarcia de Arousa');
INSERT INTO City (name) VALUES ('Santiago de Compostela');

INSERT INTO Cinema (cityId, name) VALUES (1,'Cine Yelmo');
INSERT INTO Cinema (cityId, name) VALUES (1,'Cinesa');
INSERT INTO Cinema (cityId, name) VALUES (2,'Multicines Gran Arousa');
INSERT INTO Cinema (cityId, name) VALUES (2,'Cines Avenida 3D');
INSERT INTO Cinema (cityId, name) VALUES (2,'Cinesa');
INSERT INTO Cinema (cityId, name) VALUES (3,'Cines Compostela');
INSERT INTO Cinema (cityId, name) VALUES (3,'ESNATU ZINEMA');
INSERT INTO Cinema (cityId, name) VALUES (3,'Cinesa');

INSERT INTO Hall (cinemaId, name, capacity) VALUES (1,'Sala 1',5);
INSERT INTO Hall (cinemaId, name, capacity) VALUES (1,'Sala 2',1); 
INSERT INTO Hall (cinemaId, name, capacity) VALUES (1,'Sala 3',12); 
INSERT INTO Hall (cinemaId, name, capacity) VALUES (1,'Sala 4',20);
INSERT INTO Hall (cinemaId, name, capacity) VALUES (1,'Sala 5',8);
INSERT INTO Hall (cinemaId, name, capacity) VALUES (1,'Sala 6',5);
INSERT INTO Hall (cinemaId, name, capacity) VALUES (1,'Sala 7',15);

INSERT INTO Hall (cinemaId, name, capacity) VALUES (2,'Sala 1',15);
INSERT INTO Hall (cinemaId, name, capacity) VALUES (3,'Sala 1',15);
INSERT INTO Hall (cinemaId, name, capacity) VALUES (3,'Sala 2',5); 
INSERT INTO Hall (cinemaId, name, capacity) VALUES (4,'Sala 1',5); 
INSERT INTO Hall (cinemaId, name, capacity) VALUES (4,'Sala 2',20);
INSERT INTO Hall (cinemaId, name, capacity) VALUES (5,'Sala 1',30);
INSERT INTO Hall (cinemaId, name, capacity) VALUES (5,'Sala 2',3); 
INSERT INTO Hall (cinemaId, name, capacity) VALUES (6,'Sala 1',10); 
INSERT INTO Hall (cinemaId, name, capacity) VALUES (6,'Sala 2',7);

INSERT INTO Movie (title, summary, duration)
	VALUES ('A Space Odyssey', 
        '2001 is a story of evolution. Sometime in the distant past, someone\
or something nudged evolution by placing a monolith on Earth (presumably\
elsewhere throughout the universe as well)...',164);

INSERT INTO Movie (title, summary, duration)
	VALUES ('Blade Runner', 
        'In the 21st century, a corporation develops human clones to be used\
as slaves in colonies outside the Earth, identified as replicants. In 2019,\
a former police officer is hired to hunt down a fugitive group of clones\
living undercover in Los Angeles...',117);

INSERT INTO Movie (title, summary, duration)
	VALUES ('Alien', 
        'In the distant future, the crew of the commercial spaceship Nostromo\
are on their way home when they pick up a distress call from a distant moon.\
The crew are under obligation to investigate and the spaceship descends on the\
moon afterwards...',117);

INSERT INTO Movie (title, summary, duration)
	VALUES ('Matrix', 
        'Thomas A. Anderson is a man living two lives. By day he is an average\
computer programmer and by night a hacker known as Neo. Neo has always\
questioned his reality, but the truth is far beyond his imagination...',150);

INSERT INTO Movie (title, summary, duration)
	VALUES ('Pale rider', 
        'Clint Eastwood is a mysterious preacher who comes to a gold mining\
camp near a small town in the mountains. The miners are in grave danger as\
a ruthless landowner decides to take their land, with the support of the\
sheriff...',116);
    
INSERT INTO Movie (title, summary, duration)
	VALUES ('Pulp Fiction', 
        'Series of less-and-more related but separated short stories of crime\
and comedy that each result into an unexpected ending including unexpected\
deaths, sudden twists, black comedy events and horrific conclusions of crime\
and its following consequences...',158);
    
INSERT INTO Movie (title, summary, duration)
	VALUES ('Unforgiven',
        'Retired Old West gunslinger William Munny reluctantly takes on one\
last job, with the help of his old partner and a young man...',131);

INSERT INTO Movie (title, summary, duration)
	VALUES ('Dirty Dancing',
		'It stars Jennifer Grey as Frances "Baby" Houseman, a young woman who\
falls in love with dance instructor Johnny Castle (Patrick Swayze) at a\
holiday resort...',100);

INSERT INTO Movie (title, summary, duration)
	VALUES ('Driving Miss Daisy',
		'The story defines Daisy and her point of view through a network of\
relationships and emotions by focusing on her home life, synagogue, friends,\
family, fears, and concerns over a twenty five year period....',100);

INSERT INTO Movie (title, summary, duration)
	VALUES ('The Bucket List',
		'The main plot follows two terminally ill men on their road trip with\
a wish list of things to do before they "kick the bucket"....',97);


INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (1,1,DATE_ADD(DATE(NOW()), INTERVAL '0 00:05' DAY_MINUTE),7.20,5);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (2,2,DATE_ADD(DATE(NOW()), INTERVAL '0 12:30' DAY_MINUTE),10.20,1);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (3,3,DATE_ADD(DATE(NOW()), INTERVAL '0 15:30' DAY_MINUTE),7.20,12);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (3,4,DATE_ADD(DATE(NOW()), INTERVAL '0 15:30' DAY_MINUTE),4.20,12);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (3,5,DATE_ADD(DATE(NOW()), INTERVAL '0 15:30' DAY_MINUTE),10.20,12);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (4,3,DATE_ADD(DATE(NOW()), INTERVAL '0 17:30' DAY_MINUTE),4.20,20);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (4,4,DATE_ADD(DATE(NOW()), INTERVAL '0 17:30' DAY_MINUTE),7.20,20);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (4,5,DATE_ADD(DATE(NOW()), INTERVAL '0 17:30' DAY_MINUTE),4.20,20);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (5,5,DATE_ADD(DATE(NOW()), INTERVAL '0 19:30' DAY_MINUTE),7.20,8);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (6,6,DATE_ADD(DATE(NOW()), INTERVAL '0 21:30' DAY_MINUTE),10.20,5);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (7,7,DATE_ADD(DATE(NOW()), INTERVAL '0 23:55' DAY_MINUTE),7.20,15);

INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (1,1,DATE_ADD(DATE(NOW()), INTERVAL '1 10:05' DAY_MINUTE),4.20,5);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (2,2,DATE_ADD(DATE(NOW()), INTERVAL '1 12:30' DAY_MINUTE),10.20,1);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (2,3,DATE_ADD(DATE(NOW()), INTERVAL '1 12:30' DAY_MINUTE),4.20,1);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (2,4,DATE_ADD(DATE(NOW()), INTERVAL '1 12:30' DAY_MINUTE),7.20,1);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (2,5,DATE_ADD(DATE(NOW()), INTERVAL '1 12:30' DAY_MINUTE),10.20,1);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (3,2,DATE_ADD(DATE(NOW()), INTERVAL '1 15:30' DAY_MINUTE),7.20,12);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (3,3,DATE_ADD(DATE(NOW()), INTERVAL '1 15:30' DAY_MINUTE),4.20,12);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (3,4,DATE_ADD(DATE(NOW()), INTERVAL '1 15:30' DAY_MINUTE),7.20,12);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (3,5,DATE_ADD(DATE(NOW()), INTERVAL '1 15:30' DAY_MINUTE),10.20,12);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (4,4,DATE_ADD(DATE(NOW()), INTERVAL '1 23:55' DAY_MINUTE),7.20,20);

INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (1,5,DATE_ADD(DATE(NOW()), INTERVAL '2 10:05' DAY_MINUTE),4.20,5);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (2,6,DATE_ADD(DATE(NOW()), INTERVAL '2 12:30' DAY_MINUTE),7.20,1);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (3,7,DATE_ADD(DATE(NOW()), INTERVAL '2 15:30' DAY_MINUTE),10.20,12);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (4,8,DATE_ADD(DATE(NOW()), INTERVAL '2 23:55' DAY_MINUTE),7.20,20);

INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (1,1,DATE_ADD(DATE(NOW()), INTERVAL '3 10:05' DAY_MINUTE),4.20,5);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (2,2,DATE_ADD(DATE(NOW()), INTERVAL '3 12:30' DAY_MINUTE),10.20,1);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (3,3,DATE_ADD(DATE(NOW()), INTERVAL '3 15:30' DAY_MINUTE),4.20,12);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (4,4,DATE_ADD(DATE(NOW()), INTERVAL '3 23:55' DAY_MINUTE),7.20,20);

INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (1,5,DATE_ADD(DATE(NOW()), INTERVAL '4 10:05' DAY_MINUTE),4.20,5);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (2,6,DATE_ADD(DATE(NOW()), INTERVAL '4 12:30' DAY_MINUTE),10.20,1);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (3,7,DATE_ADD(DATE(NOW()), INTERVAL '4 15:30' DAY_MINUTE),4.20,12);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (4,8,DATE_ADD(DATE(NOW()), INTERVAL '4 23:55' DAY_MINUTE),7.20,20);

INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (1,1,DATE_ADD(DATE(NOW()), INTERVAL '5 10:05' DAY_MINUTE),10.20,5);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (2,2,DATE_ADD(DATE(NOW()), INTERVAL '5 12:30' DAY_MINUTE),7.20,1);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (3,3,DATE_ADD(DATE(NOW()), INTERVAL '5 15:30' DAY_MINUTE),4.20,12);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (4,4,DATE_ADD(DATE(NOW()), INTERVAL '5 23:55' DAY_MINUTE),7.20,20);

INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (1,5,DATE_ADD(DATE(NOW()), INTERVAL '6 10:05' DAY_MINUTE),7.20,5);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (2,6,DATE_ADD(DATE(NOW()), INTERVAL '6 12:30' DAY_MINUTE),10.20,1);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (3,7,DATE_ADD(DATE(NOW()), INTERVAL '6 15:30' DAY_MINUTE),7.20,12);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (4,8,DATE_ADD(DATE(NOW()), INTERVAL '6 23:55' DAY_MINUTE),10.20,20);

INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (1,1,DATE_ADD(DATE(NOW()), INTERVAL '7 10:05' DAY_MINUTE),7.20,5);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (2,2,DATE_ADD(DATE(NOW()), INTERVAL '7 12:30' DAY_MINUTE),4.20,1);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (3,3,DATE_ADD(DATE(NOW()), INTERVAL '7 15:30' DAY_MINUTE),10.20,12);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (4,4,DATE_ADD(DATE(NOW()), INTERVAL '7 23:55' DAY_MINUTE),4.20,20);



INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (11,1,DATE_ADD(DATE(NOW()), INTERVAL '0 00:05' DAY_MINUTE),7.20,5);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (12,2,DATE_ADD(DATE(NOW()), INTERVAL '0 12:30' DAY_MINUTE),10.20,1);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (11,3,DATE_ADD(DATE(NOW()), INTERVAL '0 15:30' DAY_MINUTE),7.20,12);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (12,4,DATE_ADD(DATE(NOW()), INTERVAL '0 15:30' DAY_MINUTE),4.20,12);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (11,3,DATE_ADD(DATE(NOW()), INTERVAL '0 17:30' DAY_MINUTE),7.20,20);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (12,4,DATE_ADD(DATE(NOW()), INTERVAL '0 17:30' DAY_MINUTE),10.20,20);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (11,5,DATE_ADD(DATE(NOW()), INTERVAL '0 17:30' DAY_MINUTE),4.20,20);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (12,5,DATE_ADD(DATE(NOW()), INTERVAL '0 19:30' DAY_MINUTE),4.20,8);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (11,6,DATE_ADD(DATE(NOW()), INTERVAL '0 21:30' DAY_MINUTE),7.20,5);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (12,7,DATE_ADD(DATE(NOW()), INTERVAL '0 23:55' DAY_MINUTE),10.20,15);

INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (12,1,DATE_ADD(DATE(NOW()), INTERVAL '1 10:05' DAY_MINUTE),7.20,5);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (11,2,DATE_ADD(DATE(NOW()), INTERVAL '1 12:30' DAY_MINUTE),4.20,1);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (12,3,DATE_ADD(DATE(NOW()), INTERVAL '1 12:30' DAY_MINUTE),7.20,1);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (12,4,DATE_ADD(DATE(NOW()), INTERVAL '1 12:30' DAY_MINUTE),10.20,1);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (11,5,DATE_ADD(DATE(NOW()), INTERVAL '1 12:30' DAY_MINUTE),7.20,1);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (12,2,DATE_ADD(DATE(NOW()), INTERVAL '1 15:30' DAY_MINUTE),4.20,12);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (11,3,DATE_ADD(DATE(NOW()), INTERVAL '1 15:30' DAY_MINUTE),7.20,12);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (12,4,DATE_ADD(DATE(NOW()), INTERVAL '1 15:30' DAY_MINUTE),10.20,12);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (11,5,DATE_ADD(DATE(NOW()), INTERVAL '1 15:30' DAY_MINUTE),7.20,12);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (12,4,DATE_ADD(DATE(NOW()), INTERVAL '1 23:55' DAY_MINUTE),7.20,20);



INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (15,1,DATE_ADD(DATE(NOW()), INTERVAL '0 00:05' DAY_MINUTE),7.20,5);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (16,2,DATE_ADD(DATE(NOW()), INTERVAL '0 12:30' DAY_MINUTE),7.20,1);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (15,3,DATE_ADD(DATE(NOW()), INTERVAL '0 15:30' DAY_MINUTE),10.20,12);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (16,4,DATE_ADD(DATE(NOW()), INTERVAL '0 15:30' DAY_MINUTE),4.20,12);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (16,3,DATE_ADD(DATE(NOW()), INTERVAL '0 17:30' DAY_MINUTE),7.20,20);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (15,4,DATE_ADD(DATE(NOW()), INTERVAL '0 17:30' DAY_MINUTE),10.20,20);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (16,5,DATE_ADD(DATE(NOW()), INTERVAL '0 17:30' DAY_MINUTE),7.20,20);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (15,5,DATE_ADD(DATE(NOW()), INTERVAL '0 19:30' DAY_MINUTE),4.20,8);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (16,6,DATE_ADD(DATE(NOW()), INTERVAL '0 21:30' DAY_MINUTE),7.20,5);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (15,7,DATE_ADD(DATE(NOW()), INTERVAL '0 23:55' DAY_MINUTE),10.20,15);

INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (16,1,DATE_ADD(DATE(NOW()), INTERVAL '1 10:05' DAY_MINUTE),7.20,5);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (15,2,DATE_ADD(DATE(NOW()), INTERVAL '1 12:30' DAY_MINUTE),7.20,1);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (16,3,DATE_ADD(DATE(NOW()), INTERVAL '1 12:30' DAY_MINUTE),10.20,1);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (15,4,DATE_ADD(DATE(NOW()), INTERVAL '1 12:30' DAY_MINUTE),7.20,1);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (16,5,DATE_ADD(DATE(NOW()), INTERVAL '1 12:30' DAY_MINUTE),4.20,1);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (15,2,DATE_ADD(DATE(NOW()), INTERVAL '1 15:30' DAY_MINUTE),7.20,12);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (16,3,DATE_ADD(DATE(NOW()), INTERVAL '1 15:30' DAY_MINUTE),10.20,12);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (15,4,DATE_ADD(DATE(NOW()), INTERVAL '1 15:30' DAY_MINUTE),7.20,12);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (16,5,DATE_ADD(DATE(NOW()), INTERVAL '1 15:30' DAY_MINUTE),4.20,12);
INSERT INTO Session (hallId,movieId,date,price,availableSeats) VALUES (15,4,DATE_ADD(DATE(NOW()), INTERVAL '1 23:55' DAY_MINUTE),7.20,20);


INSERT INTO User (userName, password, firstName, lastName, email, role)
	VALUES ('viewer', '$2a$10$6Yz.4lqN5VgTWW9HdQuI7.v2ONSMJg34gor0PShvUGnEZBuNYJINe', 'user_example', 'user_example', 'user_example@udc.es', 0);

INSERT INTO User (userName, password, firstName, lastName, email, role)
	VALUES ('ticketseller', '$2a$10$JDb1xPrBpsz7qub0iDIn2ubF13oNuuBKMf6VBxI1n.eWj9EYe9qtW', 'ticketseller_example', 'ticketseller_example', 'ticketseller_example@udc.es', 1);



INSERT INTO Sale (userId, sessionId, seats, creditcard, used, date)
	VALUES (1, 1, 1, '1231231231231231', 0, DATE_ADD(DATE(NOW()), INTERVAL '-4 13:00' DAY_MINUTE));
    
INSERT INTO Sale (userId, sessionId, seats, creditcard, used, date)
	VALUES (1, 1, 4, '1231231231231231', 0, DATE_ADD(DATE(NOW()), INTERVAL '-4 14:00' DAY_MINUTE));

INSERT INTO Sale (userId, sessionId, seats, creditcard, used, date)
	VALUES (1, 2, 2, '1231231231231231', 0, DATE_ADD(DATE(NOW()), INTERVAL '-4 15:00' DAY_MINUTE));
    
INSERT INTO Sale (userId, sessionId, seats, creditcard, used, date)
	VALUES (1, 2, 4, '1231231231231231', 0, DATE_ADD(DATE(NOW()), INTERVAL '-4 16:00' DAY_MINUTE));
    
INSERT INTO Sale (userId, sessionId, seats, creditcard, used, date)
	VALUES (1, 1, 8, '1231231231231231', 1, DATE_ADD(DATE(NOW()), INTERVAL '-4 17:00' DAY_MINUTE));
    
INSERT INTO Sale (userId, sessionId, seats, creditcard, used, date)
	VALUES (1, 1, 1, '1231231231231231', 1, DATE_ADD(DATE(NOW()), INTERVAL '-3 18:00' DAY_MINUTE));
    
INSERT INTO Sale (userId, sessionId, seats, creditcard, used, date)
	VALUES (1, 2, 2, '1231231231231231', 1, DATE_ADD(DATE(NOW()), INTERVAL '-3 19:00' DAY_MINUTE));
    
INSERT INTO Sale (userId, sessionId, seats, creditcard, used, date)
	VALUES (1, 2, 3, '1231231231231231', 1, DATE_ADD(DATE(NOW()), INTERVAL '-3 20:00' DAY_MINUTE));
    
INSERT INTO Sale (userId, sessionId, seats, creditcard, used, date)
	VALUES (1, 3, 4, '1231231231231231', 1, DATE_ADD(DATE(NOW()), INTERVAL '-3 15:00' DAY_MINUTE));
    
INSERT INTO Sale (userId, sessionId, seats, creditcard, used, date)
	VALUES (1, 3, 5, '1231231231231231', 0, DATE_ADD(DATE(NOW()), INTERVAL '-3 15:00' DAY_MINUTE));
    
INSERT INTO Sale (userId, sessionId, seats, creditcard, used, date)
	VALUES (1, 1, 1, '1231231231231231', 0, DATE_ADD(DATE(NOW()), INTERVAL '-3 16:00' DAY_MINUTE));
    
INSERT INTO Sale (userId, sessionId, seats, creditcard, used, date)
	VALUES (1, 1, 2, '1231231231231231', 0, DATE_ADD(DATE(NOW()), INTERVAL '-2 17:00' DAY_MINUTE));
    
INSERT INTO Sale (userId, sessionId, seats, creditcard, used, date)
	VALUES (1, 3, 3, '1231231231231231', 0, DATE_ADD(DATE(NOW()), INTERVAL '-2 18:00' DAY_MINUTE));
    
INSERT INTO Sale (userId, sessionId, seats, creditcard, used, date)
	VALUES (1, 5, 3, '1231231231231231', 0, DATE_ADD(DATE(NOW()), INTERVAL '-2 18:00' DAY_MINUTE));
    
INSERT INTO Sale (userId, sessionId, seats, creditcard, used, date)
	VALUES (1, 1, 3, '1231231231231231', 0, DATE_ADD(DATE(NOW()), INTERVAL '-2 18:00' DAY_MINUTE));
    
INSERT INTO Sale (userId, sessionId, seats, creditcard, used, date)
	VALUES (1, 3, 4, '1231231231231231', 0, DATE_ADD(DATE(NOW()), INTERVAL '-2 19:00' DAY_MINUTE));
    
INSERT INTO Sale (userId, sessionId, seats, creditcard, used, date)
	VALUES (1, 1, 5, '1231231231231231', 1, DATE_ADD(DATE(NOW()), INTERVAL '-2 14:00' DAY_MINUTE));
    
INSERT INTO Sale (userId, sessionId, seats, creditcard, used, date)
	VALUES (1, 1, 6, '1231231231231231', 1, DATE_ADD(DATE(NOW()), INTERVAL '-2 13:00' DAY_MINUTE));
    
INSERT INTO Sale (userId, sessionId, seats, creditcard, used, date)
	VALUES (1, 4, 7, '1231231231231231', 1, DATE_ADD(DATE(NOW()), INTERVAL '-1 12:00' DAY_MINUTE));
    
INSERT INTO Sale (userId, sessionId, seats, creditcard, used, date)
	VALUES (1, 3, 1, '1231231231231231', 1, DATE_ADD(DATE(NOW()), INTERVAL '-1 11:00' DAY_MINUTE));
    
INSERT INTO Sale (userId, sessionId, seats, creditcard, used, date)
	VALUES (1, 4, 2, '1231231231231231', 0, DATE_ADD(DATE(NOW()), INTERVAL '-1 10:00' DAY_MINUTE));
    
INSERT INTO Sale (userId, sessionId, seats, creditcard, used, date)
	VALUES (1, 2, 3, '1231231231231231', 0, DATE_ADD(DATE(NOW()), INTERVAL '-1 15:00' DAY_MINUTE));
