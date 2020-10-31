package com.registerLab.mappers;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.registerLab.entities.Elemento;
public interface ElementoMapper {	
	public Elemento getElemento(@Param("ElemId") int id);
	public void AgregarElemento(@Param("id") int id
			,@Param("cat") String categoria
			,@Param("fab") String fabricante
			,@Param("ref") String referencia
			,@Param("fecAq") Date fechaAdquisicion
			,@Param("fecIn") Date fechaInicioActividad
			,@Param("fecFin") Date fechaFinActividad);	
	public List<Elemento> consultarElementos();
	public void desvincularElementos(@Param("categoria") String categoria,@Param("equipo") int id);
	public void darBaja(@Param("elemento")int elemento);
	public boolean elementoAsociadoaEquipo(@Param("elemento")int elemento);
	public ArrayList<Elemento> getElementoPorCategoria(@Param("categoria") String categoria);
	public ArrayList<Elemento> getElementosActivos();
}