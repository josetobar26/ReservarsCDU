/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.proyecto2.crud_escenarios.services;

import com.google.gson.Gson;
import edu.proyecto2.crud_escenarios.bean.DeporteBean;
import edu.proyecto2.crud_escenarios.bean.EscenarioBean;
import edu.proyecto2.crud_escenarios.bean.ReservaBean;
import edu.proyecto2.crud_escenarios.data.Deporte;
import edu.proyecto2.crud_escenarios.data.EspacioDeportivo;
import edu.proyecto2.crud_escenarios.data.ReservaEspacio;
import edu.proyecto2.crud_escenarios.util.ConverterJson;
import java.io.BufferedReader;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
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
    private ReservaBean reservabean=new ReservaBean();
    private ConverterJson converteJson=new ConverterJson();
    
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
    public String findAllEscenariosDeportes(@PathParam("id") int id){
        
        JSONArray espaciosJson = new JSONArray();
        for(EspacioDeportivo obj:this.escenariobean.getEspaciosDeportes(id)){  
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
            objson.put("deporteList",deportesJson);
            espaciosJson.put(objson);
        }   
        return espaciosJson.toString();   
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
    @POST
    @Path("AgregarReserva")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String guardarReservaEspacio(String reservaJson) {
        System.out.println("-- " + reservaJson);
        final Gson gson = new Gson();
        final ReservaEspacio reservaObj = gson.fromJson(reservaJson, ReservaEspacio.class);
        System.out.println(": " + reservaObj.getNombre());
        System.out.println(": " + reservaObj.getFechaini());
        
        this.reservabean.guardarReserva(reservaObj);
        return reservaJson;
    }
   @PUT
//    @Path("Agregar")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String updateEspacioDeportivo(String espacioJson) {
        final Gson gson = new Gson();
        final EspacioDeportivo espacioObj = gson.fromJson(espacioJson, EspacioDeportivo.class);
        this.escenariobean.edit(espacioObj);
        return "true";
    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String deleteEspacioDeportivo(@PathParam("id") int id) {
        escenariobean.delete(id);
        return "true";
    }

    @GET
    @Path("Reserva/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getReservaEspacio(@PathParam("id") int id){
        System.out.println("Metodo full");
        JSONArray reservasJson = new JSONArray();
         for(ReservaEspacio obj:this.reservabean.getReservaEspacio(id)){  
            JSONObject objson=new JSONObject();
            objson.put("idReserva",obj.getIdReserva());
            objson.put("nombre", obj.getNombre());
            objson.put("fechaini",obj.getFechaini().getTime());
            objson.put("fechafin",obj.getFechafin().getTime());
            objson.put("tipo",obj.getTipo());
            objson.put("esfija",obj.getEsfija());
            objson.put("descripcion",obj.getDescripcion());
            objson.put("idEspacio",this.converteJson.convertirEspacio(obj.getIdEspacio()) );
        
            reservasJson.put(objson);
        }   
         return reservasJson.toString();
    }
    
    
    
    
}
