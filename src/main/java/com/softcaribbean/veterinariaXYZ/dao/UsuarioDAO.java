package com.softcaribbean.veterinariaXYZ.dao;

import com.softcaribbean.veterinariaXYZ.dto.Clientes;
import com.softcaribbean.veterinariaXYZ.dto.Usuario;

import java.util.List;

public interface UsuarioDAO {
    //List<Usuario> getUser();
    Usuario getByCorreoAndPassword(String correo, String password);
}
