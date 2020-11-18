package com.registerLab.servicios;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.shiro.SecurityUtils;

import com.google.inject.Inject;
import com.registerLab.ECILabException;
import com.registerLab.DAO.ElementoDAO;
import com.registerLab.DAO.EquipoDAO;
import com.registerLab.DAO.LaboratorioDAO;
import com.registerLab.DAO.NovedadDAO;
import com.registerLab.DAO.UsuarioDAO;
import com.registerLab.entities.Elemento;
import com.registerLab.entities.Equipo;
import com.registerLab.entities.Laboratorio;
import com.registerLab.entities.Novedad;
import com.registerLab.entities.Usuario;

public  class ServiciosECILabImpl implements ServiciosECILab{
	@Inject
	private UsuarioDAO usuario;
	@Inject
	private EquipoDAO equipo;
	@Inject
	private LaboratorioDAO laboratorio;
	@Inject
	private NovedadDAO novedad;
	@Inject
	private ElementoDAO elemento;
	
	
	/*
	 * @param correo - correo del usuario a buscar
	 * @return devuelve el Usuario correspondiente al correo
	 */
	public Usuario getUsuario(String correo) {
		return usuario.getUsuario(correo);
	}
	/*
	 * @Param id la id del equipo a registrar
	 * @throws ECILabException
	 */
	public void insertarEquipoSinLaboratorio(int id,Date fechaInicioActividad,Date fechafinactividad,Date fechaAdquisicion) throws ECILabException {
		if(fechaInicioActividad==null || fechaAdquisicion==null) throw new ECILabException("fecha adquisicion o fecha fin de actividad no pueden ser nulas");
		if(fechaInicioActividad.before(fechaAdquisicion)) throw new ECILabException("Un equipo no puede iniciar actividad antes de su adquisicion.");
		equipo.insertarEquipoSinLaboratorio(id, fechaInicioActividad, fechafinactividad, fechaAdquisicion);
	}
	
	public void insertarEquipoSinLaboratorio(int id,Date fechaInicioActividad,Date fechafinactividad,Date fechaAdquisicion,ArrayList<Elemento> elementos) throws ECILabException{
	
		if(elementos==null) throw new ECILabException("DEben haber elementos");
		if(elementos.size()!=4) throw new ECILabException("Verifique el numero de elementos");
		if(!hayUnElementoDeCadaCategoria(elementos)) throw new ECILabException("Verifique los elementos, hay categorias repetidas.");
		if(!elementosLibres(elementos)) throw new ECILabException("Algunos elementos ya se encuentran vinculados a otro equipo");
		insertarEquipoSinLaboratorio(id,fechaInicioActividad,fechafinactividad,fechaAdquisicion);
		for(Elemento e:elementos) {
			this.asociarElemento(e.getId(), id);
			this.AgregarNovedad("Se asocio el elemento "+String.valueOf(e.getId())+" al equipo "+String.valueOf(id), "Elemento necesario para registrar el equipo", id, e.getId(), this.getUsuario(SecurityUtils.getSubject().getPrincipal().toString()).getId());
		}
		
	}
	
	private boolean elementosLibres(ArrayList<Elemento> elementos) {
	
		for(Elemento e:elementos) {
			if(this.equipoPoseElemento(e.getId())) return false;
		}
		return true;
	}
	
	private boolean hayUnElementoDeCadaCategoria(ArrayList<Elemento> elementos) {
	
		String[] categoria = new String[] {"TORRE","MOUSE","TECLADO","PANTALLA"};
		for(String c:categoria) {
			if(!existeElementoConCategoria(elementos,c)) return false;
		}
		return true;
	}
	
	private boolean existeElementoConCategoria(ArrayList<Elemento> elementos, String categoria) {
	
		for(Elemento e:elementos) {
			if(e.getCategoria().equals(categoria)) return true;
		}
		return false;
	}
	
	/*
	 * @param id - la id de un equipo
	 * @return el equipo al que corresponde la id
	 */
	public Equipo getEquipo(int id) {
		return equipo.getEquipo(id);
	}
	
	/*
	 * @param id - la id de un elemento
	 * @return el elemento al que corresponde el id
	 */
	public Elemento getElemento(int id) {
		return elemento.getElemento(id);
	}
	
	public List<Elemento> getElementos(){
		return elemento.consultarElementos();
	}
	
