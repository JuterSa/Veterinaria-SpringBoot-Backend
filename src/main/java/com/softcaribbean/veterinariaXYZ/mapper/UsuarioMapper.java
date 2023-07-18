package com.softcaribbean.veterinariaXYZ.mapper;

import com.softcaribbean.veterinariaXYZ.dto.Usuario;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioMapper implements RowMapper<Usuario> {
    @Override
    public Usuario mapRow(ResultSet resultSet, int i) throws SQLException {
        Usuario entity = new Usuario();
        entity.setNmid( resultSet.getInt("nmid"));
        entity.setDsnombrecompleto(resultSet.getString("dsnombrecompleto"));
        entity.setDscorreo(resultSet.getString("dscorreo"));
        entity.setDscontraseña(resultSet.getString("dscontraseña"));
        entity.setDsrol(resultSet.getString("dsrol"));
        return entity;
    }
}
