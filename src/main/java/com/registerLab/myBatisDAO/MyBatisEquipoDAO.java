package com.registerLab.myBatisDAO;

import java.sql.Date;

import java.util.ArrayList;

import com.google.inject.Inject;

import com.registerLab.ECILabException;

import com.registerLab.DAO.EquipoDAO;

import com.registerLab.entities.Equipo;

import com.registerLab.mappers.EquipoMapper;

public class MyBatisEquipoDAO implements EquipoDAO{
	
	@Inject
	private EquipoMapper mapper;
	
	
	@Override
	public Equipo getEquipo(int id) {
		return mapper.getEquipo(id);
	}

	@Override
	public void insertarEquipoSinLaboratorio(int id,Date fechainicioactividad,Date fechafinactividad,Date fechaadquisicion) 
			throws ECILabException{
		
		if(getEquipo(id)!=null) throw new ECILabException("Ya existe un equipo con esta id");
		
		if(fechafinactividad != null) throw new ECILabException("El equipo debe estar activo");
	
		mapper.insertarEquipoSinLaboratorio(id, fechainicioactividad, fechafinactividad, fechaadquisicion);
		
	}
	
	@Override
	public void insertarEquipo(int id,Date fechainicioactividad,Date fechafinactividad,Date fechaadquisicion,int lab) 
			throws ECILabException{
		
		if(getEquipo(id)!=null) throw new ECILabException("Ya existe un equipo con esta id");
		
		if(fechafinactividad != null) throw new ECILabException("El equipo debe estar activo");
	
		mapper.insertarEquipo(id, fechainicioactividad, fechafinactividad, fechaadquisicion,lab);
		
	}
	
	@Override 
	public void asociarElemento(int idElemento, int IdEquipoN)throws ECILabException{
		Equipo e = getEquipo(IdEquipoN);
		if(e==null) throw new ECILabException("El equipo no se encuetra registrado");
		if(e.getFechaFinActividad()!=null) throw new ECILabException("El equipo fue dado de baja, no se le pueden asociar elementos.");
		mapper.asociarElemento(idElemento, IdEquipoN);
	}

	@Override
	public ArrayList<Equipo> getEquipos() {
		return mapper.getEquipos();
	}

	@Override
	public boolean equipoPoseElemento(int elemento) {
		return mapper.equipoPoseElemento(elemento);
	}
	
	@Override
	public void darBaja(int equipo) {
		mapper.darBaja(equipo);
	}
	
	public void desvincularEquipo(int idEquipo) {
		
	}
	
	public boolean equipoAsociadoaLaboratorio(int equipo) {
		return mapper.equipoAsociadoaLaboratorio(equipo);
	}

	@Override
	public ArrayList<Equipo> getAllEquipos() {
		return mapper.getAllEquipos();
	}
	
	@Override
	public ArrayList<Equipo> getEquiposinLab(){
		return mapper.getEquiposinLab();
	}

}