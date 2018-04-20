/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeporteTest;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import edu.proyecto2.crud_escenarios.bean.DeporteBean;
import edu.proyecto2.crud_escenarios.data.Deporte;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author USUARIO
 */
public class DeporteTest {
    
    DeporteBean deporteBean; 
    List<Deporte> deportes;
    List<Deporte> deportesTest;
    
    public DeporteTest() {
        deportes = new ArrayList<Deporte>();
        deportesTest = new ArrayList<Deporte>();
        llenar();
    }
    
    @Before
    public void before() {
        deporteBean = new DeporteBean();
    }
    
    @Test
    public void testGetDeportes() {
        List<Deporte> deportes = deporteBean.getDeportes();
        assertNotNull(deportes);
        assertTrue(deportes.get(0).equals(deportesTest.get(0)));
    }
    
    private void llenar(){
        Deporte deporte = new Deporte();
        deporte.setIdDeporte(1);
        deporte.setNombre("deporte");
       
        for (int i=1;i<5;i++){
            this.deportesTest.add(deporte);
        }
    }
    
}