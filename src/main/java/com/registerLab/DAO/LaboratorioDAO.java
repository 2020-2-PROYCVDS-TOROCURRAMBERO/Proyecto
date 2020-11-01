package com.registerLab.DAO ;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.registerLab.ECILabException;
import com.registerLab.entities.Equipo;
import com.registerLab.entities.Laboratorio;

public interface LaboratorioDAO{

	Laboratorio getLaboratorio(int id);
	
	void agregarLaboratorio(int id, String nombre, int capacidad, Date fechacierre, Date fechaapertura) throws ECILabException;
	
	List<Laboratorio> getLaboratorios();
	
	List<Laboratorio> getTodosLaboratorios();
	
	void asociarEquipo(int idEquipo, int idLaboratorioN) throws ECILabException;

	void desasociarEquipo(int equipo);

	void cerrarLaboratorio(int laboratorio);
	
	int cantidadEquipo( int laboratorio);
	
	int ElementosLaboratorio(int laboratorio);
	
	int equiposLaboratorios(String mes);

	Laboratorio getLaboratorioEquipo(int idEquipo);
}