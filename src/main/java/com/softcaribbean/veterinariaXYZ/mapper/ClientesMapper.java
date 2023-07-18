package com.softcaribbean.veterinariaXYZ.mapper;

import com.softcaribbean.veterinariaXYZ.dto.Clientes;
import com.softcaribbean.veterinariaXYZ.util.UtilDate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientesMapper implements RowMapper<Clientes> {
    @Override
    public Clientes mapRow(ResultSet resultSet, int i) throws SQLException {
        Clientes entity = new Clientes();
        entity.setNmid( resultSet.getInt("nmid"));
        entity.setDstipodocumento(resultSet.getString("dstipodocumento"));
        entity.setCdidentificacion(resultSet.getInt("cdidentificacion"));
        entity.setDsnombrecompleto(resultSet.getString("dsnombrecompleto"));
        entity.setDsciudad(resultSet.getString("dsciudad"));
        entity.setDsdireccion(resultSet.getString("dsdireccion"));
        entity.setDstelefono(resultSet.getLong("dstelefono"));
        entity.setDtfechacreacion(UtilDate.getLocalDate(resultSet.getDate("dtfechacreacion")));
        return entity;
    }
}
