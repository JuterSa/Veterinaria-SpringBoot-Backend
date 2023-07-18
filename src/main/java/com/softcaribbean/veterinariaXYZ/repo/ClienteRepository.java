package com.softcaribbean.veterinariaXYZ.repo;

import com.softcaribbean.veterinariaXYZ.dto.Clientes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository //Anotacion que le dice a Spring que esta clase es un repositorio
public interface ClienteRepository extends CrudRepository<Clientes, Integer> {
}
