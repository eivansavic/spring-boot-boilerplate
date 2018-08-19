CREATE TABLE `verification_token` (
  `id` bigint(20) NOT NULL,
  `expiry_date` datetime NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NULL,
PRIMARY KEY (`id`),
CONSTRAINT `user_id`
FOREIGN KEY (`user_id`)
REFERENCES `user` (`id`)
ON DELETE NO ACTION
ON UPDATE NO ACTION);