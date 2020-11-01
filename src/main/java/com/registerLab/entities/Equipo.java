package com.registerLab.entities;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
public class Equipo implements Serializable{
	private int id;
	private Date fechaInicioActividad;
	private Date fechaFinActividad;
	private Date fechaAdquisicion;
	private int laboratorio;
	private ArrayList<Elemento> elementos;	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}	
	public Date getFechaInicioActividad() {
		return fechaInicioActividad;
	}	
	public void setFechaInicioActividad(Date fechaInicioActividad) {
		this.fechaInicioActividad = fechaInicioActividad;
	}	
	public Date getFechaFinActividad() {
		return fechaFinActividad;
	}	
	public Date getFechaAdquisicion() {
		return fechaAdquisicion;
	}	
	public void setFechaAdquisicion(Date fechaAdquisicion) {
		this.fechaAdquisicion = fechaAdquisicion;
	}	
	public void setElementos(ArrayList<Elemento> elementos){
		this.elementos = elementos;
	}	
	public ArrayList<Elemento> getElementos(){
		return elementos;
	}
	public int getLaboratorio() {
		return laboratorio;
	}
	public void setLaboratorio(int laboratorio) {
		this.laboratorio = laboratorio;
	}	
}