package com.registerLab.beans;

import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;

import org.apache.shiro.SecurityUtils;

import com.google.inject.Injector;

import com.registerLab.ECILabException;
import com.registerLab.entities.Equipo;
import com.registerLab.servicios.ServiciosECILabImpl;

@SuppressWarnings("deprecation")
@ManagedBean(name="equiBajaBean")
@RequestScoped
public class DarBajaEquipoBean extends BaseBeanRegisterLab{
	private Injector injector;
	private ServiciosECILabImpl servicios;
	@ManagedProperty(value="#{param.equipoBaja}")
	private int equipoBaja;
	
	public DarBajaEquipoBean() {
		injector = super.getInjector();
		servicios = injector.getInstance(ServiciosECILabImpl.class);
	}
	
	public void setEquipoBaja(int equipo) {
		this.equipoBaja = equipo;
	}
	
	public int getEquipoBaja() {
		return equipoBaja;
	}
	
	public void darBaja(Equipo eq) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			servicios.darBajaEquipo(eq.getId(),servicios.getUsuario(SecurityUtils.getSubject().getPrincipal().toString()).getId());
			context.addMessage(null, new FacesMessage("¡Listo!","El equipo ha sido dado de baja satisfactoriamente."));
		}catch(ECILabException e) {
			System.out.println(e.getMessage());
			context.addMessage(null, new FacesMessage("Error",e.getMessage()));

			
		}
	}
	public Equipo getEquipo(int eq) {
		return servicios.getEquipo(eq);
	}
	
	
}
