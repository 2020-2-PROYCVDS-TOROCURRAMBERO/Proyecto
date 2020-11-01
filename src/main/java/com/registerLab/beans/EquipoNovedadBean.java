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
	 * @param equipo - la id del nuevo equipo a asociar a la vista
	 */
	public void setEquipo(int equipo) {
		this.equipo = equipo;
	}
	/*
	 * @return devuelve la id del equipo asociado a la vista
	 */
	public int getEquipo() {
		return equipo;
	}

}
