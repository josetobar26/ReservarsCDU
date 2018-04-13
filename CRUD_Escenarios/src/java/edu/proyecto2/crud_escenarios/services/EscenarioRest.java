/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.proyecto2.crud_escenarios.services;

import edu.proyecto2.crud_escenarios.bean.DeporteBean;
import edu.proyecto2.crud_escenarios.bean.EscenarioBean;
import edu.proyecto2.crud_escenarios.data.Deporte;
import edu.proyecto2.crud_escenarios.data.EspacioDeportivo;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jose
 */

@Path("Escenario")
public class EscenarioRest {
    private EscenarioBean escenariobean=new EscenarioBean();
    private DeporteBean deportebean=new DeporteBean();
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<EspacioDeportivo> findAllEspaciosdeportivos(){
        return escenariobean.getList(); 
    }
    @GET
    @Path("deportes")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Deporte> findAllDeportes(){
        return deportebean.getDeportes();
       
    }
    @GET
    @Path("EspacioDeporte/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<EspacioDeportivo> findAllEscenariosDeportes(@PathParam("id") int id){
        return escenariobean.getEspaciosDeportes(id);
       
    }
    
    
}
