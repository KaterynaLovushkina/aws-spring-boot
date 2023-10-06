CREATE DATABASE  IF NOT EXISTS store;
Use store;

DROP TABLE IF EXISTS download_info;
DROP TABLE IF EXISTS download;
DROP TABLE IF EXISTS app_creating;
DROP TABLE IF EXISTS creater;
DROP TABLE IF EXISTS feedback;
DROP TABLE IF EXISTS app;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS app_category;
DROP TABLE IF EXISTS email_preferences;
DROP TABLE IF EXISTS appCategoryInsertInfo;
DROP TABLE IF EXISTS user;




CREATE TABLE app (
                     id int NOT NULL AUTO_INCREMENT,
                     name varchar(25) NOT NULL,
                     description text NOT NULL,
                     weight_in_mb int NOT NULL,
                     create_date date NOT NULL,
                     is_free bool NOT NULL,
                     price_in_dollars decimal(8,2) NULL,
                     has_advert bool NOT NULL,
                     category_id int NOT NULL,
                     CONSTRAINT app_pk PRIMARY KEY (id)
) ENGINE = INNODB;

-- Table: app_category
CREATE TABLE app_category (
                              id int NOT NULL AUTO_INCREMENT,
                              name varchar(30) NOT NULL,
                              description text NULL,
                              CONSTRAINT app_category_pk PRIMARY KEY (id)
) ENGINE = INNODB;

-- Table: app_creating
CREATE TABLE app_creating (
                              app_id int NOT NULL,
                              creater_id int NOT NULL,
                              CONSTRAINT app_creating_pk PRIMARY KEY (app_id,creater_id)
) ENGINE = INNODB;


-- Table: category
CREATE TABLE category (
                          id int NOT NULL AUTO_INCREMENT,
                          audience_type enum ('child', 'teen', 'mature', 'all') NOT NULL,
                          app_category_id int NOT NULL,
                          CONSTRAINT audience_type_check CHECK (audience_type in ('child', 'teen', 'mature', 'all')),
                          CONSTRAINT category_pk PRIMARY KEY (id)
) ENGINE = INNODB;

-- Table: creater
CREATE TABLE creater (
                         id int NOT NULL AUTO_INCREMENT,
                         full_name varchar(60) NOT NULL,
                         work_branch varchar(50) NOT NULL,
                         email varchar(70) NOT NULL,
                         created_app_amount int NULL,
                         CONSTRAINT email_validation_check_for_creater CHECK (email REGEXP '[a-zA-Z0-9_+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-]+'),
                         CONSTRAINT creater_pk PRIMARY KEY (id)
) ENGINE = INNODB;



-- Table: download
CREATE TABLE download (
                          id int NOT NULL AUTO_INCREMENT,
                          amount int NOT NULL,
                          total_price decimal(8,2) NULL,
                          user_id int NOT NULL,
                          CONSTRAINT download_pk PRIMARY KEY (id)
) ENGINE = INNODB;

-- Table: download_info
CREATE TABLE download_info (
                               app_id int NOT NULL,
                               download_id int NOT NULL,
                               CONSTRAINT download_info_pk PRIMARY KEY (app_id,download_id)
) ENGINE = INNODB;



-- Table: feedback
CREATE TABLE feedback (
                          id int NOT NULL AUTO_INCREMENT,
                          description text NULL,
                          created_date date NOT NULL,
                          rate float NOT NULL,
                          user_id int NOT NULL,
                          app_id int NOT NULL,
                          CONSTRAINT rate_range_check CHECK (rate >= 0 and rate <= 5),
                          CONSTRAINT feedback_pk PRIMARY KEY (id)
) ENGINE = INNODB;

-- Table: user
CREATE TABLE user (
                      id int NOT NULL AUTO_INCREMENT,
                      password_hash varchar(60) NOT NULL,
                      full_name varchar(100) NOT NULL,
                      country varchar(30) NOT NULL,
                      CONSTRAINT user_pk PRIMARY KEY (id)
) ENGINE = INNODB;

