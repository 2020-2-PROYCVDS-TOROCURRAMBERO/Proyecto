package com.registerLab.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.shiro.SecurityUtils;

import com.google.inject.Injector;
import com.registerLab.ECILabException;
import com.registerLab.entities.Elemento;
import com.registerLab.servicios.ServiciosECILabImpl;

@SuppressWarnings("deprecation")
@ManagedBean(name="asBeanN")
@SessionScoped
public class AsociarBeanNext extends BaseBeanRegisterLab{
	private Injector injector;
	private ServiciosECILabImpl servicios;
	private int equipo;
	public AsociarBeanNext() {
		injector = super.getInjector();
		servicios = injector.getInstance(ServiciosECILabImpl.class);
	}
	public void setEquipo(int equipo) {
		this.equipo = equipo;
	}
	public int getEquipo() {
		return equipo;
	}
	public void asociarElemento(int elm) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			//System.out.println("id                 "+elm+" "+equipo);
			servicios.asociarElemento(elm, equipo ,servicios.getUsuario(SecurityUtils.getSubject().getPrincipal().toString()).getId());
			context.addMessage(null, new FacesMessage("Succesfull","se ha asociado correctamente"));
		} catch (ECILabException e) {
	        context.addMessage(null, new FacesMessage("Error",e.getMessage()));
		}
	}
}
