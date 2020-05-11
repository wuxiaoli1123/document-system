DROP TABLE IF EXISTS `user_tb`;
CREATE TABLE `user_tb` (
   `account` varchar(10) NOT NULL,
   `password` varchar(10) NOT NULL,
   `role` char(1) NOT NULL,
  PRIMARY KEY (`account`)
) ;

