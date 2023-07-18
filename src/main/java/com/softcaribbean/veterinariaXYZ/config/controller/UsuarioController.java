package com.softcaribbean.veterinariaXYZ.config.controller;


import com.softcaribbean.veterinariaXYZ.config.controller.errors.ApplicationCustomException;
import com.softcaribbean.veterinariaXYZ.dto.Clientes;
import com.softcaribbean.veterinariaXYZ.dto.Usuario;
import com.softcaribbean.veterinariaXYZ.service.ClientesService;
import com.softcaribbean.veterinariaXYZ.service.UsuarioService;
import com.softcaribbean.veterinariaXYZ.util.MessagesConstants;
import com.softcaribbean.veterinariaXYZ.util.ResponseMessage;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    private final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    private static final String ENITY_NAME = "usuario";


    private final UsuarioService usuarioService;
    //private final Servicios clienteServices;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public ResponseEntity<ResponseMessage<Usuario>> loginUsuario(@Valid @RequestBody Usuario usuario) throws ApplicationCustomException {
        log.debug("REST login usuario : {}", usuario);
        String dscorreo = usuario.getDscorreo();
        String dscontraseña = usuario.getDscontraseña();
        Usuario find = usuarioService.findUser(dscorreo,dscontraseña);
        if(find==null){
            //ResponseEntity.ok(new ResponseMessage<>(0,"Acceso denegado", "Usuario o contraseña invalido"));
           throw new ApplicationCustomException(MessagesConstants.ENTITY_ALREADY_EXISTS_CODE, String.format("Acceso denegado", ENITY_NAME));
        }
        Usuario result = usuarioService.findUser(dscorreo,dscontraseña); //
        return ResponseEntity.ok( new ResponseMessage<>(0,"Acceso concedido", result));

    }
    @PostMapping("/login/{dscorreo}/{dscontraseña}")
    public ResponseEntity<ResponseMessage<Usuario>> loginUs(@PathVariable String dscorreo, @PathVariable String dscontraseña ) throws ApplicationCustomException {
        log.debug("REST login usuario : {}", dscorreo + dscontraseña);
        //System.out.print("Muestra de que si esta recibiendo:" + dscontraseña + " " + dscorreo);
        Usuario find = usuarioService.findUser(dscorreo,dscontraseña);
        if(find==null){
            //ResponseEntity.ok(new ResponseMessage<>(0,"Acceso denegado", "Usuario o contraseña invalido"));
            throw new ApplicationCustomException(MessagesConstants.ENTITY_ALREADY_EXISTS_CODE, String.format("Acceso denegado", ENITY_NAME));
        }
        Usuario result = usuarioService.findUser(dscorreo,dscontraseña); //
        return ResponseEntity.ok( new ResponseMessage<>(0,"Acceso concedido", result));

    }
}
