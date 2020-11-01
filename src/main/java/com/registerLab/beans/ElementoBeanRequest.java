package com.registerLab.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="ElmBeanReq")
@RequestScoped
public class ElementoBeanRequest {
	@ManagedProperty(value="#{param.elemento}")
	private int elemento;
	@ManagedProperty(value="#{param.equipo}")
	private int equipo;
	public ElementoBeanRequest() {
		
	}
	/*
	 * @return devuelve la id del elemento asociado a la vista
	 */
	public int getElemento() {
		return elemento;
	}
	/*
	 * Actualiza la id del elemento asociado a la vista
	 */
	public void setElemento(int elemento) {
		this.elemento = elemento;
	}
	/*
	 * @param equipo - la id del equipo a asociar a la vista
	 */
	public void setEquipo(int equipo) {
		this.equipo = equipo;
	}
	/*
	 * @return devuelve la id deequipo asociado a la vista
	 */
	public int getEquipo() {
		return equipo;
	}
	
}