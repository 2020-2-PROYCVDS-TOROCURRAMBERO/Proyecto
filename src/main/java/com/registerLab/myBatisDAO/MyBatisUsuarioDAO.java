package com.registerLab.myBatisDAO;
import com.google.inject.Inject;
import com.registerLab.DAO.UsuarioDAO;
import com.registerLab.entities.Usuario;
import com.registerLab.mappers.UsuarioMapper;
public class MyBatisUsuarioDAO implements UsuarioDAO{
	@Inject
	private UsuarioMapper mapper;	
	@Override
	public Usuario getUsuario(String correo) {
		return mapper.getUsuario(correo);
	}
	@Override
	public void registrarUsuario(int carnet, String nombre, String apellido, String correo, String rol, String contra) {
		mapper.registrarUsuario(carnet,nombre,apellido,correo,rol,contra);		
	}
}