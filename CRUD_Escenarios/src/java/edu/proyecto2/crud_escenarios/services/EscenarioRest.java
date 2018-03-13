/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyecto2.crud_escenarios.services;

import edu.proyecto2.crud_escenarios.bean.EscenarioBean;
import edu.proyecto2.crud_escenarios.data.EspacioDeportivo;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jose
 */

@Path("Escenario")
public class EscenarioRest {
    private EscenarioBean escenariobean=new EscenarioBean();
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<EspacioDeportivo> findAllEspaciosdeportivos(){
        return escenariobean.getList(); 
    }
    
    
}