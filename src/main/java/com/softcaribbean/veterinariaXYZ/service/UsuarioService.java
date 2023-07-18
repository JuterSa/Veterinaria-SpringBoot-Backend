package com.softcaribbean.veterinariaXYZ.service;

import com.softcaribbean.veterinariaXYZ.dto.Usuario;

public interface UsuarioService {
    Usuario findUser(String correo, String password);
}
