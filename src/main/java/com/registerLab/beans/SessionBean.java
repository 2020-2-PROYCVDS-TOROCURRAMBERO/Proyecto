package com.registerLab.beans;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;

import com.google.inject.Injector;
import com.registerLab.entities.Elemento;
import com.registerLab.entities.Laboratorio;
import com.registerLab.entities.Usuario;
import com.registerLab.servicios.ServiciosECILabImpl;

@ManagedBean(name="sesBean")
@SessionScoped
public class SessionBean extends BaseBeanRegisterLab{
	private String email;
	private String password;
	private Usuario user;
	private Injector injector;
	private ServiciosECILabImpl servicios;
	
	public SessionBean() {
		injector = super.getInjector();
		servicios = injector.getInstance(ServiciosECILabImpl.class);
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		try {
			if(SecurityUtils.getSubject().getPrincipal()!=null) FacesContext.getCurrentInstance().getExternalContext().redirect("useradmin.xhtml");
		} catch (IOException e) {
		}
		return email;
	}
	public String getPassword() {
		return password;
	}
	public void login() {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			String hex = new Sha256Hash(password).toHex();
			System.out.println(hex);
			UsernamePasswordToken token = new UsernamePasswordToken(email, hex);
			token.setRememberMe(true);
			currentUser.login(token);
			FacesContext.getCurrentInstance().getExternalContext().redirect("useradmin.xhtml");
			if(user==null) user = servicios.getUsuario(SecurityUtils.getSubject().getPrincipal().toString());
		}
			catch(Exception e) {
		        FacesContext context = FacesContext.getCurrentInstance();
		        context.addMessage(null, new FacesMessage("¡No se ha podido iniciar sesion. Por favor verifique sus credenciales e intentelo nuevamente.","Por favor Revise sus credenciales.") );

			}
		}
		public Usuario getUsuario(){
			try {
				if(SecurityUtils.getSubject().getPrincipal()==null) FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");

			}catch(Exception e) {
			}
			if(user==null && SecurityUtils.getSubject().getPrincipal()!=null) user = servicios.getUsuario(SecurityUtils.getSubject().getPrincipal().toString());
			return user;
		}
		
		public void logout() {
			try {
				SecurityUtils.getSubject().logout();
				user =null;
				FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void historialElemento() {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("historialElemento.xhtml");
			} catch (Exception e) {
			}	
		}
		public List<Elemento> getHistorialElementos(){
			try{
				List<Elemento> el = servicios.getElementos();
				return el;
			}catch(Exception e){
				return null;
			}
		}
		public List<Laboratorio> getHistorialLaboratorios(){
			try {
				List<Laboratorio> labo = servicios.getLaboratorios();
				return labo;
			}
			catch(Exception e){
				return null;
			}
		}
		
		public void registrarEquipo() {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("registreEquipo.xhtml");
			} catch (Exception e) {
				e.printStackTrace();
		
			}
		}
		
		public void asociarElemento() {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("asociarElemento.xhtml");
			} catch (Exception e) {
				e.printStackTrace();
		
			}
		}
		
		public void asociarEquipo() {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("asociarEquipo.xhtml");
			} catch (Exception e) {
				e.printStackTrace();
		
			}
		}
		
		public void registrarEquipo( int id, Date fechainicioactividad, Date fechafinactividad , Date fechaadquisicion) {
			try{
				
				servicios.insertarEquipoSinLaboratorio(id, fechainicioactividad, fechafinactividad, fechaadquisicion);
				
			}catch(Exception e){
				
			}
		}
		
		public void registrarElemento() {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("registreElemento.xhtml");
			} catch (Exception e) {
				
			}
		}
		
		public void registrarLaboratorio() {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("registrarLaboratorio.xhtml");
			} catch (Exception e) {
				
			}
		}
		
		public void registrarElemento(String categoria, String fabricante, String referecia,Date fechaadquisicion,Date fechainicioactividad,Date fechafinactividad) {
			try {
				
				servicios.AgregarElemento(categoria, fabricante, referecia, fechaadquisicion, fechainicioactividad, fechafinactividad);
				
			} catch (Exception e) {
				
			}
		}
		
		public void regrese() {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}