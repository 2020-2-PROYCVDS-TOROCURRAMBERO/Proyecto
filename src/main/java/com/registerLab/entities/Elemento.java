package com.registerLab.entities;

import java.io.Serializable;
import java.sql.Date;

public class Elemento implements Serializable{
	private int id;
	private String categoria;
	private String fabricante;
	private String referencia;
	private Date fechaAdquisicion;
	private Date fechaInicioActividad;
	private Date fechaFinActividad;
	
	public Elemento() {	
	}
	/*
	 * Actualiza la id que le corresponde al elemento
	 * @param id- La id del elemento a actualizar
	 */
	public void setId(int id) {
		this.id=id;
	}
	/*
	 * Actualiza la categoria que corresponde al elmento
	 * @param categoria - El valor de la categoria a que corresponde este elemento
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	/*
	 * Actualiza la informacion sobre quien elaboro el elemento
	 * @param fabricante- corresponde a quien elaboro el equipo
	 */
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	
	
	public int getId() {
		return id;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public String getFabricante() {
		return fabricante;
	}
	
	public String getReferencia() {
		return referencia;
	}
	
	
	public Date getFechaInicioActividad() {
		return fechaInicioActividad;
	}
	
	public Date getFechaFinActividad() {
		return fechaFinActividad;
	}
	
	public Date getFechaAdquisicion() {
		return fechaAdquisicion;
	}
	
	public void setFechaInicioActividad(Date fechaInicioActividad) {
		this.fechaInicioActividad = fechaInicioActividad;
	}
	
	
	public void setFechaAdquisicion(Date fechaAdquisicion) {
		this.fechaAdquisicion = fechaAdquisicion;
	}
	
	public void setFechaFinActividad(Date fechaFinActividad) {
		this.fechaFinActividad = fechaFinActividad;
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof Elemento)) return false;
		return ((Elemento) o).getId()==id;
	}
	
}
