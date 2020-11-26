package com.registerLab.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import com.google.inject.Injector;
import com.registerLab.ECILabException;
import com.registerLab.entities.Laboratorio;
import com.registerLab.servicios.ServiciosECILabImpl;

@ManagedBean(name="cerrarLabBean")
@SessionScoped
public class CerrarLaboratorioBean extends BaseBeanRegisterLab{
	private Injector injector;
	private ServiciosECILabImpl servicios;
	public CerrarLaboratorioBean() {
		injector = super.getInjector();
		servicios = injector.getInstance(ServiciosECILabImpl.class);
	}
	public List<Laboratorio> getLaboratorios(){
		return servicios.getLaboratorios();
	}
	public void cerrarLaboratorio(Laboratorio lab) {
		FacesContext context = FacesContext.getCurrentInstance(); 
		try {
			servicios.cerrarLaboratorio(lab.getId());
			context.addMessage(null, new FacesMessage("SuccessFull","El laboratorio "+String.valueOf(lab.getId()) +" "+String.valueOf(lab.getNombre()+" a sido cerrado")));
		}catch(ECILabException e) {
			context.addMessage(null, new FacesMessage("Error",e.getMessage()));
		}
	}
}
