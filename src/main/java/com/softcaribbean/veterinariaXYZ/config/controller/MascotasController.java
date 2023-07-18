package com.softcaribbean.veterinariaXYZ.config.controller;

import com.softcaribbean.veterinariaXYZ.config.controller.errors.ApplicationCustomException;
import com.softcaribbean.veterinariaXYZ.dto.Clientes;
import com.softcaribbean.veterinariaXYZ.dto.Mascotas;
import com.softcaribbean.veterinariaXYZ.service.ClientesService;
import com.softcaribbean.veterinariaXYZ.service.MascotasService;
import com.softcaribbean.veterinariaXYZ.util.MessagesConstants;
import com.softcaribbean.veterinariaXYZ.util.ResponseMessage;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MascotasController {
    private Logger log = LoggerFactory.getLogger(MascotasController.class);

    private static final String ENITY_NAME = "Mascotas";

    private final MascotasService mascotasService;

    public MascotasController(MascotasService mascotasService){
        this.mascotasService = mascotasService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/mascotas")
    public ResponseEntity<ResponseMessage<List<Mascotas>>> getAllMascotas(){
        log.debug("REST para obtener todas las mascotas");

        return ResponseEntity.ok( new ResponseMessage<>(0,null, mascotasService.findAll()));
    }
    @PutMapping("/mascotas")
    public ResponseEntity<ResponseMessage<Mascotas>> updateMascota(@Valid @RequestBody Mascotas mascota) throws ApplicationCustomException {
        log.debug("REST para actualizar una mascota : {} ", mascota);
        if(mascota.getNmid() == 0){
            throw new ApplicationCustomException(MessagesConstants.ENTITY_ALREADY_EXISTS_CODE,String.format(MessagesConstants.ENTITY_NOT_EXISTS, ENITY_NAME));
        }
        Mascotas result = mascotasService.update(mascota);
        return ResponseEntity.ok( new ResponseMessage<>(0, null, result));
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/mascotas/{nmcliente}")
    public ResponseEntity<ResponseMessage<List<Mascotas>>> getMascotasByIdCliente(@PathVariable int nmcliente){
        log.debug("REST para obtener mascotas por el id del cliente", nmcliente);
        return ResponseEntity.ok( new ResponseMessage<>(0,"No se encontraron mascotas asociadas a este cliente o no hay informacion registrada", mascotasService.findAllByClientes(nmcliente)));
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/mascotas")
    public ResponseEntity<ResponseMessage<Mascotas>> createMascotas(@Valid @RequestBody Mascotas mascota) throws ApplicationCustomException {
        log.debug("REST request to save raf_bitacora : {}", mascota);
        Mascotas mascotaFind = mascotasService.findOne(mascota.getNmid());
        if(mascotaFind != null){
            throw new ApplicationCustomException(MessagesConstants.ENTITY_ALREADY_EXISTS_CODE, String.format(MessagesConstants.ENTITY_ALREADY_EXISTS, ENITY_NAME));
        }
        Mascotas result = mascotasService.save(mascota);
        return ResponseEntity.ok( new ResponseMessage<>(0,null, result));
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/cliente-mascota/{nmid}")
    public ResponseEntity<ResponseMessage<Mascotas>> delete(@PathVariable int nmid) {
        log.debug("REST request to delete mascotas asociadas al cliente : {}", nmid);
        mascotasService.delete(nmid);
        return ResponseEntity.ok(new ResponseMessage<>(0, "Mascota eliminada con exito", null) );
    }
}
