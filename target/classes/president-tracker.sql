CREATE DATABASE IF NOT EXISTS `president_tracker` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `president_tracker`;

--
-- Table structure for table `president`
--

DROP TABLE IF EXISTS `president`;
CREATE TABLE `president` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `party` varchar(45) DEFAULT NULL,
  `term_start` date,
  `term_end` date,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

LOCK TABLES `president` WRITE;

INSERT INTO `president` VALUES 
	(1,'George','Washington','None',17890430,17970304),
	(2,'John','Adams','Federalist',17970304,18010304),
	(3,'Thomas','Jefferson','Democratic-Republican',18010304,18090304),
	(4,'James','Madison','Democratic-Republican',18090304,18170304),
	(5,'James','Monroe','Democratic-Republican',18170304,18250304),
	(6,'John Quincy','Adams','Democratic-Republican',18250304,18290304),
	(7,'Andrew','Jackson','Democratic',18290304,18370304),
	(8,'Martin','Van Buren','Democratic',18370304,18410304),
	(9,'William Henry','Harrison','Whig',18410304,18410404),
	(10,'John','Tyler','Independent',18410404,18450304),
	(11,'James K.','Polk','Democratic',18450304,18490304),
	(12,'Zachary','Taylor','Whig',18490304,18500709),
	(13,'Millard','Fillmore','Whig',18500709,18530304),
	(14,'Franklin','Pierce','Democratic',18530304,18570304),
	(15,'James','Buchanan','Democratic',18570304,18610304),
	(16,'Abraham','Lincoln','Republican',18610304,18650415),
	(17,'Andrew','Johnson','Independent',18650415,18690304),
	(18,'Ulysses S.','Grant','Republican',18690304,18770304),
	(19,'Rutherford B.','Hayes','Republican',18770304,18810304),
	(20,'James','Garfield','Republican',18810919,18810919),
	(21,'Chester A.','Arthur','Republican',18810304,18850304),
	(22,'Grover','Cleveland','Democratic',18850304,18890304),
	(23,'Benjamin','Harrison','Republican',18890304,18930304),
	(24,'Grover','Cleveland','Democratic',18930304,18970304),
	(25,'William','McKinley','Republican',18970304,19010914),
	(26,'Theodore','Roosevelt','Republican',19010914,19090304),
	(27,'William Howard','Taft','Republican',19090304,19130304),
	(28,'Woodrow','Wilson','Democratic',19130304,19210304),
	(29,'Warren','Harding','Republican',19210304,19230802),
	(30,'Calvin','Coolidge','Republican',19230802,19290304),
	(31,'Herbert','Hoover','Republican',19290304,19330304),
	(32,'Franklin','Roosevelt','Democratic',19330304,19450412),
	(33,'Harry','Truman','Democratic',19450412,19530120),
	(34,'Dwight','Eisenhower','Republican',19530120,19610120),
	(35,'John F.','Kennedy','Democratic',19610120,19631122),
	(36,'Lyndon','Johnson','Democratic',19631122,19690120),
	(37,'Richard','Nixon','Republican',19690120,19740809),
	(38,'Gerald','Ford','Republican',19740809,19770120),
	(39,'Jimmy','Carter','Democratic',19770120,19810120),
	(40,'Ronald','Reagan','Republican',19810120,19890120),
	(41,'George H. W.','Bush','Republican',19890120,19930120),
	(42,'Bill','Clinton','Democratic',19930120,20010120),
	(43,'George W.','Bush','Republican',20010120,20090120),
	(44,'Barack','Obama','Democratic',20090120,20170120),
	(45,'Donald','Trump','Republican',20170120,20210120);

UNLOCK TABLES;