	/*
	 * @param id - la id del elemento a insertar
	 * @param categoria - la categoria al que corresponde el elemento a ser insertado -TORRE -MOUSE -TECLADO -PANTALLA
	 * @param fabricante - quien elaboro el elemento
	 * @param referencia - corresponde al modelo del equipo
	 * @param fechaAdquisicion corresponde a la fecha de compra del elemento
	 * @param fechaInicioActividad cuando el equipo inicio a ser utilizado
	 * @param fechaFinActivida fecha en que el equipo termina suvida util  
	 */
	public void AgregarElemento(String categoria, String fabricante, String referencia, Date fechaAdquisicion, Date fechaInicioActividad, Date fechaFinActivida) throws ECILabException {
		elemento.AgregarElemento(categoria, fabricante, referencia, fechaAdquisicion, fechaInicioActividad, fechaFinActivida);
	}
	
	/*
	 * @param descripcion - la descripcion del procedimiento que se realizo sobre un equipo
	 * @param justificacion - razon por la cual el se realizo el procedimiento al equipo
	 * @param idEquipo - corresponde al equipo sobre el cual se genero la novedada
	 * @param idElemento - corresponde al elemento sobre el cual se genero la novedad
	 * @param usuario - quien registra esta novedad
	 */
	public void AgregarNovedad(String descripcion,String justificacion,int idEquipo,int idElemento,int usuario) throws ECILabException {
		if(equipo.getEquipo(idEquipo)==null) throw new ECILabException("No existe el equipo.");
		if(elemento.getElemento(idElemento)==null) throw new ECILabException("No existe el Elemento.");
		if(!equipoPosee(equipo.getEquipo(idEquipo),idElemento)) throw new ECILabException("Equipo y elemento no se encuentran vinculados");
		novedad.agregarNovedad(descripcion, justificacion, idEquipo, idElemento,usuario);
	}
	
	private boolean equipoPosee(Equipo equipo2, int idElemento) {
		for(Elemento e:equipo2.getElementos()) {
			if(e.getId()==idElemento) return true;
		}
		return false;
	}

	@Override
	/*
	 * @param id - la id de la novedad a buscar
	 * @return la novedad correspondiente a la id
	 */
	public Novedad getNovedad(int id) throws ECILabException  {
		return novedad.getNovedad(id);
	}
	
	@Override
	public int getUltimaNovedad() {
		return novedad.getUltimaNovedad();
	}
	
	@Override
	/*
	 * Asocia un equipo a un elemento
	 * @param idElemento - la id que corresponde al elemento a asociar al equipo
	 * @param idEquipoN - corresponde a la id del equipo al que se le desea asociar un elemento
	 * @param usuario - corresponde a la id del usuario que aprobo el asociar el elemento a el equipo
	 */
	public void asociarElemento(int idElemento, int IdEquipoN,int usuario) throws ECILabException {
		Elemento e = getElemento(idElemento);
		if(e==null) throw new ECILabException("No existe el elemento a vincular.");
		if(elementoAsociadoaEquipo(idElemento)) throw new ECILabException("Este elemento ya se encuentra vinculado a otro equipo");
		if(e.getFechaFinActividad()!=null) throw new ECILabException("El elemento a sido dado de baja, este no puede ser vinculado a ningun equipo.");
		if(equipo.getEquipo(IdEquipoN)==null) throw new ECILabException("No existe este equipo.");
		elemento.desvincularElementos(e.getCategoria(),equipo.getEquipo(IdEquipoN).getId());
		equipo.asociarElemento(idElemento, IdEquipoN);
		novedad.agregarNovedad("Asociacion elemento "+String.valueOf(idElemento),"completar equipo "+String.valueOf(IdEquipoN), IdEquipoN, idElemento,usuario);
	}
	
	/*
	 * Asocia un elemento a un equipo
	 * @param idElemento - la id del equipo al que se le dea asociar un elemento
	 * @param idEquipo - la id del equipo al que se le desea asociar un elemento
	 */
	
