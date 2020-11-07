package registerEciLabInfo;

import org.junit.Before;
import org.mybatis.guice.XMLMyBatisModule;

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


public class TestBase {
	protected Injector injector = Guice.createInjector(new XMLMyBatisModule() {
        @Override
        protected void initialize() {
            setEnvironmentId("test");
            setClassPathResource("mybatis-config-h2.xml");
            bind(ServiciosECILab.class).to(ServiciosECILabImpl.class);
            bind(UsuarioDAO.class).to(MyBatisUsuarioDAO.class);
            bind(ElementoDAO.class).to(MyBatisElementoDAO.class);
            bind(LaboratorioDAO.class).to(MyBatisLaboratorioDAO.class);
            bind(EquipoDAO.class).to(MyBatisEquipoDAO.class);
            bind(NovedadDAO.class).to(MyBatisNovedadDAO.class);
        }
});

	@Before
	public void setup() {
		injector.injectMembers(this);
	}
}
