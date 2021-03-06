/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReservaTest;

import edu.proyecto2.crud_escenarios.bean.ReservaBean;
import edu.proyecto2.crud_escenarios.data.EspacioDeportivo;
import edu.proyecto2.crud_escenarios.data.ReservaEspacio;
import edu.proyecto2.crud_escenarios.data.Usuario;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    
    
    public ReservaTest() throws ParseException {
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
    
    private void llenar() throws ParseException{
        DateFormat format = new SimpleDateFormat("YYYY-MM-dd");
        DateFormat formatHora = new SimpleDateFormat("YYYY-MM-dd, HH:mm:ss");
        Integer idReserva = new Integer(1);
        EspacioDeportivo idEspacio = new EspacioDeportivo();
        Integer id = new Integer("2");
        Usuario usuario = new Usuario(id);
        
        String inicio = "2018-05-10";
        Date fechaIni = format.parse(inicio);
        
        String fin = "2018-05-21";
        Date fechaFin = format.parse(fin);
        
        String registro = "2018-05-21, 12:35:30";
        Date fechaHoraReg = formatHora.parse(registro);
        
        ReservaEspacio reserva = new ReservaEspacio();
        reserva.setDescripcion("Entreno");
        reserva.setEsfija(true);
        reserva.setFechafin(fechaFin);
        reserva.setFechahorareg(fechaHoraReg);
        reserva.setFechaini(fechaIni);
        reserva.setIdEspacio(idEspacio);
        reserva.setIdUsuario(usuario);
        reserva.setIdReserva(idReserva);        
        reserva.setModificadopor("Pantoja");
        reserva.setNombre("Pantoja");
        reserva.setRegistradopor("Pantoja");
        reserva.setTipo("tipo");
        
        for (int i=1;i<5;i++){
            this.reservasTest.add(reserva);
        }
    }

}
