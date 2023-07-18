package com.softcaribbean.veterinariaXYZ.service;

import com.softcaribbean.veterinariaXYZ.dao.MascotasDAO;
import com.softcaribbean.veterinariaXYZ.dto.Mascotas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MascotasServiceImpl implements  MascotasService{
    private  final Logger log = LoggerFactory.getLogger(ClientesServiceImpl.class);
    private final MascotasDAO mascotaDAO;

    public MascotasServiceImpl(MascotasDAO mascotaDAO) { this.mascotaDAO = mascotaDAO; }

    @Override
    public Mascotas save(Mascotas mascotas){
        log.debug("Peticion para agregar una mascota: {}", mascotas);
        return mascotaDAO.insert(mascotas);
    }

    @Override
    public Mascotas update(Mascotas mascotas){
        log.debug("Peticion para actualizar una mascotas: {}", mascotas);
        return mascotaDAO.update(mascotas);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Mascotas> findAll() {
        log.debug("Peticion para obtener todas las mascotas");
        return mascotaDAO.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Mascotas findOne(int nmid) {
        log.debug("Peticion para encontrar una mascota: {}", nmid);
        return mascotaDAO.getById(nmid);
    }
    @Override
    @Transactional(readOnly = true)
    public List<Mascotas> findAllByClientes(int nmcliente){
        log.debug("Peticion para obtener todas las mascotas de un cliente");
        return mascotaDAO.getAllIdCliente(nmcliente);
    }
    @Override
    public void delete(int nmid) {
        log.debug("Request to delete mascotas : {}", nmid);
        mascotaDAO.delete(nmid);
    }
}
