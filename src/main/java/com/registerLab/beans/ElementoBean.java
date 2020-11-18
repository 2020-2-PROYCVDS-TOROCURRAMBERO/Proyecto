package com.registerLab.beans;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.registerLab.ECILabException;
import com.registerLab.entities.Elemento;
import com.registerLab.servicios.ServiciosECILabImpl;
import java.sql.Date;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="elmBean")
@SessionScoped
public class ElementoBean extends BaseBeanRegisterLab{
	@Inject
	private ServiciosECILabImpl servicios;
	private Injector injector;
	private int id;
	private String categoria;
	private String fabricante;
	private String referencia;
	private java.util.Date fechaAdquisicion;
	
	public ElementoBean() {
		injector = super.getInjector();
		servicios = injector.getInstance(ServiciosECILabImpl.class);
		categoria= "TORRE";
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public void setReferencia(String referencia) {
		this.referencia=referencia;
	}
	public void setFechaAdquisicion(java.util.Date fecha) {
		this.fechaAdquisicion = fecha;
	}
	public int getId() {
		return id;
	}
	public String getFabricante() {
		return fabricante;
	}
	public String getReferencia(){
		return referencia;
	}
	public java.util.Date getFechaAdquisicion() {
		return fechaAdquisicion;
	}
	public String getCategoria() {
		return categoria;
	}
	public void registrarElemento() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			Date d=null; 
			if(fechaAdquisicion!=null) d= new Date(fechaAdquisicion.getTime());
			servicios.AgregarElemento(categoria, fabricante, referencia, d, null, null);
	        context.addMessage(null, new FacesMessage("Succesfull","elemento insertado.") );
		}catch(ECILabException e) {
			context.addMessage(null, new FacesMessage("Error",e.getMessage()));
		}	
	}
	public ArrayList<Elemento> getElementosActivos(){
		return servicios.getElementosActivos();
		
	}
	public Elemento getElemento() {
		return servicios.getElemento(id);
	}
	
}
