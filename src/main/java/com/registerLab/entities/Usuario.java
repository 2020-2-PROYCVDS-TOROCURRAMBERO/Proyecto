package com.registerLab.entities;
import java.io.Serializable;
public class Usuario implements Serializable{
	private int id;
	private String nombre;
	private String apellido;
	private String correo;
	private String rol;
	private String contra;
	public Usuario(){		
	}
	public void setId(int id) {
		this.id=id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public void setContra(String contra) {
		this.contra = contra;
	}
	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public String getRol() {
		return rol;
	}
	public String getContra() {
		return contra;
	}
	public String getCorreo() {
		return correo;
	}
}