package com.softcaribbean.veterinariaXYZ.dao;

import com.softcaribbean.veterinariaXYZ.dto.Clientes;
import com.softcaribbean.veterinariaXYZ.mapper.ClientesMapper;
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
import java.util.Objects;

@Repository
public class ClientesDAOImpl implements ClientesDAO {
    private static final String INSERT ="INSERT INTO clientes (dstipodocumento, cdidentificacion, dsnombrecompleto, dsciudad, dsdireccion, dstelefono, dtfechacreacion) values (?,?,?,?,?,?, NOW())";
    private static final String UPDATE ="UPDATE clientes SET dstipodocumento = ?, cdidentificacion = ?,dsnombrecompleto = ?, dsciudad =?, dsdireccion = ?, dstelefono= ? WHERE nmid = ?";
    private static final String SELECT ="SELECT * FROM clientes";
    private static final String SELECTBYID = SELECT + " WHERE nmid = ?";

    private static final String DELETE = "DELETE FROM clientes WHERE nmid = ?";
    JdbcTemplate jdbcTemplate;


    public ClientesDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);

    }

    @Override
    public List<Clientes> getAll() { return jdbcTemplate.query(SELECT, new ClientesMapper());}

    @Override
    public Clientes getById(int nmid){
        try{
            return jdbcTemplate.queryForObject(SELECTBYID, new ClientesMapper(),nmid);
        }
        catch(EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public Clientes insert(Clientes entity){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT, new String[]{"nmdato"});
            DaoUtil.setPrepareStatement(ps, new Object[]{
                    entity.getDstipodocumento(),
                    entity.getCdidentificacion(),
                    entity.getDsnombrecompleto(),
                    entity.getDsciudad(),
                    entity.getDsdireccion(),
                    entity.getDstelefono()
            });

            return ps;
        }, keyHolder);
        entity.setNmid(Objects.requireNonNull(keyHolder.getKey()).intValue());
        return entity;
    }
    @Override
    public Boolean delete(int nmid) {
        return jdbcTemplate.update(DELETE,
                nmid
        )>0;
    }

    @Override
    public Clientes update(Clientes entity){
        jdbcTemplate.update(UPDATE,
                entity.getDstipodocumento(),
                entity.getCdidentificacion(),
                entity.getDsnombrecompleto(),
                entity.getDsciudad(),
                entity.getDsdireccion(),
                entity.getDstelefono(),
                entity.getNmid());
        return entity;
    }



}
