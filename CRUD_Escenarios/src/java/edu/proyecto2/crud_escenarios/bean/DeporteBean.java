/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyecto2.crud_escenarios.bean;

import edu.proyecto2.crud_escenarios.data.Deporte;
import edu.proyecto2.crud_escenarios.data.DeporteJpaController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jose
 */
@ManagedBean
@ViewScoped
public class DeporteBean implements Serializable {
    List<Deporte> deportes= new ArrayList<Deporte>();
    private EntityManagerFactory emf;
    private Deporte selectDe; 
    private String cambio;
    
    @PostConstruct
    public void init() {
        this.cambio="valor";
        
    }
    

    public List<Deporte> getDeportes() {
         emf=Persistence.createEntityManagerFactory("CRUD_EscenariosPU");
         DeporteJpaController ctrl=new DeporteJpaController(emf);
         deportes=ctrl.findDeporteEntities();
         return deportes;
         
    }

    public void setDeportes(List<Deporte> deportes) {
        this.deportes = deportes;
    }
     public void changeDeporte(){
        cambio="valor1";
        System.out.println("Deporte");
    }

    public Deporte getSelectDe() {
        return selectDe;
    }

    public void setSelectDe(Deporte selectDe) {
        this.selectDe = selectDe;
    }

    public String getCambio() {
        return cambio;
    }

    public void setCambio(String cambio) {
        this.cambio = cambio;
    }
    public Deporte getDeport(Integer id) {
        if (id == null){
            throw new IllegalArgumentException("no id provided");
        }
        for (Deporte beer : this.getDeportes()){
            if (id.equals(beer.getIdDeporte())){
                return beer;
            }
        }
        return null;
    }
    
}
