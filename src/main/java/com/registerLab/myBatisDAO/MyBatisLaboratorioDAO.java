package com.registerLab.myBatisDAO;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.google.inject.Inject;
import com.registerLab.ECILabException;
import com.registerLab.DAO.EquipoDAO;
import com.registerLab.DAO.LaboratorioDAO;
import com.registerLab.entities.Equipo;
import com.registerLab.entities.Laboratorio;
import com.registerLab.mappers.EquipoMapper;
import com.registerLab.mappers.LaboratorioMapper;

public class MyBatisLaboratorioDAO implements LaboratorioDAO{
	
	@Inject
	private LaboratorioMapper mapper;
	
	@Override
	public Laboratorio getLaboratorio(int id) {
		return mapper.getLaboratorio(id);
	}
	
	@Override
	public void asociarEquipo(int idEquipo, int idLaboratorioN) throws ECILabException{
		Laboratorio e = getLaboratorio(idLaboratorioN);
		if(e==null) throw new ECILabException("El equipo no se encuetra registrado");
		mapper.asociarEquipo(idEquipo, idLaboratorioN);	
	}
	
	@Override
	public void agregarLaboratorio(int id, String nombre, int capacidad, Date fechacierre, Date fechaapertura) throws ECILabException {
		if (getLaboratorio(id) != null) throw new ECILabException ("Este laboratorio ya esta registrado");
		if (nombre == null || nombre == "") throw new ECILabException ("El nombre del laboratorio no debe estar vacio");
		mapper.agregarLaboratorio(id, nombre, capacidad, fechacierre,fechaapertura);
	}
	
	@Override
	public List<Laboratorio> getLaboratorios(){
		return mapper.getLaboratorios();
	}

	@Override
	public void desasociarEquipo(int equipo) {
		mapper.desasociarEquipo(equipo);
		
	}

	@Override
	public void cerrarLaboratorio(int laboratorio) {
		mapper.cerrarLaboratorio(laboratorio);
		
	}
	
	@Override
	public List<Laboratorio> getTodosLaboratorios(){
		return mapper.getTodosLaboratorios();
	}
	
	@Override
	public int cantidadEquipo( int laboratorio) {
		return mapper.cantidadEquipo(laboratorio);
	}
	
	@Override
	public int ElementosLaboratorio(int laboratorio) {
		return mapper.ElementosLaboratorio(laboratorio);
	}
	
	@Override
	public int equiposLaboratorios(String mes) {
		return mapper.equiposLaboratorios(mes);
	}

	@Override
	public Laboratorio getLaboratorioEquipo(int idEquipo) {
		return mapper.getLaboratorioEquipo(idEquipo);
	}
}