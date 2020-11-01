package com.registerLab.beans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.google.inject.Injector;
import com.registerLab.entities.Elemento;
import com.registerLab.entities.Novedad;
import com.registerLab.servicios.ServiciosECILabImpl;

@ManagedBean(name="ENovBean")
@SessionScoped
public class ElementoBeanNovedad extends BaseBeanRegisterLab{
	private int elemento;
	private int equipo;
	private Injector injector;
	private ServiciosECILabImpl servicios;
	public ElementoBeanNovedad() {
		injector = super.getInjector();
		servicios = injector.getInstance(ServiciosECILabImpl.class);
	}
	/*
	 * Actualiza la id del elemento
	 * @Param elemento - la id del elemento
	 */
	public void setElemento(int elemento) {
		this.elemento = elemento;
	}
	/*
	 * @Return devuelve el elemento recopilado por la vista
	 */
	public int getElemento() {
		return elemento;
	}
	/*
	 * @return devuelve el elemento correspondiente a el atributo elemento
	 */
	public Elemento get() {
		return servicios.getElemento(elemento);
	}
	/*
	 * @return devuelve todas las novedades asociadas al elemento
	 */
	public ArrayList<Novedad> getNovedad(){
		return servicios.getNovedadesElemento(elemento);
	}
	/*
	 * @return devuelve los elementos que corresponden al equipo y al elemento
	 */
	public ArrayList<Novedad> getNovedadElementoEquipo(){
		return servicios.getNovedades(elemento,equipo);
	}
	/*
	 * @Return id del equipo
	 */
	public int getEquipo() {
		return equipo;
	}
	/*
	 * @param id - la id del equipo a actualizar
	 */
	public void setEquipo(int id) {
		equipo = id;
	}

}
