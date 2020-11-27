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
	 * Actualiza el ID del elemento.
	 * @Param elemento ID del elemento.
	 */
	public void setElemento(int elemento) {
		this.elemento = elemento;
	}
	/*
	 * @Return Retorna el elemento adquirido por la vista.
	 */
	public int getElemento() {
		return elemento;
	}
	/*
	 * @return Retorna el elemento correspondiente al atributo elemento.
	 */
	public Elemento get() {
		return servicios.getElemento(elemento);
	}
	/*
	 * @return Retorna las novedades asociadas al elemento.
	 */
	public ArrayList<Novedad> getNovedad(){
		return servicios.getNovedadesElemento(elemento);
	}
	/*
	 * @return Retorna los elementos correspondientes al equipo y al elemento.
	 */
	public ArrayList<Novedad> getNovedadElementoEquipo(){
		return servicios.getNovedades(elemento,equipo);
	}
	/*
	 * @Return Retorna el ID del equipo
	 */
	public int getEquipo() {
		return equipo;
	}
	/*
	 * @param id ID del equipo por actualizar.
	 */
	public void setEquipo(int id) {
		equipo = id;
	}
}