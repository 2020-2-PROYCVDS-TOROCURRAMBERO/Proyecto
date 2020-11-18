package com.registerLab.myBatisDAO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.registerLab.ECILabException;
import com.registerLab.DAO.ElementoDAO;
import com.registerLab.entities.Elemento;
import com.registerLab.mappers.ElementoMapper;


public class MyBatisElementoDAO implements ElementoDAO{
	@Inject
	private ElementoMapper mapper;
	
	@Override
	public Elemento getElemento(int id) {
		
		return mapper.getElemento(id);
		
	}
	
	@Override
	public void AgregarElemento(String categoria, String fabricante, String referencia, Date fechaAdquisicion, Date fechaInicioActividad, Date fechaFinActividad) 
			throws ECILabException {

		
		if(fechaFinActividad != null) throw new ECILabException("la fecha fin de actividad debe ser nula");
		
		if(!(categoria.equals("TORRE")  || categoria.equals("PANTALLA") || categoria.equals("MOUSE") || categoria.equals("TECLADO"))) throw new ECILabException("Categoria Erronea");
		
		if(fechaAdquisicion==null) throw new ECILabException("la fecha de aquisicion, no debe ser nula");
		
		mapper.AgregarElemento(categoria, fabricante, referencia, fechaAdquisicion, fechaInicioActividad, fechaFinActividad);
		
		
	}
	
	@Override
	public List <Elemento> consultarElementos() {
		
		return mapper.consultarElementos();
		
	}

	@Override
	public void desvincularElementos(String categoria, int id) {
		mapper.desvincularElementos(categoria,id);
		
	}

	@Override
	public void darBaja(int elemento) {
		mapper.darBaja(elemento);
		
	}

	@Override
	public boolean elementoAsociadoaEquipo(int elemento) {
		return mapper.elementoAsociadoaEquipo(elemento);
	}

	@Override
	public ArrayList<Elemento> getElemento(String categoria) {
		return mapper.getElementoPorCategoria(categoria);
	}

	@Override
	public ArrayList<Elemento> getElementosActivos() {
		return mapper.getElementosActivos();
	}
	
}