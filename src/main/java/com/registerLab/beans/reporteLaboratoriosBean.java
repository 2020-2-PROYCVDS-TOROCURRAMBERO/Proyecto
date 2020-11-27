package com.registerLab.beans;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import com.google.inject.Injector;
import com.registerLab.entities.Elemento;
import com.registerLab.entities.Laboratorio;
import com.registerLab.entities.Novedad;
import com.registerLab.servicios.ServiciosECILabImpl;
@ManagedBean(name="reportLabBean")
@RequestScoped
public class reporteLaboratoriosBean extends BaseBeanRegisterLab {
	private Injector injector;
	private ServiciosECILabImpl servicios;
	private int laboratorio;
	
	public reporteLaboratoriosBean() {
		injector = super.getInjector();
		servicios = injector.getInstance(ServiciosECILabImpl.class);
	}
	public List<Laboratorio> getTotalLaboratorios(){
		try{
			List<Laboratorio> el = servicios.getLaboratorios();
			return el;
		}catch(Exception e){
			return null;
		}
	}
	public int getLaboratorio() {
		return laboratorio;
	}
	public void setLaboratorio(int laboratorio) {
		this.laboratorio = laboratorio;
	} 
	public Laboratorio getLaboratorio(int laboratorio) {
		return servicios.getLaboratorio(laboratorio);
	}
	
	public int cantidaEquipos(Laboratorio laboratorio) {
		return servicios.cantidadEquipo(laboratorio.getId());
	}
	public String estadoLaboratorio() {
		if (servicios.getLaboratorio(laboratorio).getFechaCierre() == null) {
			return "Laboratorio Activo";
		}
		else {
			return "Laboratorio Inactivo";
		}	
	}
	public String NombreLaboratorio() {
		return getLaboratorio(this.laboratorio).getNombre();
	}
	public ArrayList<Novedad> getNovedadesRelacionadas(int labo){
		return servicios.getNovedadesLabEqui(labo);
	}
}