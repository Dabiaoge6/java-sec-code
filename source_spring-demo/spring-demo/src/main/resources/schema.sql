DROP TABLE IF EXISTS `person`;
CREATE TABLE IF NOT EXISTS `person` (
                          `id` varchar(255) NOT NULL,
                          `city` varchar(255) NOT NULL,
                          `province` varchar(255) NOT NULL,
                          `idCard` varchar(3200) NOT NULL,
                          `name` varchar(3200) NOT NULL,
                          `phone` varchar(3200) NOT NULL,
                          PRIMARY KEY (`id`)
);
INSERT INTO `person`(`id`, `city`, `province`, `idCard`, `name`, `phone`) VALUES ('2c9532717949e7b3017949f71b310002', '南京', 'sj', '1111', 'a', '1212');
INSERT INTO `person`(`id`, `city`, `province`, `idCard`, `name`, `phone`) VALUES ('8a80819c7949f4de017949f6fb4c0000', 'dsewd', 'anhui', '20318989832434', 'zxcv', '15132454657');
INSERT INTO `person`(`id`, `city`, `province`, `idCard`, `name`, `phone`) VALUES ('8a80819c7949f4de017949f724130001', 'dsewd', 'anhui', '20318989832434', 'zxcv', '15132454657');
INSERT INTO `person`(`id`, `city`, `province`, `idCard`, `name`, `phone`) VALUES ('8a80819c7949f4de017949f792000002', 'wer', 'ferf', '1213324324324535', 'sdwfd', '21324324');
INSERT INTO `person`(`id`, `city`, `province`, `idCard`, `name`, `phone`) VALUES ('8a80819c7949f4de017949f8581f0003', '安徽', 'ferr', '324352324324355435', 'dwf', '143545456546');
INSERT INTO `person`(`id`, `city`, `province`, `idCard`, `name`, `phone`) VALUES ('8a80819c7949f4de017949fa39f20004', '合肥', '安徽', '513436200005086219', '张三', '13905398888');
INSERT INTO `person`(`id`, `city`, `province`, `idCard`, `name`, `phone`) VALUES ('8a80819c794a04b701794a0560eb0000', 'wew2', 'dewf1', '513436200005087991', 'csdc', '13905398888');
INSERT INTO `person`(`id`, `city`, `province`, `idCard`, `name`, `phone`) VALUES ('8a80819c79b2eca40179b2fdd14f0000', 'der', 'dwed', '232321321', 'ddewf', '1232132');
INSERT INTO `person`(`id`, `city`, `province`, `idCard`, `name`, `phone`) VALUES ('8a80819c79b2eca40179b2fdeff80001', 'der', 'dwed', '232321321', 'ddewf', '1232132');
