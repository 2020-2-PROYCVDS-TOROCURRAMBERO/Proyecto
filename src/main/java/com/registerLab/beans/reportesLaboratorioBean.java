package com.registerLab.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;


@SuppressWarnings("deprecation")
@ManagedBean(name="repotsLabBean")
@RequestScoped


public class reportesLaboratorioBean {
	
	@ManagedProperty(value="#{param.laboratorio}")	
	private int laboratorio;
	
	
	public int getLaboratorio() {
		return this.laboratorio;
	}
	
	public void setLaboratorio(int laboratorio) {
		this.laboratorio = laboratorio;
	}

}
