package com.softcaribbean.veterinariaXYZ.dao;

import com.softcaribbean.veterinariaXYZ.dto.Mascotas;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MascotasDAO {
    List<Mascotas> getAll();

    Mascotas getById(int nmid);

    Mascotas insert(Mascotas entity);

    Mascotas update(Mascotas entity);

    @Transactional(readOnly = true)
    List<Mascotas> getAllIdCliente(int nmbitacora);

    Boolean delete(int nmid);
}
