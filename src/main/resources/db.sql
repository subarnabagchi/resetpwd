DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    `id`               INTEGER PRIMARY KEY AUTO_INCREMENT,
    `first_name`       VARCHAR(60),
    `last_name`        VARCHAR(60),
    `email_id`         VARCHAR(255) UNIQUE not null,
    `password`         VARCHAR(60)         not null,
    `enabled`          BOOLEAN,
    `sys_created_time` DATETIME,
    `last_login_time`  DATETIME
);