CREATE TABLE email_preferences (
                                   email varchar(100) NOT NULL ,
                                   keep_with_up_to_date_news bool NOT NULL,
                                   receive_reply_notification bool NOT NULL,
                                   user_id int NOT NULL,
                                   CONSTRAINT email_preferences_pk PRIMARY KEY (email)
);
CREATE TABLE appCategoryInsertInfo(
                                      id int NOT NULL,
                                      nameAppCategory varchar(30) NULL,
                                      description text NULL,
                                      action varchar(20) NOT NULL,
                                      timeStamp DATETIME NOT NULL,
                                      user varchar(50) NOT NULL,
                                      CONSTRAINT app_category_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: app_category (table: app)
ALTER TABLE app ADD CONSTRAINT app_category FOREIGN KEY app_category (category_id)
    REFERENCES category (id);

-- Reference: app_creating_app (table: app_creating)
ALTER TABLE app_creating ADD CONSTRAINT app_creating_app FOREIGN KEY app_creating_app (app_id)
    REFERENCES app (id);

-- Reference: app_creating_creater (table: app_creating)
ALTER TABLE app_creating ADD CONSTRAINT app_creating_creater FOREIGN KEY app_creating_creater (creater_id)
    REFERENCES creater (id);


-- Reference: category_app_category (table: category)
ALTER TABLE category ADD CONSTRAINT category_app_category FOREIGN KEY category_app_category (app_category_id)
    REFERENCES app_category (id);


-- Reference: download_info_app (table: download_info)
ALTER TABLE download_info ADD CONSTRAINT download_info_app FOREIGN KEY download_info_app (app_id)
    REFERENCES app (id);

-- Reference: download_info_download (table: download_info)
ALTER TABLE download_info ADD CONSTRAINT download_info_download FOREIGN KEY download_info_download (download_id)
    REFERENCES download (id);

-- Reference: download_user (table: download)
ALTER TABLE download ADD CONSTRAINT download_user FOREIGN KEY download_user (user_id)
    REFERENCES user (id);


-- Reference: feedback_app (table: feedback)
ALTER TABLE feedback ADD CONSTRAINT feedback_app FOREIGN KEY feedback_app (app_id)
    REFERENCES app (id);

-- Reference: feedback_user (table: feedback)
ALTER TABLE feedback ADD CONSTRAINT feedback_user FOREIGN KEY feedback_user (user_id)
    REFERENCES user (id);







-- Insertions:

INSERT INTO user(password_hash, full_name, country) VALUES('$2a$09$RBRjpc0FdzDKBeg3BfX4i.iYNgYGCqHXTX964MPXz1oiI0D2n3ejm', 'Kateryna Lovushkina', 'Ukraine'),
                                                                ('$2a$09$nvAOFMdnI5iO3zk18XESZ.gSkoPqsNrVI8FBL4xhWocDU7bkphS/q', 'Mirzoyan Genadiy', 'Greece'),
                                                                ('$2a$09$ZPXldMZ1kaOI59MjUBEIFOvSqc.M7XZm0oPqNL1XWGPz41tpohnMS', 'Pikaso Mykola', 'Czech Republick'),
                                                                ('$2a$09$DUW9afr.ALR6DGdJg/zojOlQ62bkwiJrVUFd0u.CR5CmInivKHh0m', 'Veres Olesy', 'Ukraine'),
                                                                ('$2a$09$68V1pQtgVEXuKpW29i4UD.g7Gb3.S9mhCJshVWY75PaKwViW/eev6', 'Mirza Filomonova', 'Poland'),
                                                                ('$2a$09$7JgIMakICbz7y4wvLV98feBpwRb2yEAIfQV7M3/bLEv/6v8ciGmuC', 'Leonid Govototnuk', 'Ukraine'),
                                                                ('$2a$09$ipdH7mShUKZDJrep8YLqu.k0sFUMPXiAdg2VzM2Vuqzxpd/Ntt5NS', 'Oksana Tigran', 'Moldovia'),
                                                                ('$2a$09$n/kJS7R17H6UqYz9RScB8u4yi8dWMVbfiUOgGtRKNUEfAAsv//7lq', 'Kateryna Petrenko', 'Poland'),
                                                                ('$2a$09$JtcA/htU8fxz.6bhoL5DKeuhdBVEeVQhvMIM/2LabiSdnfrTxhRh.', 'Nina Oleksiyenko', 'Estoniya'),
                                                                ('$2a$09$TlbzecN.0cHl2Lq8ztdILeOwgBuD6K3o7ARwuJlvgB3uwv4P4XoD6', 'Dmytro Kuleba', 'Ukraine'),
                                                                ('$2a$09$ipX3X19afC85ihu0Qy/d9.X.WS2fHnSEHCA6hKQGGhBG8fnjbBNBW', 'Olga Kinguru', 'Litva'),
                                                                ('$2a$09$nJ19NQ3dj.oFqVhPwgBlMOWHicSWpwoNMY.INSO/0th/chIRS1Aa.', 'Evgen Kostomarov', 'Poland'),
                                                                ('$2a$09$NZ6YUI6zH7dqAodIfPUe2eG5lsMJ2u7BYOqmXW67iUdKuMQ52hz.O', 'Dagni Lithard', 'Great Britain'),
                                                                ('$2a$09$SzkqwEzbqr.mdkQi5vlDPOpm1OtsNes6xo4/1bvhIgLc6CjMKhsku', 'Taras Chmut', 'Ukraine');



INSERT INTO app_category( name, description )  VALUES('Books', 'Apps that provide extensive interactivity for content that is traditionally offered in printed form. If you’re planning a more traditional reading experience, you may want to look at publishing an iBook instead.'),
                                                     ('Business', 'Apps that assist with running a business or provide a means to collaborate, edit, or share content.'),
                                                     ('Developer Tools', 'Apps that provide tools for app development, management, and distribution.'),
                                                     ('Education', 'Apps that provide an interactive learning experience on a specific skill or subject.'),
                                                     ('Entertainment', 'Apps that are interactive and designed to entertain and inform the user, and which contain audio, visual, or other content.'),
                                                     ('Games', 'Apps that provide single or multiplayer interactive activities for entertainment purposes.'),
                                                     ('Sports', 'Apps related to professional, amateur, collegiate, or recreational sporting activities.'),
                                                     ('Social Networking', 'Apps that connect people by means of text, voice, photo, or video. Apps that contribute to community development.'),
                                                     ('Photo & Video', 'Apps that assist in capturing, editing, managing, storing, or sharing photos and videos.'),
                                                     ('Music', 'Apps that are for discovering, listening to, recording, performing, or composing music, and that are interactive in nature.');


INSERT INTO category( audience_type, app_category_id )  VALUES('all', 1),
                                                              ('mature', 2),
                                                              ('mature', 3),
                                                              ('all', 4),
                                                              ('all', 5),
                                                              ('child', 6),
                                                              ('teen', 7),
                                                              ('all', 8),
                                                              ('teen', 9),
                                                              ('all', 10);


INSERT INTO app( name, description, weight_in_mb, create_date, is_free, price_in_dollars, has_advert, category_id )
VALUES('Amoung us','Play online or over local WiFi with 4-15 players as you attempt to prep your spaceship for departure, but beware as one will be an impostor bent on killing everyone', 230, '2021-12-12', true, null, true, 6),
      ('Cash up','Cash App is the easy way to send, spend, save, and invest* your money. It’s the SAFE, FAST, and FREE mobile finance** app.', 150, '2022-01-30', true, null, true, 2),
      ('Audible','Get entertainment that moves with you this summer. Wherever your travels take you, indulge in a binge-worthy library of audiobooks, tune into exclusive podcasts and discover genre-bending Audible Originals', 150, '2022-09-01', false, 5, true, 1), -- books
      ('Deep Cleaner-Phone Faster','DeepCleaner, focusing on mobile phone cleaning, makes your mobile phone smooth from now on.', 130, '2022-07-27', true, null, false, 3),   -- dev tools
      ('SkyView Explore Universe','You dont need to be an astronomer to find stars or constellations in the sky, just open SkyView® and let it guide you to their location and identify them.', 350, '2022-01-30', false, 2, false, 4),   -- education
      ('Scanner Radio Pro','Listen to live audio from over 7,000 fire and police scanners, weather radios, amateur radio repeaters, air traffic and marine radios from around the world. ', 260, '2022-08-20', false, 24.99, true, 5),   -- entertaiment
      ('Leap Fitness Group','Home workouts include daily exercises for all major muscle groups. In just a few minutes a day, you can build muscle and stay in shape at home without going to the gym.', 250, '2022-08-25', true, null, true, 7),   -- sport
      ('TikTok','trends start here. On a device or on the web, viewers can watch and discover millions of personalized short videos.', 450, '2022-06-29', true, null, true, 8),   -- social
      ('Filto: Video Editor','Best photo/video editor with customized aesthetic sparkle filters & VHS effects which updated weekly. The coolest video & photo editor with multi-style content for creators! Its the easiest to use on IG and Facebook!', 180, '2022-09-01', false, 7, true, 9),   -- photo video
      ('Audiomack','Stream and download the best new music, play music offline without data transfer and listen to your MP3s! Browse music from the most popular categories like Hip Hop, Rap, R&B, EDM,','220','2021-09-08', false, 4.99, true, 10);



INSERT INTO creater( full_name, work_branch, email, created_app_amount ) VALUES('Benjamin Franklin', 'back-end developer', 'benjamin@gmail.com',3),
                                                                               ('Antoniy Fraanz', 'fron-end developer', 'front_ant@gmail.com',5),
                                                                               ('Liss Trass', 'full-stack developer', 'trass11@gmail.com',2),
                                                                               ('Kety Bonni', 'designer developer', 'rett455@gmail.com',1),
                                                                               ('Mellisa Gniss', 'back-end developer', 'mellis@gmail.com',1),
                                                                               ('Irina Kucher', 'front-end developer', 'kucher00@gmail.com',2),
                                                                               ('Pfilip Temonenko', 'designer', 'temonenko1@gmail.com',4),
                                                                               ('Georg Simomns', 'full-stack developer', 'tgeorg101@gmail.com',5),
                                                                               ('Anita Lovush', 'back-end developer', 'anita_lov11@gmail.com',2),
                                                                               ('Kyrilo Zavadka', 'full-stack developer', 'kyroloz@gmail.com',1),
                                                                               ('Naliya Merzin', 'back-end developer', 'natmerz@gmail.com',null),
                                                                               ('Petro Zastavniy', 'front-end developer', 'zastav_petro@org.net',2);


INSERT INTO download( amount, total_price, user_id ) VALUES(3,5,1),
                                                           (2,2,2),
                                                           (1,24.99,3),
                                                           (4,7,4),
                                                           (2,0,5),
                                                           (3,4.99,6),
                                                           (1,0,7),
                                                           (2,5,8),
                                                           (5,26.99,9),
                                                           (2,7,10);


INSERT INTO download_info(  app_id, download_id ) VALUES(1,1),
                                                                        (2,1),
                                                                        (3,1),
                                                                        (4,2),
                                                                        (5,2),
                                                                        (6,3),
                                                                        (8,4),
                                                                        (2,4),
                                                                        (7,4),
                                                                        (9,4),
                                                                        (8,5),
                                                                        (2,6),
                                                                        (10,6),
                                                                        (7,6),
                                                                        (4,7),
                                                                        (4,8),
                                                                        (5,8),
                                                                        (6,9),
                                                                        (5,9),
                                                                        (8,9),
                                                                        (4,9),
                                                                        (1,9),
                                                                        (2,10),
                                                                        (9,10);



INSERT INTO feedback( description, created_date, rate, user_id , app_id) VALUES('nice try , go forward','2022-01-10', 4.0, 1, 3),
                                                                               ('could be better','2021-11-13', 3.0, 1, 1),
                                                                               ('and i paid money for that!','2022-05-30', 2.0, 2, 5),
                                                                               ('everything works totally fine','2020-09-16', 4.5, 4, 8),
                                                                               ('i like that app','2022-08-08', 5.0, 4, 2),
                                                                               ('uh....', '2022-09-08',1.0, 5, 8),
                                                                               ('nice app','2021-12-12', 4.0, 6, 10),
                                                                               ('a very good app','2022-08-08', 5.0, 6, 2),
                                                                               ('i hope you change the bugs','2022-03-18', 3.0, 8, 4),
                                                                               ('that is my favorite app','2022-02-20', 5.0, 9, 1),
                                                                               ('everything works fine','2022-02-20', 4.0, 9, 6),
                                                                               ('i like it','2021-09-10', 4.5, 10, 9);




INSERT INTO email_preferences( email, keep_with_up_to_date_news,receive_reply_notification, user_id ) VALUES('kateryna@gmail.com',true, false, 1),
                                                                                                            ('mirzoyan@gmail.com',true, false, 2),
                                                                                                            ('pikasomm@org.net',true, true, 3),
                                                                                                            ('veresss@gmail.com',false, false, 4),
                                                                                                            ('filomonova11@gmail.com',true, true, 5),
                                                                                                            ('mirzfilom@gukr.net',false, false, 5),
                                                                                                            ('govorotnhuk12@gmail.com',false, false, 6),
                                                                                                            ('govorotnhuk22@org.net',true, true, 6),
                                                                                                            ('tigran002@gmail.com',true, false, 7),
                                                                                                            ('kateryna_petr12@gmail.com',false, false, 8),
                                                                                                            ('olekseyinko_nina11@gmail.com',true, true, 9),
                                                                                                            ('kuleba_ua@gmail.com',true, true, 10),
                                                                                                            ('kin_olga7@gmail.com',false, true, 11),
                                                                                                            ('olga_kin99@ukr.net',true, false, 11),
                                                                                                            ('kostomarow-evg4@gmail.com',true, true, 12),
                                                                                                            ('dagni_li55@gmail.com',false, false, 13),
                                                                                                            ('taras_chmutt2@gmail.com',true, true, 14),
                                                                                                            ('t_chmutt1@org.net,false',false, false, 14);

-- Index
ALTER TABLE user ADD INDEX IX_User(full_name);
ALTER TABLE app ADD  INDEX IX_App(category_id);
ALTER TABLE creater ADD INDEX IX_Creater(full_name);
ALTER TABLE category ADD INDEX IX_Category(app_category_id);
ALTER TABLE feedback ADD INDEX IX_Feedback_User(user_id,app_id);
ALTER TABLE download ADD  INDEX IX_Download(user_id);
ALTER TABLE download_info ADD INDEX IX_Download_Info(download_id,app_id);

# SELECT * FROM user;
# SELECT * FROM app;
# SELECT * FROM creater;
# SELECT * FROM app_creating;
# SELECT * FROM app_category;
# SELECT * FROM category;
# SELECT * FROM download;
# SELECT * FROM download_info;
# SELECT * FROM feedback;
