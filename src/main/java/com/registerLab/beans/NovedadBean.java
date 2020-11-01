package com.registerLab.beans;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.shiro.SecurityUtils;

import com.google.inject.Injector;
import com.registerLab.ECILabException;
import com.registerLab.entities.Novedad;
import com.registerLab.servicios.ServiciosECILabImpl;
@ManagedBean(name="novBean")
@SessionScoped
public class NovedadBean extends BaseBeanRegisterLab{
	private String detalle;
	private String descripcion;
	private int equipo;
	private Injector injector;
	private ServiciosECILabImpl servicios;
	private int elemento;
	public NovedadBean() {
		injector = getInjector();
		servicios = injector.getInstance(ServiciosECILabImpl.class);	
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setEquipo(int equipo) {
		this.equipo = equipo;
	}
	public int getEquipo() {
		return equipo;
	}
	public void setElemento(int elemento) {
		this.elemento = elemento;
	}
	public int getElemento() {
		return elemento;
	}
	public void registrarNovedad() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			servicios.AgregarNovedad(descripcion, detalle, equipo, elemento, servicios.getUsuario(SecurityUtils.getSubject().getPrincipal().toString()).getId());
			context.addMessage(null, new FacesMessage("Succesfull","Insertada.") );
		} catch (ECILabException e) {
			context.addMessage(null, new FacesMessage("Error","No fue posible realizar la insercion") );
		}
	}
	public ArrayList<Novedad> getNovedades(){
		return servicios.getNovedades();
	}
}
