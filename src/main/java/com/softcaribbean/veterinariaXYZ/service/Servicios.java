package com.softcaribbean.veterinariaXYZ.service;

import com.softcaribbean.veterinariaXYZ.dto.Clientes;
import com.softcaribbean.veterinariaXYZ.repo.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Servicios {
    @Autowired
    ClienteRepository repocliente;

    public Clientes saveOrUpdateEmpresa(Clientes empresa){
        Clientes emp=repocliente.save(empresa);
        return emp;
    }
    public List<Clientes> getAllClientes(){
        List<Clientes> empresaList = new ArrayList<>();
        repocliente.findAll().forEach(empresa -> empresaList.add(empresa));  //Recorremos el iterable que regresa el metodo findAll del Jpa y lo guardamos en la lista creada
        return empresaList;
    }
}
