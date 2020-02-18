
-- Creation de la base

-- CREATE DATABASE bdd_bomberman DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

-- Creation de l'utilisateur de cette bdd

-- CREATE USER 'admin_bdd_bomberman'@'localhost' IDENTIFIED BY 'MdPBddBmbrmAn';
-- GRANT ALL ON bdd_bomberman.* TO 'admin_bdd_bomberman'@'localhost' IDENTIFIED BY 'MdPBddBmbrmAn';


-- #### TABLE #### --
DROP TABLE bdd_bomberman.Utilisateur CASCADE;

CREATE TABLE  bdd_bomberman.Utilisateur (
	id INT( 11 ) NOT NULL AUTO_INCREMENT,
	pseudo VARCHAR( 20 ) NOT NULL,
	password CHAR( 56 ) NOT NULL,
	nom VARCHAR( 20 ) NOT NULL ,
	prenom VARCHAR( 20 ) NOT NULL ,
	email VARCHAR( 60 ) NOT NULL ,
	date_naissance DATETIME NOT NULL,
	adresse VARCHAR( 50 ) NOT NULL,
	ville VARCHAR( 20 ) NOT NULL,
	code_postal CHAR( 5 ) NOT NULL,
	date_inscription DATETIME NOT NULL ,

	PRIMARY KEY ( id ),
	UNIQUE ( email ),
	UNIQUE ( pseudo )
) ENGINE = INNODB;

