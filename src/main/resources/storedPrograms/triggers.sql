
DELIMITER //
DROP TRIGGER IF EXISTS AddEmailCheckUserId //
CREATE TRIGGER AddEmailCheckUserId
    BEFORE INSERT
    ON `lovushkina`.`email_preferences`
    FOR EACH ROW
BEGIN
    IF(NOT EXISTS(
            SELECT id FROM user
            WHERE id = NEW.user_id
        ))
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Foreign key error: No user with such id';
    END IF;
END //

DROP TRIGGER IF EXISTS UpdateEmailCheckUserId //
CREATE TRIGGER UpdateEmailCheckUserId
    BEFORE UPDATE
    ON `lovushkina`.`email_preferences`
    FOR EACH ROW
BEGIN
    IF(NOT EXISTS(
            SELECT id FROM user
            WHERE id = NEW.user_id
        ))
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Foreign key error: No user with such id';
    END IF;
END //

DROP TRIGGER IF EXISTS DeleteUserCheckForeignKey //
CREATE TRIGGER DeleteUserCheckForeignKey
    BEFORE DELETE
    ON `lovushkina`.`user`
    FOR EACH ROW
BEGIN
    IF(EXISTS(
            SELECT user_id FROM email_preferences
            WHERE user_id = OLD.id
        ))
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Foreign key error: user id is exist in email_preferences and cant be deleted';
    END IF;
END //

DROP TRIGGER IF EXISTS UpdateUserCheckForeignKey //
CREATE TRIGGER UpdateUserCheckForeignKey
    BEFORE UPDATE
    ON `lovushkina`.`user`
    FOR EACH ROW
BEGIN
    IF(EXISTS(
            SELECT user_id FROM email_preferences
            WHERE user_id = OLD.id
        ))
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Foreign key error: user id is exist in email_preferences and cant be updated';
    END IF;
END //

DROP TRIGGER  IF EXISTS ValidateUserEmail //
CREATE TRIGGER ValidateUserEmail
    BEFORE INSERT
    ON `lovushkina`.`email_preferences`
    FOR EACH ROW
BEGIN
    IF (NEW.email REGEXP '^[a-zA-Z0-9_+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-]+') = 0
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Email key error: incorrect email';
    END IF;
END //


DROP TRIGGER IF EXISTS AppCategoryCardinality //
CREATE TRIGGER AppCategoryCardinality
    AFTER  INSERT
    ON `lovushkina`.`app_category`
    FOR EACH ROW
BEGIN
    -- DECLARE amount_rows int;
    SET @amount_rows := (SELECT COUNT(*) FROM app_category);
    IF  @amount_rows>12
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'You can  insert row because of range cardinality';
    END IF;
END //;

DROP TRIGGER IF EXISTS AfterInsertAppCategory //
CREATE TRIGGER AfterInsertAppCategory
    AFTER INSERT
    ON `lovushkina`.`app_category`
    FOR EACH ROW
BEGIN
    INSERT INTO lovushkina.appCategoryInsertInfo(id, nameAppCategory, description, action, timeStamp, user)
    VALUES(new.id,new.name, new.description, 'insert', NOW(), USER());
END //
DELIMITER ;

                                                                                                           
