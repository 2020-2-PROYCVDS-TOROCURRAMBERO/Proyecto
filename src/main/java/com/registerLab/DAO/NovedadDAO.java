package com.registerLab.DAO;

import java.util.ArrayList;


import com.registerLab.ECILabException;
import com.registerLab.entities.Novedad;

public interface NovedadDAO {
	
		Novedad getNovedad(int id)throws ECILabException;
		int getUltimaNovedad();
		
		void agregarNovedad(
		String descripcion,
		String justificacion,
		int idEquipo,
		int idElemento,
		int usuario) throws ECILabException;

		void registrarNovedadSinEquipo(String descripcion, String justificacion, int elemento, int usuario);
		
		ArrayList<Novedad> getNovedades();

		ArrayList<Novedad> novedadesEquipo(int equipo);


		ArrayList<Novedad> getNovedadesElemento(int elemento);
		
		ArrayList<Novedad> getNovedadesLabEqui(int labo);
		
		ArrayList<Novedad> getNovedades(int elemento, int equipo);

		void agregarNovedadSinElemento(String razon, String justificacion, int equipo, int usuario);

}
