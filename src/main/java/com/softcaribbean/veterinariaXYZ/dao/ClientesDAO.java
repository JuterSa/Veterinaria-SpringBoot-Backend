package com.softcaribbean.veterinariaXYZ.dao;

import com.softcaribbean.veterinariaXYZ.dto.Clientes;

import java.util.List;

public interface ClientesDAO {
    List<Clientes> getAll();

    Clientes getById(int nmid);

    Clientes insert(Clientes entity);

    Clientes update(Clientes entity);
    Boolean delete(int nmid);
}
