INSERT INTO `ordermeal`.`role` (`id`, `name_role`) VALUES (1, 'ROLE_ADMIN');
INSERT INTO `ordermeal`.`role` (`id`, `name_role`) VALUES (2, 'ROLE_USER');

INSERT INTO `ordermeal`.`user` (`email`, `first_name`, `is_active`, `last_name`, `password`, `phone_number`, `username`, `role_id`)
VALUES ('admin@admin', 'admin', 1, 'admin', '$2a$10$uXdbQlCVNkwAaXFe7pms6ekjFENAtMQ9lxe9efRmZFrShipQRLLLi', '0611111111', 'admin', 1);

INSERT INTO `ordermeal`.`user` (`email`, `first_name`, `is_active`, `last_name`, `password`, `phone_number`, `username`, `role_id`)
VALUES ('user@user', 'Emilija', 1, 'Drazic', '$2a$12$Mm9Hi76RlcSGYnk93wkEsOgWECfxWzYIb.7Pby8deOP8p7q6xvBP', '0633333333', 'user', 2);

INSERT INTO `ordermeal`.`meal_size` (`name`) VALUES ('Big');
INSERT INTO `ordermeal`.`meal_size` (`name`) VALUES ('Small');

INSERT INTO `ordermeal`.`meal_type` (`name`) VALUES ('Fit');
INSERT INTO `ordermeal`.`meal_type` (`name`) VALUES ('Regular');
INSERT INTO `ordermeal`.`meal_type` (`name`) VALUES ('Salad');
INSERT INTO `ordermeal`.`meal_type` (`name`) VALUES ('Dessert');

INSERT INTO `ordermeal`.`meal` (`name`, `price`, `meal_size`, `meal_type`, `contributions`, `description`, `image`, `is_tomorrow`) 
VALUES ('Grilled Chicken', 700, 1, 2, 'Salad', 'Grilled Chicken', '../../../../assets/images/meals/chicken.jpg', 0);

INSERT INTO `ordermeal`.`meal` (`name`, `price`, `meal_size`, `meal_type`, `contributions`, `description`, `image`, `is_tomorrow`) 
VALUES ('Caesar Salad', 450, 2, 3, 'None', 'A tossed salad usually made of romaine, garlic, anchovies,
and croutons and dressed with olive oil, coddled egg, lemon juice, and grated cheese', '../../../../assets/images/meals/salad.jpg', 1);

INSERT INTO `ordermeal`.`meal` (`name`, `price`, `meal_size`, `meal_type`, `contributions`, `description`, `image`, `is_tomorrow`) 
VALUES ('Creamy Soup', 600, 1, 2, 'Green Salad', 'Creamy Chicken Soup', '../../../../assets/images/meals/soup.jpg', 1);

