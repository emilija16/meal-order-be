INSERT INTO `ordermeal`.`role` (`id`, `name_role`) VALUES (1, 'ROLE_ADMIN');
INSERT INTO `ordermeal`.`role` (`id`, `name_role`) VALUES (2, 'ROLE_USER');

INSERT INTO `ordermeal`.`user` (`email`, `first_name`, `is_active`, `last_name`, `password`, `phone_number`, `username`, `role_id`) VALUES ('admin@admin', 'admin', 1, 'admin', '$2a$10$uXdbQlCVNkwAaXFe7pms6ekjFENAtMQ9lxe9efRmZFrShipQRLLLi', '555-555', 'admin', 1);

INSERT INTO `ordermeal`.`meal_size` (`name`) VALUES ('Velika porcija');
INSERT INTO `ordermeal`.`meal_size` (`name`) VALUES ('Mala porcija');

INSERT INTO `ordermeal`.`meal_type` (`name`) VALUES ('Fit obrok');
INSERT INTO `ordermeal`.`meal_type` (`name`) VALUES ('Domaca kuhinja');
INSERT INTO `ordermeal`.`meal_type` (`name`) VALUES ('Brza hrana');