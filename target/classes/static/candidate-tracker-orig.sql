CREATE DATABASE IF NOT EXISTS `president_tracker` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `president_tracker`;

--
-- Table structure for table `candidate`
--

DROP TABLE IF EXISTS `candidate`;
CREATE TABLE `candidate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `party` varchar(45) DEFAULT NULL,
  `term_start` int(4),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

LOCK TABLES `candidate` WRITE;

INSERT INTO `candidate` VALUES 
	(1, 'John','Jay','Federalist',1788),
	(2, 'Thomas','Jefferson','Democratic-Republican',1796),
	(3, 'John','Adams','Federalist',1800),
	(4, 'Charles','Pinckney','Federalist',1808),
	(5, 'Rufus','King','Federalist',1816),
	(6, 'Andrew','Jackson','Democratic',1824),
	(7, 'John Quincy','Adams','Democratic-Republican',1828),
	(8, 'William Henry','Harrison','Whig',1836),
	(9, 'Martin','Van Buren','Democratic',1840),
	(10, 'Henry','Clay','Whig',1844),
	(11, 'Lewis','Cass','Democratic',1848),
	(12, 'Winfield','Scott','Whig',1852),
	(13, 'John C.','Fremont','Republican',1856),
	(14, 'Stephen A.','Douglas','Democratic',1860),
	(15, 'George B.','McClellan','Democratic',1864),
	(16, 'Horatio','Seymour','Democratic',1868),
	(17, 'Samuel J.','Tilden','Democratic',1876),
	(18, 'Winfield S.','Hancock','Democratic',1880),
	(19, 'James G.','Blaine','Republican',1884),
	(20, 'Grover','Cleveland','Democratic',1888),
	(21, 'Benjamin','Harrison','Republican',1892),
	(22, 'William Jennings','Bryan','Democratic',1896),
	(23, 'William Jennings','Bryan','Democratic',1908),
	(24, 'William Howard','Taft','Republican',1912),
	(25, 'James M.','Cox','Democratic',1920),
	(26, 'Al','Smith','Democratic',1928),
	(27, 'Herbert','Hoover','Republican',1932),
	(28, 'Adlai','Stevenson','Democratic',1952),
	(29, 'Richard','Nixon','Republican',1960),
	(30, 'Hubert','Humphrey','Democratic',1968),
	(31, 'Gerald','Ford','Republican',1976),
	(32, 'Jimmy','Carter','Democratic',1980),
	(33, 'Michael','Dukakis','Democratic',1988),
	(34, 'George H. W.','Bush','Republican',1992),
	(35, 'Al','Gore','Democratic',2000),
	(36, 'John','McCain','Republican',2008),
	(37, 'Hillary','Clinton','Democratic',2016);

UNLOCK TABLES;