package com.registerLab.DAO;

import com.registerLab.entities.Usuario;

public interface UsuarioDAO {

	Usuario getUsuario(String correo);

	void registrarUsuario(int carnet, String nombre, String apellido, String correo, String rol, String contra);

}
