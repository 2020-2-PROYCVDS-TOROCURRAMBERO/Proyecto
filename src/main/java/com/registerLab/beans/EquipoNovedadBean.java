package com.registerLab.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="eqnovBean")
@RequestScoped
public class EquipoNovedadBean{
	@ManagedProperty(value="#{param.equipo}")
	private int equipo;
	public EquipoNovedadBean() {	
	}
	/*
	 * @param equipo ID del nuevo equipo por asociar.  
	 */
	public void setEquipo(int equipo) {
		this.equipo = equipo;
	}
	/*
	 * @return Retorna el ID del equipo asociado.
	 */
	public int getEquipo() {
		return equipo;
	}
}