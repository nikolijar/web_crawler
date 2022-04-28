CREATE TABLE `nekretnina` (
  `id` int NOT NULL,
  `tip` varchar(20) DEFAULT NULL,
  `ponuda` varchar(20) DEFAULT NULL,
  `grad` varchar(50) DEFAULT NULL,
  `deo_grada` varchar(100) DEFAULT NULL,
  `mikrolokacija` varchar(100) DEFAULT NULL,
  `kvadratura` decimal(10,2) DEFAULT NULL,
  `starost` varchar(20) DEFAULT NULL,
  `povrsina_zemljista` decimal(10,2) DEFAULT NULL,
  `sprat` decimal(5,1) DEFAULT NULL,
  `ukupna_spratnost` smallint DEFAULT NULL,
  `uknjizen` tinyint DEFAULT NULL,
  `grejanje` varchar(20) DEFAULT NULL,
  `broj_soba` decimal(5,1) DEFAULT NULL,
  `parking` tinyint DEFAULT NULL,
  `lift` tinyint DEFAULT NULL,
  `terasa` tinyint DEFAULT NULL,
  `izdavanje_na_dan` tinyint DEFAULT NULL,
  `depozit` tinyint DEFAULT NULL,
  `cena` decimal(10,2) DEFAULT NULL,
  `mesecne_rezije` decimal(10,2) DEFAULT NULL,
  `link` varchar(200) DEFAULT NULL,
  `valid` tinyint DEFAULT '0',
  PRIMARY KEY (`id`)
);