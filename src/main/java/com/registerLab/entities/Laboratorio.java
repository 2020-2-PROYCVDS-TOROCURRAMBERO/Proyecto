package com.registerLab.entities;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
public class Laboratorio implements Serializable{
	private int id;
	private String nombre;
	private int capacidad;
	private Date fechaCierre;
	private Date fechaApertura;
	private ArrayList<Equipo> equipos;	
	public void setId(int id) {
		this.id = id;
	}	
	public int getId() {
		return id;
	}	
	public int getCapacidad() {
		return capacidad;
	}	
	public String getNombre() {
		return nombre;
	}	
	public Date getFechaCierre() {
		return fechaCierre;
	}	
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}	
	public void setEquipos(ArrayList<Equipo> equipos){
		this.equipos = equipos;
	}
	public ArrayList<Equipo> getEquipos(){
		return equipos;
	}
	public Date getFechaApertura() {
		return fechaApertura;
	}
	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}	
}