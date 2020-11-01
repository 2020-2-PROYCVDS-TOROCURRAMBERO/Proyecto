package com.registerLab.beans;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@SuppressWarnings("deprecation")
@ManagedBean(name="repsElmBean")
@RequestScoped

public class reportesElementoBean {
	
	@ManagedProperty(value="#{param.elemento}")	
	private int elemento;
	
	
	public void setElemento(int elemento) {
		this.elemento = elemento;
	}
	
	public int getElemento() {
		return this.elemento;
	}
	

	
	

}
