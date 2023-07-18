package com.softcaribbean.veterinariaXYZ.service;

import com.softcaribbean.veterinariaXYZ.dto.Clientes;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClientesService {   //Solo pueden implementar mas no uso --- Revisando
    Clientes save(Clientes application); //1

    Clientes update(Clientes application);

    List<Clientes> findAll();

    Clientes findOne(int nmid);

    void delete(int nmid);
}