	public void asociarElemento(int idElemento, int IdEquipoN) throws ECILabException {
		Elemento e = getElemento(idElemento);
		if(e==null) throw new ECILabException("No existe el elemento a vincular.");
		if(elementoAsociadoaEquipo(idElemento)) throw new ECILabException("Este elemento ya se encuentra vinculado a otro equipo");
		if(e.getFechaFinActividad()!=null) throw new ECILabException("El elemento a sido dado de baja, este no puede ser vinculado a ningun equipo.");
		Equipo eq =equipo.getEquipo(IdEquipoN); 
		if(eq==null) throw new ECILabException("No existe este equipo.");
		if(eq.getFechaFinActividad()!=null) throw new ECILabException("El equipo fue dado de baja, no se le pueden asociar elementos.");
		elemento.desvincularElementos(e.getCategoria(),equipo.getEquipo(IdEquipoN).getId());
		equipo.asociarElemento(idElemento, IdEquipoN);
	}
	
	/*
	 * @param elemto - corresponde a la id de un un elemento
	 * @return determina si un elemento se encuentra vinculado a un equipo
	 */
	public boolean elementoAsociadoaEquipo(int elemento) {
		return this.elemento.elementoAsociadoaEquipo(elemento);
	}

	@Override
	public void asociarEquipo(int idEquipo, int IdLaboratorioN,int usuario) throws ECILabException {
		Equipo e = getEquipo(idEquipo);
		if(e==null) throw new ECILabException("No existe el equipo a vincular.");
		if(e.getFechaFinActividad()!=null) throw new ECILabException("El equipo a sido dado de baja, este no puede ser vinculado a ningun laboratorio.");
		Laboratorio l = laboratorio.getLaboratorio(IdLaboratorioN); 
		if(l==null) throw new ECILabException("No existe este laboratorio.");
		if(l.getCapacidad()<=l.getEquipos().size()) throw new ECILabException("Se ha llenado la capacidad de esta laboratorio");
		if(l.getFechaCierre()!=null) throw new ECILabException("No se pueden asociar equipos a un laboratorio cerrado");
		equipo.desvincularEquipo(idEquipo);
		laboratorio.asociarEquipo(idEquipo,IdLaboratorioN);
		//novedad.agregarNovedad("Asociacion elemento","completar equipo", IdLaboratorioN, idEquipo,usuario);
	}
	
	public void asociarEquipo(int idEquipo, int IdLaboratorioN) throws ECILabException {
		Equipo e = getEquipo(idEquipo);
		if(e==null) throw new ECILabException("No existe el equipo a vincular.");
		if(e.getFechaFinActividad()!=null) throw new ECILabException("El equipo a sido dado de baja, este no puede ser vinculado a ningun laboratorio.");
		if(laboratorio.getLaboratorio(IdLaboratorioN) == null) throw new ECILabException("No existe este laboratorio.");
		equipo.desvincularEquipo(idEquipo);
		laboratorio.asociarEquipo(idEquipo,IdLaboratorioN);
	}
	
	

	@Override
	public ArrayList<Equipo> getEquipos() {
		return equipo.getEquipos();
	}
	
	@Override
	public List<Laboratorio> getLaboratorios() {
		return laboratorio.getLaboratorios();
	}
	
	@Override
	public void registrarUsuario(int carnet, String nombre, String apellido, String correo, String rol, String contra) {
		usuario.registrarUsuario(carnet,nombre,apellido,correo,rol,contra);
	}
	
	@Override
	public boolean equipoPoseElemento(int elemento) {
		return equipo.equipoPoseElemento(elemento);
	}
	
	@Override
	public void darBajaElemento(int elemento,int usuario) throws ECILabException {
		Elemento elm = this.elemento.getElemento(elemento);
		if(elm==null) throw new ECILabException("El elememento debe existir para poder eliminarlo");
		if(equipoPoseElemento(elemento)) throw new ECILabException("Este equipo debe no estar asociado a algun equipo");
		if(elm.getFechaFinActividad()!=null)  throw new ECILabException("Este elemento ya fue dado de baja.");
		
		this.elemento.darBaja(elemento);
		registrarNovedadSinEquipo("Dar de baja","Tiempo o daño",elemento,usuario);
		
	}
	
	@Override
	public void darBajaConEquipoAsociado(Elemento elemento, Equipo eq) {
		this.elemento.darBaja(elemento.getId());
		this.elemento.desvincularElementos(elemento.getCategoria(), eq.getId());
		registrarNovedadSinEquipo("Dar de baja","Tiempo o daño",elemento.getId(),getUsuario(SecurityUtils.getSubject().getPrincipal().toString()).getId());
	}
	
