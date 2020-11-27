package com.registerLab.mappers;
import org.apache.ibatis.annotations.Param;
import com.registerLab.entities.Usuario;
public interface UsuarioMapper {
	public Usuario getUsuario(@Param("Correo") String correo);
	public void registrarUsuario(@Param("carnet")int carnet, @Param("nombre")String nombre,@Param("apellido") String apellido, @Param("correo")String correo,@Param("rol") String rol, @Param("contra")String contra);
}