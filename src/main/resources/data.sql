
insert into clientes (nmid, dstipodocumento, cdidentificacion, dsnombrecompleto, dsciudad, dsdireccion, dstelefono, dtfechacreacion, dtfechamodificacion) values
(1,'CC',1003097593,'Tutankamon Perez','Moñitos','KR8 CL 22B',3125069656,now(),now()),
(2,'TI',1003097593,'Epicuro Gutierrez','Moñitos','KR8 CL 22B',3125069645,now(),now());

insert into usuario (nmid,dsnombrecompleto, dscorreo, dscontraseña, dsrol, dtfechacreacion, dtfechamodificacion) values
 (1,'Saul Julio Teran','Sajute99@outlook.com','Viviana092003','ADMIN',now(),now()),
 (2,'Saulito Julio Teran','Sajute99@outlook.com','Viviana2003','ADMIN',now(),now());

 INSERT INTO mascota (nmid, dsnombrecompleto, dsespecie, dsraza, dtfechanacimiento, nmcliente, dtfechacreacion, dtfechamodificacion) VALUES
 (1,'Kryspy','Labrador','Pekine',now(),1,now(),now()),
 (2,'Crispeta','Dobergman','Pastor Aleman',now(),2,now(),now()),
 (3,'Chuky','Callejero','Dogberman',now(),1,now(),now());