	@Override
	public void registrarNovedadSinEquipo(String descripcion,String justificacion,int elemento,int usuario){
		novedad.registrarNovedadSinEquipo(descripcion,justificacion,elemento,usuario);
	}

	
	@Override
	public void darBajaEquipo(int equipo,int usuario) throws ECILabException {
		Equipo equi = this.equipo.getEquipo(equipo);
		if(equi==null) throw new ECILabException("El equipo debe existir para poder eliminarlo");
		if(equi.getFechaFinActividad()!=null)  throw new ECILabException("Este equipo ya fue dado de baja.");
		if(equi.getElementos().size() != 0) throw new ECILabException("Debe desasociar o dar de baja a todos los elementos.");
		laboratorio.desasociarEquipo(equipo);
		this.equipo.darBaja(equipo);
		
		
	}

	@Override
	public void desvincularElemento(Elemento e,Equipo eq){
		elemento.desvincularElementos(e.getCategoria(),eq.getId());
	}

	@Override
	public ArrayList<Novedad> getNovedades() {
		return novedad.getNovedades();
	}
	
	public ArrayList<Elemento> getElementos(String categoria) {
		return elemento.getElemento(categoria);
	}
	
	@Override
	public void agregarLaboratorio(int id, String nombre, int capacidad, Date fechacierre,Date fechaapertura) throws ECILabException{
		if(capacidad<=0) throw new ECILabException("La capacidad del laboratorio no puede ser negativa o cero");
		laboratorio.agregarLaboratorio(id, nombre, capacidad, fechacierre,fechaapertura);
	}
	
	public boolean equipoAsociadoaLaboratorio(int equipo) {
		return this.equipo.equipoAsociadoaLaboratorio(equipo);
	}

	public ArrayList<Elemento> getElementosActivos() {
		return elemento.getElementosActivos();
	}

	public Laboratorio getLaboratorio(int laboratorio) {
		return this.laboratorio.getLaboratorio(laboratorio);
	}

	@Override
	public void cerrarLaboratorio(int laboratorio) throws ECILabException {
		Laboratorio l = this.laboratorio.getLaboratorio(laboratorio);
		if(l==null) throw new ECILabException("No existe este laboratorio.");
		if(l.getFechaCierre()!=null) throw new ECILabException("El laboratorio ya se encuentra cerrado.");
		for(Equipo e:l.getEquipos()) {
			this.laboratorio.desasociarEquipo(e.getId());
		}
		this.laboratorio.cerrarLaboratorio(laboratorio);
		
		
	}

	public ArrayList<Equipo> getAllEquipos() {
		return equipo.getAllEquipos();
	}

	public ArrayList<Novedad> novedadesEquipo(int equipo) {
		return novedad.novedadesEquipo(equipo);
	}

	public ArrayList<Novedad> getNovedadesElemento(int elemento) {
		// TODO Auto-generated method stub
		return novedad.getNovedadesElemento(elemento);
	}
	
	public ArrayList<Equipo> getEquiposinLab(){
		return equipo.getEquiposinLab();
	}
	/*
	 * @param equipo - La id del elemento
	 * @param elemento la id del elemeto
	 * @return  todas las novedades correspondientes al elemento y equipo dados
	 */
	public ArrayList<Novedad> getNovedades(int elemento, int equipo) {
		return novedad.getNovedades(elemento,equipo);
	}
	public void agregarNovedadSinElemento(String razon, String justificacion, int equipo, int usuario) {
		novedad.agregarNovedadSinElemento(razon,justificacion,equipo,usuario);
		
	}
	
	@Override
	public List<Laboratorio> getTodosLaboratorios(){
		return laboratorio.getTodosLaboratorios();
	}
	
	@Override
	public int cantidadEquipo( int laboratorio) {
		return this.laboratorio.cantidadEquipo(laboratorio);
	}
	
	@Override
	public ArrayList<Novedad> getNovedadesLabEqui(int labo){
		return novedad.getNovedadesLabEqui(labo);
	}
	
	@Override
	public int ElementosLaboratorio(int laboratorio) {
		return this.laboratorio.ElementosLaboratorio(laboratorio);
	}
	
	@Override
	public int equiposLaboratorios(String mes) {
		return this.laboratorio.equiposLaboratorios(mes);
	}
	public Laboratorio getLaboratorioEquipo(int idEquipo) {
		return laboratorio.getLaboratorioEquipo(idEquipo);
	}
}
