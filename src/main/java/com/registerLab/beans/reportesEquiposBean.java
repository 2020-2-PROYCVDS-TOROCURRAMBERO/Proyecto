package com.registerLab.beans;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@SuppressWarnings("deprecation")
@ManagedBean(name="repsEquipBean")
@RequestScoped

public class reportesEquiposBean {
	
	@ManagedProperty(value="#{param.equipo}")	
	private int equipo;
	
	
	public void setEquipo(int equipo) {
		this.equipo = equipo;
	}
	
	public int getEquipo() {
		return this.equipo;
	}
	

	
	

}
