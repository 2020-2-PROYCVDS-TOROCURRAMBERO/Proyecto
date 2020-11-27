package com.registerLab.guice;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.registerLab.DAO.ElementoDAO;
import com.registerLab.DAO.EquipoDAO;
import com.registerLab.DAO.LaboratorioDAO;
import com.registerLab.DAO.NovedadDAO;
import com.registerLab.DAO.UsuarioDAO;
import com.registerLab.myBatisDAO.MyBatisElementoDAO;
import com.registerLab.myBatisDAO.MyBatisEquipoDAO;
import com.registerLab.myBatisDAO.MyBatisLaboratorioDAO;
import com.registerLab.myBatisDAO.MyBatisNovedadDAO;
import com.registerLab.myBatisDAO.MyBatisUsuarioDAO;
import com.registerLab.servicios.ServiciosECILab;
import com.registerLab.servicios.ServiciosECILabImpl;
public class GuiceContextListener implements ServletContextListener {
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.removeAttribute(Injector.class.getName());
    }
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Injector injector = Guice.createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                install(JdbcHelper.PostgreSQL);
                setEnvironmentId("development");
                setClassPathResource("mybatis-config.xml");
                bind(ServiciosECILab.class).to(ServiciosECILabImpl.class);
                bind(UsuarioDAO.class).to(MyBatisUsuarioDAO.class);
                bind(ElementoDAO.class).to(MyBatisElementoDAO.class);
                bind(LaboratorioDAO.class).to(MyBatisLaboratorioDAO.class);
                bind(EquipoDAO.class).to(MyBatisEquipoDAO.class);
                bind(NovedadDAO.class).to(MyBatisNovedadDAO.class);
            }
        });
        servletContextEvent.getServletContext().setAttribute(Injector.class.getName(), injector);
    }
}