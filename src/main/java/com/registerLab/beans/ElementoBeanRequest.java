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
	 * @return Retorna el ID del elemento asociado.
	 */
	public int getElemento() {
		return elemento;
	}
	/*
	 * Actualiza el ID del elemento asociado.
	 */
	public void setElemento(int elemento) {
		this.elemento = elemento;
	}
	/*
	 * @param equipo ID del equipo asociado.
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