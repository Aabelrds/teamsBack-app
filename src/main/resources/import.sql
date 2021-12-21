INSERT INTO teams (city,country,himno,name,ucl_trophies,create_at,image) VALUES('Barcelona','España','Cant del Barça','F.C.BARCELONA',5,'2021-12-20','https://pbs.twimg.com/profile_images/1409740934217142273/-RmOAoJG.jpg')
INSERT INTO teams (city,country,himno,name,ucl_trophies,create_at,image) VALUES('Madrid','España','Hala Madrid','Real Madrid',13,'2021-12-20','https://pbs.twimg.com/profile_images/1284197749030887429/7n6w-Urk_400x400.jpg');
INSERT INTO teams (city,country,himno,name,ucl_trophies,create_at,image) VALUES('Madrid','España','Rey de la furia española','Club Atlético de Madrid',0,'2021-12-20','https://pbs.twimg.com/profile_images/1275808355215388672/UZhee-Kv_400x400.jpg');


INSERT INTO players (teams_id, country, city, name, sur_name, image) VALUES (1,'España','Barcelona','Gerard','Pique','https://as.com/futbol/imagenes/2021/02/04/primera/1612467070_214885_1612470117_noticia_normal_recorte1.jpg');
INSERT INTO players (teams_id, country, city, name, sur_name, image) VALUES (1,'España','Sevilla','Pablo','Gaviria','https://assets.laliga.com/squad/2021/t178/p500046/2048x2048/p500046_t178_2021_1_002_000.jpg');
INSERT INTO players (teams_id, country, city, name, sur_name, image) VALUES (1,'Alemania',' Mönchengladbach','Marc-André','Ter Stegen','https://s.hs-data.com/bilder/spieler/gross/150720.jpg');
INSERT INTO players (teams_id, country, city, name, sur_name, image) VALUES (1,'Brasil','Juazeiro','Daniel','Alves','https://s.hs-data.com/bilder/spieler/gross/12541.jpg');
INSERT INTO players (teams_id, country, city, name, sur_name, image) VALUES (1,'Spain','Tegueste','Pedro','González','https://as01.epimg.net/futbol/imagenes/2021/11/19/primera/1637349457_487727_1637421475_noticia_normal_recorte1.jpg');
INSERT INTO players (teams_id, country, city, name, sur_name, image) VALUES (1,'Guinea-Bisáu','Bisáu','Anssumane','Fati','https://e00-marca.uecdn.es/assets/multimedia/imagenes/2021/09/16/16317906830360.jpg');
INSERT INTO players (teams_id, country, city, name, sur_name, image) VALUES (1,'Paises Bajos','Arkel','Frankie','De Jong','https://estaticos-cdn.sport.es/clip/cd3e3aad-a62e-4ae5-842b-b824146bac3d_alta-libre-aspect-ratio_default_0.jpg');
INSERT INTO players (teams_id, country, city, name, sur_name, image) VALUES (1,'Uruguay','Rivera','Ronald','Araújo','https://www.fichajes.net/files/ronald-araujo-fc-barcelona.jpg');

INSERT INTO `users` (username, password, enabled) VALUES('jose','$2a$10$rKckPUo7D8QuSfVmw7X/SOUGjo3uTrAXiUb1hJZN/7XgQW7.YgJIO',1);
INSERT INTO `users` (username, password, enabled) VALUES('admin','$2a$10$XA2YENNAbheDlw9uKN1P4uiwJ6jTrvM6p0cxQVAOIla.g1bdkiweK',1);

INSERT INTO `roles` (name) VALUES('ROLE_ADMIN');
INSERT INTO `roles` (name) VALUES('ROLE_USER');

INSERT INTO `users_roles` (user_id, role_id) VALUES(1,2);
INSERT INTO `users_roles` (user_id, role_id) VALUES(2,1);