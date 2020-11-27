package com.registerLab.beans;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.shiro.SecurityUtils;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.registerLab.ECILabException;
import com.registerLab.entities.Elemento;
import com.registerLab.entities.Equipo;
import com.registerLab.servicios.ServiciosECILabImpl;



@ManagedBean(name="equiBean")
@SessionScoped

public class EquipoBean  extends BaseBeanRegisterLab {
	
	@Inject
	private ServiciosECILabImpl servicios;
	private Injector injector;
	private int id;
	private java.util.Date fechaFin;
	private java.util.Date fechaAdquisicion;
	private java.util.Date fechaInicioActividad;
	private int idElemento;
	private int idAnt;
	private String descripcion;
	private String categoria;
	private ArrayList<Elemento> elementos;
	
	
	public EquipoBean() {
		injector = super.getInjector();
		servicios = injector.getInstance(ServiciosECILabImpl.class);
		startElementos();
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria=categoria;
	}
	public void add(Elemento e) {
		elementos.add(e);
	}
	public ArrayList<Elemento> getElementos(String categoria) {
		return servicios.getElementos(categoria);
	}
	public ArrayList<Elemento> getElementos() {
		return elementos;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setFechaInicioActividad(java.util.Date fechaInicioActividad) {
		this.fechaInicioActividad = fechaInicioActividad;
	}
	public void setFechaFinActividad(java.util.Date fechaFinActividad) {
		this.fechaFin = fechaFinActividad;
	}
	public void setFechaAdquisicion(java.util.Date fechaAdquisicion) {
		this.fechaAdquisicion=fechaAdquisicion;
	}
	public void setElementos(ArrayList<Elemento> elementos) {
		this.elementos = elementos;
	}
	
	public void setidAnt(int id) {
		this.idAnt=id;
	}
	
	public void setidElemento(int id) {
		this.idElemento = id;
	}
	
	public int getidElemento() {
		return idElemento;
	}
	
	public int getidAnt() {
		return idAnt;
	}
	
	
	public int getId() {
		return id;
	}
	public java.util.Date getfechaInicioActividad() {
		return fechaInicioActividad;
	}
	public java.util.Date getfechaFinActividad(){
		return fechaFin;
	}
	public java.util.Date getFechaAdquisicion() {
		return fechaAdquisicion;
	}
	public ArrayList<Elemento> getelementos() {
		return elementos;
	}
	/*
	 * Registra un equipo y los elementos a este
	 */
	public void registrarEquipo() {
		FacesContext context = FacesContext.getCurrentInstance();
		try{
			Date d=null; 
			Date da=null;
			if(elementos.size()<4) throw new ECILabException("Error: Hacen falta elementos para registrar este equipo.");
			if(fechaAdquisicion!=null) d= new Date(fechaAdquisicion.getTime());
			if(fechaInicioActividad!=null) da= new Date(fechaInicioActividad.getTime());
			servicios.insertarEquipoSinLaboratorio(id, da, null, d, elementos);
			elementos.clear();
			context.addMessage(null, new FacesMessage("¡Listo!","El equipo ha sido registrado satisfactoriamente."));
		}catch(Exception e){
			context.addMessage(null, new FacesMessage("Error",e.getMessage()) );			
		}
	}
	/*
	 * 
	 */
	public void asociarElemento() {
			elementos.add(servicios.getElemento(idElemento));
			FacesContext context = FacesContext.getCurrentInstance();
			try {
				Date actual=null;
				java.util.Date fechaActual = new java.util.Date(); 		
				actual=new Date (fechaActual.getTime());
				int ultimo = servicios.getUltimaNovedad();
				servicios.asociarElemento(idElemento, id,servicios.getUsuario(SecurityUtils.getSubject().getPrincipal().toString()).getId());
				servicios.AgregarNovedad("Se ha realizado una nueva asociación al elemento "+String.valueOf(idElemento)+" con su respectivo equipo "+String.valueOf(id),"Nueva asociacion de elemento", id, idElemento,servicios.getUsuario(SecurityUtils.getSubject().getPrincipal().toString()).getId());
				context.addMessage(null, new FacesMessage("¡Listo!","El elemento ha sido asociado satisfactoriamente.") );
				
				
			} catch (ECILabException e) {
				context.addMessage(null, new FacesMessage("Error","No se ha podido asociar el elemento, por favor verifique la información ingresada a continuación." ));
			}		
	}
	
	public void modificarAsociarElemento() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
		Date actual=null;
		java.util.Date fechaActual = new java.util.Date(); 		
		actual=new Date (fechaActual.getTime());
		int ultimo = servicios.getUltimaNovedad();
		servicios.AgregarNovedad(descripcion, "Cambio de asociación del elemento a otro equipo", id, idElemento,servicios.getUsuario(SecurityUtils.getSubject().getPrincipal().toString()).getId());
		servicios.asociarElemento(idElemento, id);
		context.addMessage(null, new FacesMessage("¡Listo!","La asociación ha sido modificada satisfactoriamente."));
		}
		catch(Exception e) {
			context.addMessage(null, new FacesMessage("Error","No se ha podido modificar la asociación, por favor verifique la informacion ingresada a continuación.") );
		}
	}
	public Equipo getEquipo() {
		return servicios.getEquipo(id);
	}
	/*
	 * Inicia el arrayList de elementos para evitar que a un equipo se vinculen mas de un elemento de la misma categoria
	 */
	public void startElementos() {
		elementos = new ArrayList<Elemento>() {
			@Override
			public boolean add(Elemento e) {
				if(e.getFechaFinActividad()==null || servicios.elementoAsociadoaEquipo(e.getId())) {
					ArrayList<Elemento> toRemove = new ArrayList<>();
					for(int i=0;i<size();i++) {
						if(get(i).getCategoria().equals(e.getCategoria())) toRemove.add(get(i));
					}
					for(Elemento el:toRemove) {
						remove(el);
					}
					return super.add(e);
				}
				else {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage("Error","No ha sido posible añadir el elemento que ha sido seleccionado."));
					return false;
				}
			} 
		};
	}
}