/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReservaTest;

import edu.proyecto2.crud_escenarios.bean.ReservaBean;
import edu.proyecto2.crud_escenarios.data.EspacioDeportivo;
import edu.proyecto2.crud_escenarios.data.ReservaEspacio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author USUARIO
 */
public class ReservaTest {
    
    ReservaBean reservaBean;
    List<ReservaEspacio> reservas;
    List<ReservaEspacio> reservasTest;
    
    
    public ReservaTest() {
        reservas = new ArrayList<ReservaEspacio>();
        reservasTest = new ArrayList<ReservaEspacio>();
        llenar();
    }
    
    @Before
    public void before() {
        reservaBean = new ReservaBean();
    }
    
    @Test
    public void testGetReserva() {
        List<ReservaEspacio> reservas = reservaBean.getReservaEspacio(1);
        assertNotNull(reservas);
        assertTrue(reservas.get(0).equals(reservasTest.get(0)));
    }
    
    private void llenar(){
        Date fechafin = new Date();
        Date fechahorareg = new Date();
        Date fechaini = new Date();
        Integer idReserva = new Integer(1);
        EspacioDeportivo idEspacio = new EspacioDeportivo();
        
        ReservaEspacio reserva = new ReservaEspacio();
        reserva.setDescripcion("Entreno");
        reserva.setEsfija(true);
        reserva.setFechafin(fechafin);
        reserva.setFechahorareg(fechahorareg);
        reserva.setFechaini(fechaini);
        reserva.setIdEspacio(idEspacio);
        reserva.setIdReserva(idReserva);
        reserva.setLogin("mortadela1");
        reserva.setModificadopor("Pantoja");
        reserva.setNombre("Pantoja");
        reserva.setRegistradopor("Pantoja");
        reserva.setTipo("tipo");
        
        for (int i=1;i<5;i++){
            this.reservasTest.add(reserva);
        }
    }

}
