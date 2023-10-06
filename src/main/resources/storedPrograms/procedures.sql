use lovushkina;

DELIMITER //
DROP PROCEDURE IF EXISTS CreaterInsert //
CREATE PROCEDURE CreaterInsert(
    IN new_full_name varchar(60),
    IN new_work_branch varchar(50),
    IN new_email varchar(70),
    IN new_created_app_amount int)
BEGIN
    INSERT INTO `lovushkina`.`creater`( full_name, work_branch, email, created_app_amount )
    VALUES(new_full_name,new_work_branch,new_email,new_created_app_amount);
    SELECT * FROM `creater` WHERE email = new_email;
END //

DROP PROCEDURE IF EXISTS ManyToManyRelationShip //
CREATE PROCEDURE ManyToManyRelationShip(
    IN app_name varchar(25),
    IN creater_full_name varchar(60))
BEGIN
    DECLARE app_id, creater_id INT;
    SELECT id INTO app_id FROM `lovushkina`.`app` WHERE name = app_name;
    SELECT id INTO creater_id FROM `lovushkina`.`creater` WHERE full_name = creater_full_name;
    IF (app_id IS NULL)
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Null value: cant found app with such name';
    END IF;
    IF (creater_id IS NULL)
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Null value: cant found creater with such name';
    END IF;
    INSERT INTO `lovushkina`.`app_creating`(app_id, creater_id) VALUES(app_id, creater_id);
END //

DROP PROCEDURE IF EXISTS Insert10Rows //
CREATE PROCEDURE Insert10Rows(
    IN new_name varchar(30),
    IN new_description text)
BEGIN
    DECLARE max_id INT;
    DECLARE counter INT;
    SELECT MAX(id)+1 INTO max_id FROM app_category;
    IF max_id IS NULL THEN
        SET max_id = 1;
    END IF;
    SET counter = 1;
    loop1:
    WHILE counter < 11
        DO
            INSERT INTO `lovushkina`.`app_category`(name, description)
            VALUES(CONCAT(new_name,'-',max_id),CONCAT(new_description,'-',max_id));
            SET max_id = max_id + 1;
            SET counter = counter +1;
            ITERATE loop1;
        END WHILE;
END //

DROP PROCEDURE IF EXISTS FindMaxAppCount //
CREATE PROCEDURE FindMaxAppCount()
BEGIN
    SELECT GetMaxAppCount() AS max_app_created_count;
END //

DROP PROCEDURE IF EXISTS CreateTablesWithCursor //
CREATE PROCEDURE CreateTablesWithCursor()
BEGIN
    DECLARE done BOOL DEFAULT false;
    DECLARE name VARCHAR(30);
    DECLARE my_cursor CURSOR FOR SELECT full_name FROM `user`;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = true;
    OPEN my_cursor;
    loop1: LOOP
        FETCH my_cursor INTO name;
        IF (done = true) THEN LEAVE loop1;
        END IF;
        SET @temp_query = CONCAT('CREATE TABLE `', @table_name, '` (
                          id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          age INT NOT NULL
);');
        PREPARE my_query FROM @temp_query;
        EXECUTE my_query;
        DEALLOCATE PREPARE my_query;
    END LOOP;
    CLOSE my_cursor;
END //
DELIMITER ;