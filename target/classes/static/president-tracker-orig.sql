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
  `term_start` int(4),
  `term_end` int(4),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

LOCK TABLES `president` WRITE;

INSERT INTO `president` VALUES 
	(1,'George','Washington','None',1789,1797),
	(2,'John','Adams','Federalist',1797,1801),
	(3,'Thomas','Jefferson','Democratic-Republican',1801,1809),
	(4,'James','Madison','Democratic-Republican',1809,1817),
	(5,'James','Monroe','Democratic-Republican',1817,1825),
	(6,'John Quincy','Adams','Democratic-Republican',1825,1829),
	(7,'Andrew','Jackson','Democratic',1829,1837),
	(8,'Martin','Van Buren','Democratic',1837,1841),
	(9,'William Henry','Harrison','Whig',1841,1841),
	(10,'John','Tyler','Independent',1841,1845),
	(11,'James K.','Polk','Democratic',1845,1849),
	(12,'Zachary','Taylor','Whig',1849,1850),
	(13,'Millard','Fillmore','Whig',1850,1853),
	(14,'Franklin','Pierce','Democratic',1853,1857),
	(15,'James','Buchanan','Democratic',1857,1861),
	(16,'Abraham','Lincoln','Republican',1861,1865),
	(17,'Andrew','Johnson','Independent',1865,1869),
	(18,'Ulysses S.','Grant','Republican',1869,1877),
	(19,'Rutherford B.','Hayes','Republican',1877,1881),
	(20,'James','Garfield','Republican',1881,1881),
	(21,'Chester A.','Arthur','Republican',1881,1885),
	(22,'Grover','Cleveland','Democratic',1885,1889),
	(23,'Benjamin','Harrison','Republican',1889,1893),
	(24,'Grover','Cleveland','Democratic',1893,1897),
	(25,'William','McKinley','Republican',1897,1901),
	(26,'Theodore','Roosevelt','Republican',1901,1909),
	(27,'William Howard','Taft','Republican',1909,1913),
	(28,'Woodrow','Wilson','Democratic',1913,1921),
	(29,'Warren','Harding','Republican',1921,1923),
	(30,'Calvin','Coolidge','Republican',1923,1929),
	(31,'Herbert','Hoover','Republican',1929,1933),
	(32,'Franklin','Roosevelt','Democratic',1933,1945),
	(33,'Harry','Truman','Democratic',1945,1953),
	(34,'Dwight','Eisenhower','Republican',1953,1961),
	(35,'John F.','Kennedy','Democratic',1961,1963),
	(36,'Lyndon','Johnson','Democratic',1963,1969),
	(37,'Richard','Nixon','Republican',1969,1974),
	(38,'Gerald','Ford','Republican',1974,1977),
	(39,'Jimmy','Carter','Democratic',1977,1981),
	(40,'Ronald','Reagan','Republican',1981,1989),
	(41,'George H. W.','Bush','Republican',1989,1993),
	(42,'Bill','Clinton','Democratic',1993,2001),
	(43,'George W.','Bush','Republican',2001,2009),
	(44,'Barack','Obama','Democratic',2009,2017),
	(45,'Donald','Trump','Republican',2017,2018);

UNLOCK TABLES;