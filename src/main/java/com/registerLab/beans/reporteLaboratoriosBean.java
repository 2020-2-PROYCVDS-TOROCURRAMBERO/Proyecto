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
import com.registerLab.entities.Laboratorio;
import com.registerLab.entities.Novedad;
import com.registerLab.servicios.ServiciosECILabImpl;

@ManagedBean(name="reportLabBean")
@RequestScoped

public class reporteLaboratoriosBean extends BaseBeanRegisterLab {
	private Injector injector;
	private ServiciosECILabImpl servicios;
	private int laboratorio;
	private DonutChartModel donutModel;
	private BarChartModel barModel;
	
	@PostConstruct
    public void init() {
        createDonutModel();
        createBarModel();
    }
	
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
	
		
	public void createDonutModel() {
        donutModel = new DonutChartModel();
        ChartData data = new ChartData();
         
        DonutChartDataSet dataSet = new DonutChartDataSet();
        
        List<Number> values = new ArrayList<>();
        for (int i=0; i < getTotalLaboratorios().size(); i++) {
        	values.add(servicios.ElementosLaboratorio(getTotalLaboratorios().get(i).getId()));       	
        }
        dataSet.setData(values);
         
        List<String> bgColors = new ArrayList<>();
        for (int i=0; i < values.size(); i++) {
        	int numero = (int) (Math.random() * 255) + 1;
        	int numero2 = (int) (Math.random() * 255) + 1;
        	int numero3 = (int) (Math.random() * 255) + 1;
        	if (numero != numero2 && numero2 != numero3 && numero3 != numero) {
        		if (numero >=25 && numero2 >=25 && numero3 >=25) {
        			
        			bgColors.add("rgb("+numero+","+numero2+","+numero3+")");
        		}
        	}
        	
        }
        dataSet.setBackgroundColor(bgColors);
         
        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        for(int i=0; i< values.size();i++) {
        	labels.add(getTotalLaboratorios().get(i).getNombre());
        }
        data.setLabels(labels);
        donutModel.setData(data);
    }
	
	public DonutChartModel getDonutModel() {
	        return donutModel;
	    }
	 
    public void setDonutModel(DonutChartModel donutModel) {
        this.donutModel = donutModel;
    }
    
    public BarChartModel getBarModel() {
        return barModel;
    }
 
    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }
    
    public void createBarModel() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();
         
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Estadistica");
         
        List<Number> values = new ArrayList<>();
        values.add(servicios.equiposLaboratorios("01"));
        values.add(servicios.equiposLaboratorios("02"));
        values.add(servicios.equiposLaboratorios("03"));
        values.add(servicios.equiposLaboratorios("04"));
        values.add(servicios.equiposLaboratorios("05"));
        values.add(servicios.equiposLaboratorios("06"));
        values.add(servicios.equiposLaboratorios("07"));
        values.add(servicios.equiposLaboratorios("08"));
        values.add(servicios.equiposLaboratorios("09"));
        values.add(servicios.equiposLaboratorios("10"));
        values.add(servicios.equiposLaboratorios("11"));
        values.add(servicios.equiposLaboratorios("12"));
        barDataSet.setData(values);
         
        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        bgColor.add("rgba(255, 192, 102,0.2)");
        bgColor.add("rgba(153, 102, 255,0.2)");
        bgColor.add("rgba(255, 199, 132,0.2)");
        bgColor.add("rgba(155, 99, 132,0.2)");
        bgColor.add("rgba(255, 99, 12,0.2)");
        barDataSet.setBackgroundColor(bgColor);
         
        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        borderColor.add("rgb(255, 192, 102)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(255, 199, 132)");
        borderColor.add("rgb(155, 99, 132)");
        borderColor.add("rgb(255, 99, 12)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);
         
        data.addChartDataSet(barDataSet);
         
        List<String> labels = new ArrayList<>();
        labels.add("Enero");
        labels.add("Febrero");
        labels.add("Marzo");
        labels.add("Abril");
        labels.add("Mayo");
        labels.add("Junio");
        labels.add("Julio");
        labels.add("Agosto");
        labels.add("Septiembre");
        labels.add("Octubre");
        labels.add("Noviembre");
        labels.add("Diciembre");
        data.setLabels(labels);
        barModel.setData(data);
         
        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);
         
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Bar Chart");
        options.setTitle(title);
 
        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);
 
        barModel.setOptions(options);
    }


    

}
