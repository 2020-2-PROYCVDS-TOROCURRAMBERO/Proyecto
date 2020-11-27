package com.registerLab.beans;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
@SuppressWarnings("deprecation")
@ManagedBean(name="labBean")
@RequestScoped
public class AsociarEquipoBeanRequest {
	@ManagedProperty(value="#{param.laboratorio}")
	private int laboratorio;
	public AsociarEquipoBeanRequest() {
	}
	public void setLaboratorio(int lab) {
		this.laboratorio = lab;
	}
	public int getLaboratorio() {
		return this.laboratorio;
	}
}