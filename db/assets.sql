DROP DATABASE IF EXISTS assetsDataBase;
CREATE DATABASE assetsDataBase;

CREATE USER 'newuser'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON * . * TO 'newuser'@'localhost';

FLUSH PRIVILEGES;

USE assetsDataBase;

CREATE TABLE `loans` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `idLoan` varchar(255) NOT NULL,
  `paymentPeriod` varchar(255) NOT NULL,
  `interest` DOUBLE NOT NULL,
  `amortizationTime` INTEGER NOT NULL,
  `initialCapital` DOUBLE NOT NULL,
  `debt` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`idLoan`)
);


