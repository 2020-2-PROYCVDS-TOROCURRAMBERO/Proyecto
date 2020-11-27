package com.registerLab.beans;

import java.sql.Date;
import java.util.ArrayList;

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
import com.registerLab.entities.Laboratorio;
import com.registerLab.servicios.ServiciosECILabImpl;

@ManagedBean(name="laboBean")
@SessionScoped

public class LaboratorioBean extends BaseBeanRegisterLab  {
	@Inject
	private ServiciosECILabImpl servicios;
	private Injector injector;
	private int id;
	private int capacidad;
	private String nombre;
	private java.util.Date fechaCierre;	
	private java.util.Date fechaApertura;
	private ArrayList<Equipo> equipos;
	
	public LaboratorioBean() {
		injector = super.getInjector();
		servicios = injector.getInstance(ServiciosECILabImpl.class);
		startEquipos();
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void add(Equipo e) {
		equipos.add(e);
	}
	/*
	 * @param e - equipo a ser removido de los posibles equipos que conformen el laboratorio
	 */
	
	public void remove(Equipo e) {
		equipos.remove(e);
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setfechaCierre(java.util.Date fechaCierre) {
		this.fechaCierre = fechaCierre;		
	}
	
	public java.util.Date getfechaCierre() {
		return fechaCierre;
	}
	
	public ArrayList<Equipo> getEquipos() {
		return equipos;
	}
	
	public ArrayList<Equipo> getEquipossinLab(){
		return servicios.getEquiposinLab();
	}

	public void setEquipos(ArrayList<Equipo> equipos) {
		this.equipos = equipos;
	}
	
	public void setEquipossinLab(ArrayList<Equipo> equipos){
		this.equipos = equipos;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	
	public void agregarLaboratorio() { 
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			Date d=null; 
			d= new Date(fechaApertura.getTime());
			if(equipos.size() > getCapacidad()) throw new ECILabException("La cantidad de equipos registrados exceden la capacidad del laboratorio en cuestión.");
			if (equipos.size() == 0) throw new ECILabException("Error: El laboratorio debe tener por lo menos un equipo asociado.");
			servicios.agregarLaboratorio(id, nombre, capacidad, null,d);
			for(Equipo e:equipos) {
				servicios.asociarEquipo(e.getId(), id, servicios.getUsuario(SecurityUtils.getSubject().getPrincipal().toString()).getId());

				//servicios.AgregarNovedad("Crear Laboratorio", "Completar capacidad del laboratorio",e.getId(),0, servicios.getUsuario(SecurityUtils.getSubject().getPrincipal().toString()).getId());

				servicios.agregarNovedadSinElemento("Asocio equipo a laboratorio", "Completar laboratorio", id, servicios.getUsuario(SecurityUtils.getSubject().getPrincipal().toString()).getId());

			}
			equipos.clear();
			limpiar();
	        context.addMessage(null, new FacesMessage("¡Listo!","El laboratorio ha sido registrado satisfactoriamente.") );
		}catch(ECILabException e) {
			context.addMessage(null, new FacesMessage("Error",e.getMessage()));
		}
	}
	public Laboratorio getLaboratorio() {
		return servicios.getLaboratorio(id);
	}
	public java.util.Date getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(java.util.Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	
	public void limpiar() {
		this.capacidad =0;
		this.fechaApertura = null;
		this.fechaCierre = null;
		this.id = 0;
		this.nombre = null;
	}
	
	public void startEquipos() {
		equipos = new ArrayList<Equipo>() {
			@Override
			public boolean add(Equipo e) {
				if(e.getFechaFinActividad()==null || servicios.equipoAsociadoaLaboratorio(e.getId())) {
					ArrayList<Equipo> toRemove = new ArrayList<>();
					for(int i=0;i<size();i++) {
						if(get(i).getId() == e.getId()) toRemove.add(get(i));
					}
					for(Equipo el:toRemove) {
						remove(el);
					}
					return super.add(e);
					
				}
				else {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage("Error","No ha sido posible añadir el equipo seleccionado a continuación.") );
					return false;
				}
			} 
		};
	}
	
}
