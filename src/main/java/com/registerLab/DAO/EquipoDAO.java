package com.registerLab.DAO ;

import java.sql.Date;
import java.util.ArrayList;

import com.registerLab.ECILabException;
import com.registerLab.entities.Equipo;

public interface EquipoDAO{

	Equipo getEquipo(int id);
	
	void insertarEquipoSinLaboratorio(int id,
			Date fechaInicioActividad,
			Date fechafinactividad,
			Date fechaAdquisicion)throws ECILabException;
	
	void insertarEquipo(int id,
			Date fechaInicioActividad,
			Date fechafinactividad,
			Date fechaAdquisicion,
			int lab)throws ECILabException;
	
	void asociarElemento(int idElemento, int IdEquipoN)throws ECILabException;
	
	ArrayList<Equipo> getEquipos();
	
	boolean equipoPoseElemento(int elemento);
	
	
	void darBaja(int equipo);
	
	ArrayList<Equipo> getEquiposinLab();

	void desvincularEquipo(int idEquipo);
	
	boolean equipoAsociadoaLaboratorio(int equipo);

	ArrayList<Equipo> getAllEquipos();
	
}