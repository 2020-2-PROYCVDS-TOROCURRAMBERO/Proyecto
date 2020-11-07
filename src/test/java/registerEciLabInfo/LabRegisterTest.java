package registerEciLabInfo;

import static org.junit.Assert.assertTrue;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.Generate.*;
import static org.quicktheories.generators.SourceDSL.*;

import org.junit.Before;
import org.junit.Test;
import java.sql.Date;


import com.google.inject.Inject;
import com.registerLab.ECILabException;
import com.registerLab.entities.Elemento;
import com.registerLab.entities.Equipo;
import com.registerLab.servicios.ServiciosECILabImpl;

public class LabRegisterTest extends TestBase{
	@Inject
	private ServiciosECILabImpl lab;
	@Before
	public void setUp() {
		if(lab.getUsuario("juan@escuelaing.edu.co")==null) {
			try {
				lab.registrarUsuario(1, "juan","pal", "juan@escuelaing.edu.co", "ad", "cl");
				lab.AgregarElemento(1, "TORRE", "LENOVO", "IDEA", new Date(01, 02, 2019),null,new Date(02,02,2019));
				lab.insertarEquipoSinLaboratorio(1, new Date(2, 3, 2019),null,null);
			} catch (ECILabException e) {
				
			}
		}
	}
	@Test
	public void siUnEquipoNoExisteDeberiaPoderRegistrarlo() {
		
		qt().forAll(integers().between(0, 1000)).check(id->{
			try {
				lab.insertarEquipoSinLaboratorio(id, new Date(02,02,2017),null, new Date(01,02,2017));
				//System.out.println("Salio");
				Equipo eq = lab.getEquipo(id);
				return eq.getId()==id;
			}catch(ECILabException e) {
				return lab.getEquipo(id)!=null;
			}
		});
	}

}
