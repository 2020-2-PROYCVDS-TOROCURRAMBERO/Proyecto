package com.registerLab.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.shiro.SecurityUtils;

import com.google.inject.Injector;
import com.registerLab.ECILabException;
import com.registerLab.entities.Elemento;
import com.registerLab.entities.Equipo;
import com.registerLab.servicios.ServiciosECILabImpl;


@ManagedBean(name="elemBajaBean")
@SessionScoped
public class DarBajaElementoBean extends BaseBeanRegisterLab{
	private Injector injector;
	private ServiciosECILabImpl servicios;
	
	public DarBajaElementoBean() {
		injector = super.getInjector();
		servicios = injector.getInstance(ServiciosECILabImpl.class);
	}
	
	public void darBaja(Elemento elm) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			servicios.darBajaElemento(elm.getId(),servicios.getUsuario(SecurityUtils.getSubject().getPrincipal().toString()).getId());
			context.addMessage(null, new FacesMessage("Succesfull","El elemento ha sido dado de baja."));
		}catch(ECILabException e) {
			System.out.println(e.getMessage());
			context.addMessage(null, new FacesMessage("error",e.getMessage()));

			
		}
		
	}
	
	public void darBajaConEquipoAsociado(Elemento elm,Equipo equ) {
		FacesContext context = FacesContext.getCurrentInstance();
		servicios.darBajaConEquipoAsociado(elm,equ);
		context.addMessage(null, new FacesMessage("Succesfull","El elemento ha sido dado de baja."));
		
	}
	
	public void desvincularElemento(Elemento elm,Equipo eq){
		FacesContext context = FacesContext.getCurrentInstance();
		servicios.desvincularElemento(elm,eq);
	}
	
}
