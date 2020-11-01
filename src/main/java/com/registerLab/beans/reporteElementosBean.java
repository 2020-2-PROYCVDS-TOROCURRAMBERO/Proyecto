package com.registerLab.beans;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.google.inject.Injector;
import com.registerLab.entities.Elemento;
import com.registerLab.servicios.ServiciosECILabImpl;

@ManagedBean(name="regElmBean")
@RequestScoped
public class reporteElementosBean extends BaseBeanRegisterLab {
	
	private Injector injector;
	private ServiciosECILabImpl servicios;
	private int idElemento;
	private String activo;
	private final static String[] categorias;
	static {
		categorias = new String[4];
		categorias[0] = "TORRE";
		categorias[1] = "PANTALLA";
		categorias[2] = "MOUSE";
		categorias[3] = "TECLADO";
	}
	
	public reporteElementosBean() {
		injector = super.getInjector();
		servicios = injector.getInstance(ServiciosECILabImpl.class);
	}
	public List<Elemento> getTotalElementos(){
		try{
			List<Elemento> el = servicios.getElementos();
			return el;
		}catch(Exception e){
			return null;
		}
	}
	
	public Elemento getElemento() {
		return servicios.getElemento(idElemento);
	}
	
	public int getIdElemento() {
		return idElemento;
	}
	
	public void setIdElemento(int idElemento) {
		this.idElemento = idElemento;
	}
	
	public String ElementoActivo() {
		if (getElemento().getFechaFinActividad() == null) {
			this.activo = "Activo";
			
		}
		else {
			this.activo = "Inactivo"; 
		}
		return activo;
	}
	
	public List<String> getCategorias() {
        return Arrays.asList(categorias);
    }
	
	
			
}
	

