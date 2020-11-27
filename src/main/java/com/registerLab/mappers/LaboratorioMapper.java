package com.registerLab.mappers;
import java.sql.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.registerLab.entities.Laboratorio;
import com.registerLab.entities.Usuario;
public interface LaboratorioMapper {	
	public Laboratorio getLaboratorio(@Param("Id") int id);	
	public void agregarLaboratorio(@Param("Id") int id,@Param("Nom") String nombre,@Param("capacidad") int capacidad,@Param("fecha") Date fechacierre,@Param("fechaA") Date fechaapertura);	
	public void asociarEquipo(@Param("equipo")int idEquipo,@Param("laboratorio") int idLaboratorioN);	
	public List<Laboratorio> getLaboratorios();
	public void desasociarEquipo(@Param("equipo")int equipo);
	public void cerrarLaboratorio(@Param("lab")int laboratorio);	
	public List<Laboratorio> getTodosLaboratorios();	
	public int cantidadEquipo(@Param("lab") int laboratorio);	
	public int ElementosLaboratorio(@Param("labo") int laboratorio);	
	public int equiposLaboratorios(@Param("mes") String mes);
	public Laboratorio getLaboratorioEquipo(@Param("id")int idEquipo);
}