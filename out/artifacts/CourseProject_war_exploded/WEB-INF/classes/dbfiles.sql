USE `accounts`;
--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) NOT NULL,
  `file_owner` int(11) DEFAULT NULL,
  `file_path` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_file_file_owner` FOREIGN KEY (`file_owner`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;