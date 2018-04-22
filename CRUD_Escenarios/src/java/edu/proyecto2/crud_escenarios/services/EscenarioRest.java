/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.proyecto2.crud_escenarios.services;

import com.google.gson.Gson;
import edu.proyecto2.crud_escenarios.bean.DeporteBean;
import edu.proyecto2.crud_escenarios.bean.EscenarioBean;
import edu.proyecto2.crud_escenarios.data.Deporte;
import edu.proyecto2.crud_escenarios.data.EspacioDeportivo;
import java.io.BufferedReader;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import javax.json.JsonObject;
import javax.json.JsonReader;   
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
    public String findAllEspaciosdeportivos(){
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
         JSONObject enviar=new JSONObject();
        JSONArray espaciosJson = new JSONArray();
        for(EspacioDeportivo obj:escenariobean.getList()){
            JSONObject objson=new JSONObject();
            objson.put("idEspacio",obj.getIdEspacio());
            objson.put("nombre", obj.getNombre());
            objson.put("ubicacion",obj.getUbicacion());
            objson.put("estado",obj.getEstado());
            objson.put("descripcion",obj.getDescripcion());
            objson.put("foto",obj.getFoto());
            objson.put("tipofoto",obj.getTipofoto());
            JSONArray deportesJson = new JSONArray();
            for(Deporte objDeporte:obj.getDeporteList()){
                JSONObject objDepJson=new JSONObject();
                objDepJson.put("idDeporte",objDeporte.getIdDeporte());
                objDepJson.put("nombre", objDeporte.getNombre());
                deportesJson.put(objDepJson);
            }
            objson.put("deporteList", deportesJson);
            espaciosJson.put(objson);
        }
       
        //enviar.put("espacios", espaciosJson);
        System.out.println("Resturn"+escenariobean.getList().get(5).getDeporteList().get(0).getNombre());
        return espaciosJson.toString();
        //return escenariobean.getList(); 
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
    
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String createEspacioDeportivo(String espacioJson) {
        System.out.println("-- " + espacioJson);
        final Gson gson = new Gson();
        final EspacioDeportivo espacioObj = gson.fromJson(espacioJson, EspacioDeportivo.class);
        System.out.println(": " + espacioObj.getNombre());
        System.out.println(": " + espacioObj.getDeporteList());
        this.escenariobean.save(espacioObj);
        return espacioJson;
    }
    
    
    
    
}
