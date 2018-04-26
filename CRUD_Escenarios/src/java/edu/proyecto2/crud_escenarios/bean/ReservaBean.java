/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyecto2.crud_escenarios.bean;

import edu.proyecto2.crud_escenarios.data.Deporte;
import edu.proyecto2.crud_escenarios.data.ReservaEspacio;
import edu.proyecto2.crud_escenarios.jpa.EspacioDeportivoJpaController;
import edu.proyecto2.crud_escenarios.jpa.ReservaEspacioJpaController;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
/**
 *
 * @author jose
 */
@ManagedBean
@ViewScoped
public class ReservaBean {

    
    private EntityManagerFactory emf;
    @PostConstruct
    public void init() {
       
    }
    public ReservaBean() {
    }
    public void guardarReserva(ReservaEspacio objReserva){
        System.out.println("Reserva"+objReserva.getFechafin());
        objReserva.setIdReserva(null);
        objReserva.setLogin("Administrador");
        objReserva.setFechahorareg(new Date());
        objReserva.setFechahoramod(new Date());
        objReserva.setModificadopor("Administrador");
        objReserva.setRegistradopor("Administrador");
        emf=Persistence.createEntityManagerFactory("CRUD_EscenariosPU");
        ReservaEspacioJpaController ctrl= new ReservaEspacioJpaController(emf);
       ctrl.create(objReserva);
    }
    public List<ReservaEspacio> getReservaEspacio(int id){
        emf=Persistence.createEntityManagerFactory("CRUD_EscenariosPU");
        ReservaEspacioJpaController ctrl= new ReservaEspacioJpaController(emf);
        List<ReservaEspacio> reservas=ctrl.findReservaEspacioEntities();
        List<ReservaEspacio> reservaEspacio=new ArrayList<ReservaEspacio>();
        for(int i=0;i<reservas.size();i++){
            if(reservas.get(i).getIdEspacio().getIdEspacio()==id){
                reservaEspacio.add(reservas.get(i));
            }
           
        }
        return reservaEspacio;
    }
}
