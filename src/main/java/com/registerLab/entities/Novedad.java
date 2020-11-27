package com.registerLab.entities;
import java.io.Serializable;
import java.sql.Date;
public class Novedad implements Serializable{
	private int id;
	private Date fechaNovedad;
	private String descripcion;
	private String justificacion;
	private int equipo;
	private int elemento;
	private String usuario;
	private int laboratorio;	
	public Novedad() {		
	}
	public void setId(int id) {
		this.id=id;
	}
	public void setFechaNovedad(Date fecha) {
		this.fechaNovedad = fecha;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}
	public int getId() {
		return id;
	}
	public Date getFechaNovedad() {
		return fechaNovedad;
	}
	public String getJustificacion() {
		return justificacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public int getLaboratorio() {
		return laboratorio;
	}
	public void setLaboratorio(int laboratorio) {
		this.laboratorio = laboratorio;
	}
	public int getEquipo() {
		return equipo;
	}
	public void setEquipo(int equipo) {
		this.equipo = equipo;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public int getElemento() {
		return elemento;
	}
	public void setElemento(int elemento) {
		this.elemento = elemento;
	}
}