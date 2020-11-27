package com.registerLab.beans;

import java.io.IOException;
import java.util.ArrayList;

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

@ManagedBean(name="bajaeqBean")
@SessionScoped
public class BajaEquipoBeanN extends BaseBeanRegisterLab{
	private Injector injector;
	private ServiciosECILabImpl servicios;
	private int equipo;
	private ArrayList<Elemento> darBaja;
	private ArrayList<Elemento> desasociar;
	public BajaEquipoBeanN() {
		injector = super.getInjector();
		servicios = injector.getInstance(ServiciosECILabImpl.class);
		darBaja = new ArrayList<>();
		desasociar = new ArrayList<>();
	}
	public void setEquipo(int equipo) {
		this.equipo = equipo;
	}
	public int getEquipo() {
		return equipo;
	}
	public ArrayList<Elemento> getDarBaja(){
		return darBaja;
	}
	public ArrayList<Elemento> getDesasociar(){
		return desasociar;
	}
	public void add(Elemento e) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Listo!","Ha sido agregado para darse de baja satisfactoriamente."));
		System.out.println("a");
		Elemento t = null;
		for(Elemento el:desasociar) {
			if(el.getId()==e.getId()) t= el;
		}
		if(t!=null) desasociar.remove(t);
		darBaja.remove(e);
		darBaja.add(e);
	}
	public void desasociarElemento(Elemento e) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Listo!","Se ha agregado a desasociar satisfactoriamente."));
		Elemento t = null;
		for(Elemento el:darBaja) {
			if(el.getId()==e.getId()) t = el;
		}
		if(t!=null) darBaja.remove(t);
		desasociar.remove(e);
		desasociar.add(e);
	}
	public void validar() {
		Equipo e = servicios.getEquipo(equipo);
		boolean v = false;
		for(Elemento el:darBaja) {
			v = v || !e.getElementos().contains(el);
		}
		for(Elemento el:desasociar) {
			v = v || !e.getElementos().contains(el);
		}
		if(v) {
			darBaja = new ArrayList<>();
			desasociar = new ArrayList<>();
		}
	}
	public void darDeBajaEquipo() {
			Equipo eq = servicios.getEquipo(equipo);
			if(eq.getElementos().size()==darBaja.size()+desasociar.size()) {
			for(Elemento e: desasociar) {
				servicios.desvincularElemento(e, eq);
			}
			for(Elemento e:darBaja) {
				servicios.darBajaConEquipoAsociado(e, eq);
			}
			try {
				servicios.darBajaEquipo(eq.getId(), servicios.getUsuario(SecurityUtils.getSubject().getPrincipal().toString()).getId());
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("useradmin.xhtml");
				} catch (IOException e1) {
				}
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Listo!","Se ha agregado a desasociar satisfactoriamente."));
			} catch (ECILabException e2) {
				
			}			}else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error","No se puede dar de baja este equipo."));

			}
	}
}