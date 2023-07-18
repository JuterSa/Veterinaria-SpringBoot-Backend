package com.softcaribbean.veterinariaXYZ.config.controller;


import com.softcaribbean.veterinariaXYZ.config.controller.errors.ApplicationCustomException;
import com.softcaribbean.veterinariaXYZ.dto.Clientes;
import com.softcaribbean.veterinariaXYZ.service.ClientesService;
import com.softcaribbean.veterinariaXYZ.service.Servicios;
import com.softcaribbean.veterinariaXYZ.util.MessagesConstants;
import com.softcaribbean.veterinariaXYZ.util.ResponseMessage;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientesController {
    private final Logger log = LoggerFactory.getLogger(ClientesController.class);

    private static final String ENITY_NAME = "clientes";


    private final ClientesService clientesService;
    //private final Servicios clienteServices;

    public ClientesController(ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/clientes")
    public ResponseEntity<ResponseMessage<List<Clientes>>> getAllClientes(){
        log.debug("REST para obtener todos los clientes");

        return ResponseEntity.ok( new ResponseMessage<>(0,null, clientesService.findAll()));
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/clientes")
    public ResponseEntity<ResponseMessage<Clientes>> createClientes(@Valid @RequestBody Clientes clientes) throws ApplicationCustomException {
        log.debug("REST request to save clientes : {}", clientes);
        Clientes clientesFind = clientesService.findOne(clientes.getNmid());
        if(clientesFind != null){
            throw new ApplicationCustomException(MessagesConstants.ENTITY_ALREADY_EXISTS_CODE, String.format(MessagesConstants.ENTITY_ALREADY_EXISTS, ENITY_NAME));
       }
        Clientes result = clientesService.save(clientes); //2
        System.out.println(result);
        return ResponseEntity.ok( new ResponseMessage<>(0,"Cliente agregado con exito", result));

    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/clientes")
    public ResponseEntity<ResponseMessage<Clientes>> updateClientes(@Valid @RequestBody Clientes clientes) throws ApplicationCustomException {
        log.debug("REST request to save clientes : {}", clientes);
        Clientes clientesFind = clientesService.findOne(clientes.getNmid());
        if(clientesFind.getNmid() == 0){
            throw new ApplicationCustomException(MessagesConstants.ENTITY_ALREADY_EXISTS_CODE, String.format(MessagesConstants.ENTITY_ALREADY_EXISTS, ENITY_NAME));
        }
        Clientes result = clientesService.update(clientes); //2
        System.out.println(result);
        System.out.println("Estado: " + ResponseEntity.ok().toString());
        return ResponseEntity.ok( new ResponseMessage<>(0,"Cliente actualizado con exito", result));

    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/clientes/{nmid}")
    public ResponseEntity<ResponseMessage<Clientes>> delete(@PathVariable int nmid) {
        log.debug("REST request to delete clientes : {}", nmid);
        clientesService.delete(nmid);
        return ResponseEntity.ok(new ResponseMessage<>(0, "Cliente eliminado con exito", null) );
    }

    /*@GetMapping("/enterprises") //Ver json de todas las empresas
    public List<Clientes> verEmpresas(){
        return empresaService.getAllEmpresas();
    }

    @PostMapping("/enterprises") //Guardar el json del body como una nueva empresa o registro en nuestra bd
    public Empresa guardarEmpresa(@RequestBody Empresa emp){
        return this.empresaService.saveOrUpdateEmpresa(emp);
    }*/

}
