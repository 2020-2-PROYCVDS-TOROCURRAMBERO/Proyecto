package com.registerLab.beans;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.shiro.SecurityUtils;

import com.google.inject.Injector;
import com.registerLab.ECILabException;
import com.registerLab.entities.Equipo;
import com.registerLab.entities.Laboratorio;
import com.registerLab.servicios.ServiciosECILabImpl;
@SuppressWarnings("deprecation")
@ManagedBean(name="vinBean")
@SessionScoped
public class AsociarEquipoBean extends BaseBeanRegisterLab{
	private int laboratorio;
	private Injector injector;
	private ServiciosECILabImpl servicios;
	public AsociarEquipoBean() {
		injector = super.getInjector();
		servicios = injector.getInstance(ServiciosECILabImpl.class);
	}
	public Laboratorio getLaboratorio() {
		//System.out.println(laboratorio);
		return servicios.getLaboratorio(laboratorio);
	}
	public void setLaboratorio(int lab) {
		this.laboratorio = lab;
	}
	public ArrayList<Equipo> getEquipos(){
		return servicios.getEquipos();
	}
	
	public ArrayList<Equipo> getEquiposinLab(){
		return servicios.getEquiposinLab();
	}
	
	public void asociarEquipo(Equipo e) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			//System.out.println(laboratorio);
			servicios.asociarEquipo(e.getId(), laboratorio);
			servicios.agregarNovedadSinElemento("Equipo añadido a laboratorio "+String.valueOf(laboratorio),"Necesidad de equipos en el laboratorio",e.getId(),servicios.getUsuario(SecurityUtils.getSubject().getPrincipal().toString()).getId());
			context.addMessage(null, new FacesMessage("Succesfull","Equipo asociado"));

		} catch (ECILabException e1) {
	        context.addMessage(null, new FacesMessage("Error",e1.getMessage()) );

		}
	}
	public ArrayList<Equipo> getAllEquipos(){
		return servicios.getAllEquipos();
	}

}
