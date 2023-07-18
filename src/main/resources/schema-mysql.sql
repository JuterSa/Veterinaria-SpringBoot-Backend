  CREATE TABLE IF NOT EXISTS `usuario` (
     `nmid` int(11) NOT NULL AUTO_INCREMENT,
     `dsnombrecompleto` varchar(150) NOT NULL,
     `dscorreo` varchar(50) NOT NULL,
     `dscontraseña` varchar(50) NOT NULL,
     `dsrol` varchar(30) NOT NULL,
    `dtfechacreacion` timestamp NOT NULL DEFAULT current_timestamp(),
    `dtfechamodificacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    PRIMARY KEY (`nmid`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    CREATE TABLE IF NOT EXISTS `clientes` (
      `nmid` int(11) NOT NULL AUTO_INCREMENT,
      `dstipodocumento` varchar(10) NOT NULL,
      `cdidentificacion` int(30) NOT NULL,
       `dsnombrecompleto` varchar(150) NOT NULL,
       `dsciudad` varchar(150) NOT NULL,
       `dsdireccion` varchar(150) NOT NULL,
       `dstelefono` bigint(11) NOT NULL,
      `dtfechacreacion` timestamp NOT NULL DEFAULT current_timestamp(),
      `dtfechamodificacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
      PRIMARY KEY (`nmid`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


   CREATE TABLE IF NOT EXISTS `mascota` (
      `nmid` int(11) NOT NULL AUTO_INCREMENT,
       `dsnombrecompleto` varchar(150) NOT NULL,
       `dsespecie` varchar(150) NOT NULL,
       `dsraza` varchar(150) NOT NULL,
       `dtfechanacimiento` timestamp NOT NULL DEFAULT current_timestamp(),
       `nmcliente` int(11) NOT NULL,
      `dtfechacreacion` timestamp NOT NULL DEFAULT current_timestamp(),
      `dtfechamodificacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
      PRIMARY KEY (`nmid`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

   ALTER TABLE mascota ADD CONSTRAINT FK_nmclienteid_mascota FOREIGN KEY (nmcliente) REFERENCES clientes(nmid) ON DELETE CASCADE ON UPDATE CASCADE;