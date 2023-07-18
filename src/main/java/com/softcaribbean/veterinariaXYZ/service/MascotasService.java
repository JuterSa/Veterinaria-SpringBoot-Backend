package com.softcaribbean.veterinariaXYZ.service;


import com.softcaribbean.veterinariaXYZ.dto.Mascotas;

import java.util.List;

public interface MascotasService {
    Mascotas save(Mascotas application);

    Mascotas update(Mascotas application);

    List<Mascotas> findAll();

    Mascotas findOne(int nmid);

    List<Mascotas> findAllByClientes(int nmbitacora);
    void delete(int nmid);
}
