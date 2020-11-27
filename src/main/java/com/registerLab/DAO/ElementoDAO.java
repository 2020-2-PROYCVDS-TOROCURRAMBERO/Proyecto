package com.registerLab.DAO;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.registerLab.entities.Elemento;
import com.registerLab.entities.Equipo;
import com.registerLab.entities.Usuario;
import com.registerLab.ECILabException;
public interface ElementoDAO {
	Elemento getElemento(int id);
	public void AgregarElemento(
			String categoria,
			String fabricante,
			String referencia,
			Date fechaAdquisicion,
			Date fechaInicioActividad,
			Date fechaFinActividad) throws ECILabException;
	public List <Elemento> consultarElementos();
	void desvincularElementos(String categoria, int id);
	void darBaja(int elemento);
	boolean elementoAsociadoaEquipo(int elemento);
	ArrayList<Elemento> getElemento(String categoria);
	ArrayList<Elemento> getElementosActivos();
}