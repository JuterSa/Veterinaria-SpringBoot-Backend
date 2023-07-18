package com.softcaribbean.veterinariaXYZ.dao;

import com.softcaribbean.veterinariaXYZ.dto.Mascotas;
import com.softcaribbean.veterinariaXYZ.mapper.MascotasMapper;
import com.softcaribbean.veterinariaXYZ.util.DaoUtil;
import org.springframework.cache.CacheManager;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class MascotasDAOImpl implements MascotasDAO{
    private static final String INSERT ="INSERT INTO mascota (dsnombrecompleto, dsespecie, dsraza, dtfechanacimiento, nmcliente,dtfechacreacion) values (?,?,?,?,?,NOW())";
    private static final String UPDATE ="UPDATE mascota SET dsnombrecompleto = ?, dsespecie = ?,dsraza = ?, dtfechanacimiento =? WHERE nmid = ?";
    private static final String SELECT ="SELECT * FROM mascota";
    private static final String SELECTBYID = SELECT + " WHERE nmid = ?";
    private static final String SELECTBYIDCLIENTE = "SELECT * FROM mascota WHERE nmcliente = ?";

    private static final String DELETE = "DELETE FROM mascota WHERE nmid = ?";
    JdbcTemplate jdbcTemplate;
    private CacheManager cacheManager;

    public MascotasDAOImpl(DataSource dataSource) {jdbcTemplate = new JdbcTemplate(dataSource);}

    @Override
    public List<Mascotas> getAll() { return jdbcTemplate.query(SELECT, new MascotasMapper());}

    @Override
    public Mascotas getById(int nmid){
        try{
            return jdbcTemplate.queryForObject(SELECTBYID, new MascotasMapper(),nmid);
        }
        catch(EmptyResultDataAccessException ex){
            return null;
        }
    }
    @Override
    public List<Mascotas> getAllIdCliente(int nmcliente){
        try{
            return jdbcTemplate.query(SELECTBYIDCLIENTE, new MascotasMapper(), nmcliente);
        }
        catch(EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public Mascotas insert(Mascotas entity){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT, new String[]{"nmdato"});
            DaoUtil.setPrepareStatement(ps, new Object[]{
                    entity.getDsnombrecompleto(),
                    entity.getDsespecie(),
                    entity.getDsraza(),
                    entity.getDtfechanacimiento(),
                    entity.getNmcliente()
            });
            return ps;
        }, keyHolder);
        entity.setNmid(keyHolder.getKey().intValue());
        return entity;
    }

    @Override
    public Mascotas update(Mascotas entity){
        jdbcTemplate.update(UPDATE,
                entity.getDsnombrecompleto(),
                entity.getDsespecie(),
                entity.getDsraza(),
                entity.getDtfechanacimiento(),
                entity.getNmid());
        return entity;
    }
    @Override
    public Boolean delete(int nmid) {
        return jdbcTemplate.update(DELETE,
                nmid
        )>0;
    }

}
