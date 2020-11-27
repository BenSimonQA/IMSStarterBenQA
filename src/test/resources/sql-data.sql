INSERT INTO `ims`.`customers` (`first_name`, `surname`) VALUES ('Jordan', 'Harrison');
INSERT INTO `ims`.`customers` (`first_name`, `surname`) VALUES ('Jack', 'Sparrow');
INSERT INTO `ims`.`items` (`item_name`, `price`) VALUES ('Black Ops', '20');
INSERT INTO `ims`.`items` (`item_name`, `price`) VALUES ('Tetris', '10');
INSERT INTO `ims`.`orders` (`fk_customer_id`) VALUES ('1');
INSERT INTO `ims`.`orderslists` (`fk_order_id`, `fk_item_id`) VALUES ('1','1');
