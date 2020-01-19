/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : questiontest

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2020-01-19 11:33:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(100) NOT NULL,
  `qid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `answer_ibfk_1` (`qid`),
  CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`qid`) REFERENCES `questions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for paper
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `uid` int(11) NOT NULL,
  `data` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `paper_ibfk_1` (`uid`),
  CONSTRAINT `paper_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for questions
-- ----------------------------
DROP TABLE IF EXISTS `questions`;
CREATE TABLE `questions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `pid` int(11) NOT NULL,
  `qtype` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `questions_ibfk_1` (`pid`),
  CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `paper` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for superuser
-- ----------------------------
DROP TABLE IF EXISTS `superuser`;
CREATE TABLE `superuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `userpassword` varchar(30) NOT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `email` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  `confire` int(11) DEFAULT '0',
  `randomcode` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `randomcode` (`randomcode`),
  KEY `rcode` (`randomcode`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for useranswer
-- ----------------------------
DROP TABLE IF EXISTS `useranswer`;
CREATE TABLE `useranswer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `context` varchar(300) NOT NULL,
  `aid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `useranswer_ibfk_1` (`aid`),
  CONSTRAINT `useranswer_ibfk_1` FOREIGN KEY (`aid`) REFERENCES `questions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Procedure structure for getchoice
-- ----------------------------
DROP PROCEDURE IF EXISTS `getchoice`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getchoice`(in id int)
begin
	declare acount  int default 0;
    declare bcount  int default 0;
    declare ccount  int default 0;
    declare dcount  int default 0;
    declare qname varchar(30);
    select name into qname from questions where questions.id = id;
    select count(useranswer.context) into acount from useranswer where aid=id and useranswer.context="xuan0";
    select count(useranswer.context) into bcount from useranswer where aid=id and useranswer.context="xuan1";
    select count(useranswer.context) into ccount from useranswer where aid=id and useranswer.context="xuan2";
    select count(useranswer.context) into dcount from useranswer where aid=id and useranswer.context="xuan3";
    select qname,acount,bcount,ccount,dcount;
end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for getcount2
-- ----------------------------
DROP PROCEDURE IF EXISTS `getcount2`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getcount2`(in pid int)
begin 
	declare cou int default 0;
    declare anum int default 0;
    declare qnum int default 0;
    select count(*) into anum from paper,questions,answer where paper.id=questions.pid and questions.id =answer.qid and paper.id =pid;
    select count(*) into qnum from paper,questions where paper.id = questions.pid and paper.id=pid;
    if qnum=0 then 
		set cou =0;
	else 
		set cou = anum/qnum;
	end if;
    select cou;
end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for getcount3
-- ----------------------------
DROP PROCEDURE IF EXISTS `getcount3`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getcount3`(in pid int)
begin
	declare coun int default 0;
	declare anum int default 0;
    declare pnum int default 0;
    select count(*) into anum from questions,useranswer,paper where questions.pid=paper.id and useranswer.aid = questions.id and paper.id=pid;
    select count(*) into pnum from questions,paper where questions.pid=paper.id and paper.id=pid;
    if anum=0 then 
		set coun =0;
        else
        set coun= anum/pnum;
	end if;
    select coun;
end
;;
DELIMITER ;
