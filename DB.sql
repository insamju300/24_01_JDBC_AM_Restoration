################################################
################################################
DROP DATABASE IF EXISTS `JDBC_AM`;
CREATE DATABASE `JDBC_AM`;
USE `JDBC_AM`;

CREATE TABLE `member`(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    loginId CHAR(30) NOT NULL UNIQUE,
    loginPw CHAR(200) NOT NULL,
    `name` CHAR(100) NOT NULL
);


CREATE TABLE article(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    title CHAR(100) NOT NULL,
     writerId INT(10) UNSIGNED NOT NULL,
    `body` TEXT NOT NULL,
     FOREIGN KEY (`writerId`) REFERENCES `member` (`id`)
);

CREATE UNIQUE INDEX `loginIdIndex` ON `member` (`loginId`);



INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test1',
loginPw = 'test1',
`name` = 'test1';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test2',
loginPw = 'test2',
`name` = 'test2';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = CONCAT('제목', RAND()),
`body` = CONCAT('내용', RAND()),
writerId = 1;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = CONCAT('제목', RAND()),
`body` = CONCAT('내용', RAND()),
writerId = 2;

SELECT *
FROM `member`;

SELECT *
FROM article
ORDER BY id DESC;


#############################################################


UPDATE article
SET updateDate = NOW(),
title = 'abc'
WHERE id = 6;

### loginId : test1
SELECT *
FROM `member`
WHERE loginId = 'test1';

SELECT COUNT(*) > 0
FROM `member`
WHERE loginId = 'test1';

SELECT COUNT(*) > 0
FROM `member`
WHERE loginId = 'test3';

SELECT 1 + 1;
SELECT 1 > 1;

