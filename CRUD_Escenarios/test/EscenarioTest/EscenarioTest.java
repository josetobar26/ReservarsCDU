/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EscenarioTest;

import edu.proyecto2.crud_escenarios.bean.DeporteBean;
import edu.proyecto2.crud_escenarios.bean.EscenarioBean;
import edu.proyecto2.crud_escenarios.data.EspacioDeportivo;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author USUARIO
 */
public class EscenarioTest {
    
    EscenarioBean escenarioBean;
    List<EspacioDeportivo> escenarios;
    List<EspacioDeportivo> escenariosTest;
    
    public EscenarioTest() {
        escenarios = new ArrayList<EspacioDeportivo>();
        escenariosTest = new ArrayList<EspacioDeportivo>();
        llenar();
    }
    
    @Before
    public void before() {
        escenarioBean = new EscenarioBean();
    }
   
    @Test
    public void getEspaciosDeportes() {
        List<EspacioDeportivo> escenarios = escenarioBean.getEspaciosDeportes(1);
        assertNotNull(escenarios);
        assertTrue(escenarios.get(0).equals(escenariosTest.get(0)));
    }
    
    private void llenar(){
        EspacioDeportivo escenario = new EspacioDeportivo();
        escenario.setIdEspacio(1);
        escenario.setNombre("Sintetica");
        escenario.setDescripcion("Cancha para practicar");
        escenario.setEstado("Mantenimiento");
        escenario.setTipofoto("jpeg");
        //escenario.setDeporteList(deporteList);
        escenario.setUbicacion("CDU");
        //escenario.setFoto(foto);
        //escenario.setReservaEspacioList(reservaEspacioList);
        
        
        for (int i=1;i<5;i++){
            this.escenariosTest.add(escenario);
        }
    }
}
