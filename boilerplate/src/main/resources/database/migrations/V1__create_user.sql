CREATE TABLE `user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(128) NOT NULL,
  `email_subscribed` bit(1) NOT NULL,
  `email_verified` bit(1) NOT NULL,
  `hash_password` VARCHAR(255) NOT NULL,
  `phone_number` VARCHAR(50) NULL,
  `role` VARCHAR(255) NOT NULL,
  `deleted` bit(1) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `id_UNIQUE` (`id` ASC),
UNIQUE INDEX `email_UNIQUE` (`email` ASC));