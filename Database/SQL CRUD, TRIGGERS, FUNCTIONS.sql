Use myLibrary;
-- user to manage database
INSERT INTO user(username,password,usertype) values 
('admin','admin','admin'),
('user','user','user'),
('manager','manager','manager');

INSERT INTO employee(username,pin,birthdate,phone,salary,name,surname) values
('admin','admin','1999-10-10',0685841160,2000,'Besjon','Cifliku'),
('manager','manager','2002-12-5',0685012213,1500,'Aldrin','Cifliku');

-- users for publisher
INSERT INTO user(username,password,usertype) values 
('pegi','pegi','pub'),
('albas','albas','pub'),
('meidaprint','mediaprint','pub'),
('zambak','zambak','pub'),
('ping','ping','pub');

INSERT INTO PUBLISHER VALUES
(123456789,'Pegi',0685412121,'Tonny Montana','New Hampshire Street','pegi','pegi'),
(135248632,'Albas',0695221135,'Besjon Cifliku','Miami,Florida','albas','albas'),
(111252221,'Mediaprint',0674585210,'Rendi Orton','Rruga Durrsit','meidaprint','meidaprint'),
(333355555,'Zambak',040512345,'Klar R. Jackson','Komuna e Parisit','zambak','zambak'),
(698541230,'Penguin',0674110210,'Ray M. Spenser','Oslso , Babrru','ping','ping');


-- useraccounts for a member  
INSERT INTO user(username,password,usertype) values 
('besjon','cifliku','member'),
('orlin','malkja','member'),
('ylli','pango','member'),
('emiljano','shullazi','member'),
('dritan','dajti','member'),
('klemend','balili','member'),
('reni','koci','member'),
('jurgen','ndoros','member');

INSERT INTO MEMBER VALUES
(12345678,'Besjon','Cifliku',0685841160,'Rruga Loni Ligori,Tirane','2018-01-05','besjon','cifliku'),
(12101110,'Orlin','Malkja',0674521012,'Rruga Mine Peza,Tirane','2018-04-06','orlin','malkja'),
(11111000,'Ylli','Pango',0675236512,'Rruga 21 Dhjetori,Tirane','2018-05-07','ylli','pango'),
(98522111,'Emiljano','Shullazi',0687512015,'Rruga 1 Maj,Tirane','2018-03-09','emiljano','shullazi'),
(13521011,'Dritan','Dajti',0685574100,'Rruga Dibres,Tirane','2018-02-01','dritan','dajti'),
(12312312,'Klemend','Balili',0685200000,'Rruga Burgut,Tirane','2018-08-02','klemend','balili'),
(19852314,'Reni','Koqi',0685879221,'Rruga Konvikti i Kuq,Lushnje','2018-02-03','reni','koci'),
(25241236,'Jurgen','Hashkurti',0699452101,'Rruga The Fallen Kingdom,Tirane','2018-09-05','jurgen','ndoros');

INSERT INTO BOOK VALUES
(1234567891,'Harry Potter 1','R.K. Jackson','1985-01-09','Very intersting and nice book to spend time with-Jordanson',135248632),
(1237894541,'Harry Potter 2','R.K. Jackson','1987-07-24','It is very intersting and fasciating-New York Times',135248632),
(1234567871,'Harry Potter 3','R.K. Jackson','1990-12-11','The most amaizing book I have ever read!-Tomson School',135248632),
(1124117658,'Notredam De Paris','Victor Hygo','1960-02-25','Autori qe u rrit me shekullin e 20.Liber mjaft i arrire!- Gazeta Mapo',111252221),
(0012043508,'Burrat qe urrene grate','Olson','2015-03-21','BlaBlaBla-Drakula',135248632),
(1211105551,'Nils Holgesron','Sema Santorini','2016-06-05','',123456789),
(1111117010,'Meri Yll','Viktor Canosinaj','2013-07-11','Historia magjike e jetes se pare nga afer-S.B',135248632),
(1987456103,'Digital Design','John F.Walkery','2005-08-07','A very helpful book for future engineers-Prof.Dr. Doktorri',333355555);

INSERT INTO LIBRARY(Description,Book_ISBN) VALUES
('This is description 1',1234567891),
('This is description 2',1237894541),
('This is description 3',1234567891),
('This is description 4',1111117010),
('This is description 5',0012043508),
('This is description 6',1987456103),
('This is description 7',1124117658),
('This is description 8',1124117658),
('This is description 9',1234567891),
('This is description 10',1234567891),
('This is description 11',1237894541),
('This is description 12',0012043508),
('This is description 13',0012043508),
('This is description 14',1211105551),
('This is description 15',1237894541);
 
INSERT INTO LIBRARY(Description,Book_ISBN) VALUES 
('This is description 17',1234567871),
('This is description 18',1234567871),
('This is description 19',1234567871);
INSERT INTO LIBRARY(Description,Book_ISBN) VALUE 
('This is description 20',1124117658);

INSERT INTO BORROW VALUES
('2019-10-02','2019-10-03',21,12345678),-- Besjon-Albas
('2019-05-06','2019-06-07',32,25241236),-- Emiljano-Albas
('2018-01-01','2020-01-01',25,98522111);-- Diego-Albas
-- Besjon borrow 3 books from albas
INSERT INTO BORROW VALUES
('2019-10-02','2019-10-03',26,12345678),
('2019-10-02','2019-10-03',27,12345678),
('2019-10-02','2019-10-03',28,12345678);
-- Klemend borrow one mediaprint book.
 INSERT INTO BORROW VALUE
 ('2019-10-02','2019-10-03',30,12312312);
-- Besjon borrow a mediaprint book.
 INSERT INTO BORROW VALUE
 ('2019-10-02','2019-10-03',31,12345678);
 
 INSERT INTO BOOK VALUES
(1234,'Test','R.K. Jackson','1985-01-09','Very intersting and nice book to spend time with-Jordanson',698541230),
(1235,'Test 2','R.K. Jackson','1987-07-24','It is very intersting and fasciating-New York Times',698541230),
(1236,'Test 3','R.K. Jackson','1990-12-11','The most amaizing book I have ever read!-Tomson School',698541230),
(1237,'Test','Victor Hygo','1960-02-25','Autori qe u rrit me shekullin e 20.Liber mjaft i arrire!- Gazeta Mapo',698541230),
(1238,'Test','Olson','2015-03-21','BlaBlaBla-Drakula',698541230),
(1239,'Test','Sema Santorini','2016-06-05','',698541230);


DELIMITER $$
CREATE PROCEDURE readUsers ()
BEGIN
 SELECT * FROM employee,user WHERE employee.username LIKE user.username and employee.pin LIKE user.password;
    END$$
DELIMITER ;
CALL readUsers();


DELIMITER $$
CREATE PROCEDURE readMembers ()
BEGIN
 SELECT * FROM member;
    END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE readBooks ()
BEGIN
 SELECT Date_From,Date_To,Library_BookID,ISBN,Title,Member_IDM,Name FROM borrow,book,library,member where Library_BookID=BookID and Member_IDM=IDM and Book_ISBN=ISBN;
    END$$
DELIMITER ;
call readBooks();

DELIMITER $$
CREATE PROCEDURE deleteUsers (IN idE INT , IN username VARCHAR(45))
BEGIN

        delete from user where username LIKE username;

        delete from employee where id=idE;


     
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER decrEdit
    BEFORE UPDATE ON library
    FOR EACH ROW 
BEGIN
    INSERT INTO library
    SET action = 'update',
     Description = "No description for entry!",
        changedat = NOW(); 
END$$
DELIMITER ;