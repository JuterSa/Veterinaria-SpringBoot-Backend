package com.softcaribbean.veterinariaXYZ.dao;

import com.softcaribbean.veterinariaXYZ.dto.Clientes;
import com.softcaribbean.veterinariaXYZ.dto.Usuario;
import com.softcaribbean.veterinariaXYZ.mapper.ClientesMapper;
import com.softcaribbean.veterinariaXYZ.mapper.UsuarioMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO{

    private static final String SELECT ="SELECT * FROM usuario WHERE dscorreo = ? AND dscontraseña =?";

    JdbcTemplate jdbcTemplate;


    public UsuarioDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);

    }
    @Override
    public Usuario getByCorreoAndPassword(String correo, String password){
        try{
            return jdbcTemplate.queryForObject(SELECT, new UsuarioMapper(),correo,password);
        }
        catch(EmptyResultDataAccessException ex){
            return null;
        }
    }
}
