-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `mylibrary` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `mylibrary` ;

-- -----------------------------------------------------
-- Table `mylibrary`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mylibrary`.`user` (
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  `usertype` VARCHAR(10) NULL DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mylibrary`.`publisher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mylibrary`.`publisher` (
  `PUBID` INT(11) NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Phone` INT(11) NOT NULL,
  `ContactPerson` VARCHAR(45) NULL DEFAULT NULL,
  `Adress` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`PUBID`, `username`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  CONSTRAINT `username`
    FOREIGN KEY (`username`)
    REFERENCES `mylibrary`.`user` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mylibrary`.`book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mylibrary`.`book` (
  `ISBN` INT(11) NOT NULL,
  `Title` VARCHAR(45) NOT NULL,
  `Author` VARCHAR(45) NOT NULL,
  `PublishingYear` DATE NOT NULL,
  `Review` VARCHAR(100) NULL DEFAULT NULL,
  `Publisher_PUBID` INT(11) NOT NULL,
  PRIMARY KEY (`ISBN`),
  UNIQUE INDEX `ISBN` (`ISBN` ASC) VISIBLE,
  INDEX `Publisher_PUBID` (`Publisher_PUBID` ASC) VISIBLE,
  CONSTRAINT `book_ibfk_1`
    FOREIGN KEY (`Publisher_PUBID`)
    REFERENCES `mylibrary`.`publisher` (`PUBID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mylibrary`.`library`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mylibrary`.`library` (
  `BookID` INT(11) NOT NULL AUTO_INCREMENT,
  `Description` VARCHAR(45) NULL DEFAULT NULL,
  `Book_ISBN` INT(11) NOT NULL,
  PRIMARY KEY (`BookID`),
  UNIQUE INDEX `BookID` (`BookID` ASC) VISIBLE,
  INDEX `Book_ISBN` (`Book_ISBN` ASC) VISIBLE,
  CONSTRAINT `library_ibfk_1`
    FOREIGN KEY (`Book_ISBN`)
    REFERENCES `mylibrary`.`book` (`ISBN`))
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mylibrary`.`member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mylibrary`.`member` (
  `IDM` INT(11) NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Surname` VARCHAR(45) NOT NULL,
  `Phone` VARCHAR(45) NOT NULL,
  `Adress` VARCHAR(45) NULL DEFAULT NULL,
  `Reg` DATE NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`IDM`, `username`),
  UNIQUE INDEX `Phone` (`Phone` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  CONSTRAINT `username_member`
    FOREIGN KEY (`username`)
    REFERENCES `mylibrary`.`user` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mylibrary`.`borrow`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mylibrary`.`borrow` (
  `Date_From` DATE NOT NULL,
  `Date_To` DATE NOT NULL,
  `Library_BookID` INT(11) NOT NULL,
  `Member_IDM` INT(11) NOT NULL,
  PRIMARY KEY (`Library_BookID`, `Member_IDM`),
  INDEX `Member_IDM` (`Member_IDM` ASC) VISIBLE,
  CONSTRAINT `borrow_ibfk_1`
    FOREIGN KEY (`Library_BookID`)
    REFERENCES `mylibrary`.`library` (`BookID`),
  CONSTRAINT `borrow_ibfk_2`
    FOREIGN KEY (`Member_IDM`)
    REFERENCES `mylibrary`.`member` (`IDM`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mylibrary`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mylibrary`.`employee` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `pin` VARCHAR(45) NOT NULL,
  `birthdate` VARCHAR(30) NOT NULL,
  `phone` INT(11) NOT NULL,
  `salary` DOUBLE NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `username`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  CONSTRAINT `username_emp`
    FOREIGN KEY (`username`)
    REFERENCES `mylibrary`.`user` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
