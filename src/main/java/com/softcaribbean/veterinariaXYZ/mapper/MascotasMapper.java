package com.softcaribbean.veterinariaXYZ.mapper;

import com.softcaribbean.veterinariaXYZ.dto.Clientes;
import com.softcaribbean.veterinariaXYZ.dto.Mascotas;
import com.softcaribbean.veterinariaXYZ.util.UtilDate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MascotasMapper implements RowMapper<Mascotas> {
    @Override
    public Mascotas mapRow(ResultSet resultSet, int i) throws SQLException {
        Mascotas entity = new Mascotas();
        entity.setNmid( resultSet.getInt("nmid"));
        entity.setDsnombrecompleto(resultSet.getString("dsnombrecompleto"));
        entity.setDsespecie(resultSet.getString("dsespecie"));
        entity.setDsraza(resultSet.getString("dsraza"));
        entity.setDtfechanacimiento(UtilDate.getLocalDate(resultSet.getDate("dtfechanacimiento")));
        entity.setNmcliente(resultSet.getInt("nmcliente"));
        return entity;
    }

}
