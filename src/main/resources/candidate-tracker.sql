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
  `election_year` int(4),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

LOCK TABLES `candidate` WRITE;

INSERT INTO `candidate` VALUES 
	(1, 'John','Jay','Federalist',1788),
    (2, 'George','Clinton','Democratic-Republican',1792),
	(3, 'Thomas','Jefferson','Democratic-Republican',1796),
	(4, 'John','Adams','Federalist',1800),
    (5, 'Charles','Pinckney','Federalist',1804),
	(6, 'Charles','Pinckney','Federalist',1808),
    (7, 'DeWitt','Clinton','Democratic-Republican',1812),
	(8, 'Rufus','King','Federalist',1816),
    (9, 'Richard','Stockton','Federalist',1820),
	(10, 'Andrew','Jackson','Democratic',1824),
	(11, 'John Quincy','Adams','Democratic-Republican',1828),
    (12, 'Henry','Clay','National Republican',1832),
	(13, 'William Henry','Harrison','Whig',1836),
	(14, 'Martin','Van Buren','Democratic',1840),
	(15, 'Henry','Clay','Whig',1844),
	(16, 'Lewis','Cass','Democratic',1848),
	(17, 'Winfield','Scott','Whig',1852),
	(18, 'John C.','Fremont','Republican',1856),
	(19, 'Stephen A.','Douglas','Democratic',1860),
	(20, 'George B.','McClellan','Democratic',1864),
	(21, 'Horatio','Seymour','Democratic',1868),
    (22, 'Horace','Greeley','Liberal Republican',1872),
	(23, 'Samuel J.','Tilden','Democratic',1876),
	(24, 'Winfield S.','Hancock','Democratic',1880),
	(25, 'James G.','Blaine','Republican',1884),
	(26, 'Grover','Cleveland','Democratic',1888),
	(27, 'Benjamin','Harrison','Republican',1892),
	(28, 'William Jennings','Bryan','Democratic',1896),
    (29, 'William Jennings','Bryan','Democratic',1900),
    (30, 'Alton','Parker','Democratic',1904),
	(32, 'William Jennings','Bryan','Democratic',1908),
	(33, 'William Howard','Taft','Republican',1912),
    (34, 'Charles Evans','Hughes','Republican',1916),
	(35, 'James M.','Cox','Democratic',1920),
    (36, 'John W.','Davis','Democratic',1924),
	(37, 'Al','Smith','Democratic',1928),
	(38, 'Herbert','Hoover','Republican',1932),
    (39, 'Alf','Landon','Republican',1936),
    (40, 'Wendell','Willkie','Republican',1940),
    (41, 'Thomas','Dewey','Republican',1944),
    (42, 'Thomas','Dewey','Republican',1948),
	(43, 'Adlai','Stevenson','Democratic',1952),
    (44, 'Adlai','Stevenson','Democratic',1956),
	(45, 'Richard','Nixon','Republican',1960),
    (46, 'Barry','Goldwater','Republican',1964),
	(47, 'Hubert','Humphrey','Democratic',1968),
    (48, 'George','McGovern','Democratic',1972),
	(49, 'Gerald','Ford','Republican',1976),
	(50, 'Jimmy','Carter','Democratic',1980),
    (51, 'Walter','Mondale','Democratic',1984),
	(52, 'Michael','Dukakis','Democratic',1988),
	(53, 'George H. W.','Bush','Republican',1992),
    (54, 'Bob','Dole','Republican',1996),
	(55, 'Al','Gore','Democratic',2000),
    (56, 'John','Kerry','Democratic',2004),
	(57, 'John','McCain','Republican',2008),
    (58, 'Mitt','Romney','Republican',2012),
	(59, 'Hillary','Clinton','Democratic',2016);

UNLOCK TABLES;