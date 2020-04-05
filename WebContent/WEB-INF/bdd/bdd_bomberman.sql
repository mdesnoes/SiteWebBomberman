
-- #### Creation de la base #### --
-- CREATE DATABASE bdd_bomberman DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

-- #### Creation de l'utilisateur de cette bdd #### --
-- CREATE USER 'admin_bdd_bomberman'@'localhost' IDENTIFIED BY 'MdPBddBmbrmAn';
-- GRANT ALL ON bdd_bomberman.* TO 'admin_bdd_bomberman'@'localhost' IDENTIFIED BY 'MdPBddBmbrmAn';

-- #### TABLE #### --
-- DROP TABLE bdd_bomberman.Utilisateur CASCADE;

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


-- DROP TABLE bdd_bomberman.Partie CASCADE;

CREATE TABLE bdd_bomberman.Partie (
	id INT( 11 ) NOT NULL AUTO_INCREMENT,
	date_debut DATETIME NOT NULL,
	date_fin DATETIME NOT NULL,
	vainqueur VARCHAR( 30 ) NOT NULL,
	
	PRIMARY KEY ( id )
) ENGINE = INNODB;

-- DROP TABLE bdd_bomberman.Boutique CASCADE;

CREATE TABLE bdd_bomberman.Boutique (
    id INT( 11 ) NOT NULL AUTO_INCREMENT,
    nom VARCHAR( 20 ) NOT NULL,
    type VARCHAR ( 20 ) NOT NULL,
    prix VARCHAR( 5 ) NOT NULL,
    description VARCHAR ( 200 ) NOT NULL,
    image VARCHAR ( 50 ) NOT NULL,
    PRIMARY KEY( id ),
    UNIQUE( nom )
) ENGINE = INNODB;


-- #### Les objets à vendre #### --
-- BONUS --
INSERT INTO Boutique (nom, type, prix, description, image)
VALUES ('Bombe+', 'Bonus', '1000', 'Augmente la portée de la bombe', 'img/Boutique/bombe.png');

INSERT INTO Boutique (nom, type, prix, description, image)
VALUES ('Vitesse+', 'Bonus', '1500', 'Augmente la vitesse du joueur', 'img/Boutique/vitesse.png');

-- SKIN --
INSERT INTO Boutique (nom, type, prix, description, image)
VALUES ('Skin Militaire', 'Skin', '2420', 'Nouveau skin de militaire pour les bombermans', 'img/Boutique/skin_militaire.png');

INSERT INTO Boutique (nom, type, prix, description, image)
VALUES ('Skin Monstre', 'Skin', '1850', 'Nouveau skin de monstre pour les bombermans', 'img/Boutique/skin_monstre.png');

INSERT INTO Boutique (nom, type, prix, description, image)
VALUES ('Skin Bombe', 'Skin', '820', 'Amélioration du skin pour les bombes', 'img/Boutique/skin_bombe.png');

-- MAP --
INSERT INTO Boutique (nom, type, prix, description, image)
VALUES ('Map aquatique', 'Map', '5000', 'Nouvelle map qui se joue sur l eau !', 'img/Boutique/map_aquatique.png');

INSERT INTO Boutique (nom, type, prix, description, image)
VALUES ('Map géante', 'Map', '8500', 'Nouvelle map pour 30 joueurs !', 'img/Boutique/map_geante.png');




