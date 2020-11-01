package com.registerLab.beans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.google.inject.Injector;
import com.registerLab.entities.Equipo;
import com.registerLab.entities.Novedad;
import com.registerLab.servicios.ServiciosECILabImpl;

@ManagedBean(name="eqnovsBean")
@SessionScoped
public class EquipoNovedadesBean extends BaseBeanRegisterLab{
	private Injector injector;
	private ServiciosECILabImpl servicios;
	private int equipo;
	public EquipoNovedadesBean() {
		injector = super.getInjector();
		servicios = injector.getInstance(ServiciosECILabImpl.class);
	}
	public void setEquipo(int equipo) {
		this.equipo = equipo;
	}
	public Equipo get() {
		return servicios.getEquipo(equipo);
	}
	public int getEquipo() {
		return equipo;
	}
	public ArrayList<Novedad> getNovedades(){
		return servicios.novedadesEquipo(equipo);
	}
	

}
