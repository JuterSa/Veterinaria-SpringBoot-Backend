package com.softcaribbean.veterinariaXYZ.service;

import com.softcaribbean.veterinariaXYZ.dao.ClientesDAO;
import com.softcaribbean.veterinariaXYZ.dao.UsuarioDAO;
import com.softcaribbean.veterinariaXYZ.dto.Clientes;
import com.softcaribbean.veterinariaXYZ.dto.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioServiceImpl implements  UsuarioService{
    private final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    private final UsuarioDAO usuarioDao;

    public UsuarioServiceImpl(UsuarioDAO usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    @Override
    public Usuario findUser(String correo, String password) {
        log.debug("Request to get a ususario : {}", correo + password);
        return usuarioDao.getByCorreoAndPassword(correo, password);
    }
}
