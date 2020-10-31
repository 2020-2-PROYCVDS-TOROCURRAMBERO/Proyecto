package com.registerLab.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.shiro.SecurityUtils;

import com.google.inject.Injector;
import com.registerLab.ECILabException;
import com.registerLab.entities.Elemento;
import com.registerLab.entities.Equipo;
import com.registerLab.entities.Laboratorio;
import com.registerLab.servicios.ServiciosECILabImpl;

@SuppressWarnings("deprecation")
@ManagedBean(name="asoBean")
@RequestScoped
public class AsociarBean extends BaseBeanRegisterLab{
	@ManagedProperty(value="#{param.idEquipo}")
	private int idEquipo;
	private int idElemento;
	private int idLaboratorio;
	private Injector injector;
	private int equipo;
	private ServiciosECILabImpl servicios;
	public AsociarBean() {
		injector = super.getInjector();
		servicios = injector.getInstance(ServiciosECILabImpl.class);
	}
	public void setIdEquipo(int id) {
		this.idEquipo = id;
	}
	public int getIdEquipo() {
		return idEquipo;
	}
	public void setIdElemento(int id) {
		idElemento = id;
	}
	public int getIdElemento() {
		return idElemento;
	}
	public void asociarElemento(Elemento elm) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			System.out.println("id                 "+elm.getId()+" "+idEquipo);
			servicios.asociarElemento(elm.getId(), idEquipo,servicios.getUsuario(SecurityUtils.getSubject().getPrincipal().toString()).getId());
			context.addMessage(null, new FacesMessage("Succesfull","se ha asociado correctamente"));
		} catch (ECILabException e) {
	        context.addMessage(null, new FacesMessage("Error",e.getMessage()));
		}
	}
	
	public void asociarEquipo() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			servicios.asociarEquipo(idEquipo, idLaboratorio,servicios.getUsuario(SecurityUtils.getSubject().getPrincipal().toString()).getId());
			context.addMessage(null, new FacesMessage("Succesfull","se ha asociado correctamente"));
		} catch (ECILabException e) {
	        context.addMessage(null, new FacesMessage("Error",e.getMessage()));
		}
	}
	
	public List<Equipo> getEquipos(){
		return servicios.getEquipos();
	}
	
	public List<Laboratorio> getLaboratorios(){
		return servicios.getLaboratorios();
	}
	
	public Equipo getEquipo() {
		return servicios.getEquipo(equipo);
	}
	
	public void setEquipo(int equipo) {
		this.equipo = equipo;
	}
	
	
}
