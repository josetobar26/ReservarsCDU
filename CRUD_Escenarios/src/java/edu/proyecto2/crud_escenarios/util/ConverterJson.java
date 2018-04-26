/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyecto2.crud_escenarios.util;

import edu.proyecto2.crud_escenarios.data.Deporte;
import edu.proyecto2.crud_escenarios.data.EspacioDeportivo;
import java.util.List;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

/**
 *
 * @author jose
 */
public class ConverterJson {
    
    public ConverterJson(){}
    
    public String convertirEspacioDeportivo(List<EspacioDeportivo> espacios){
        JSONArray espaciosJson = new JSONArray();
        for(EspacioDeportivo obj:espacios){  
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
    public JSONObject convertirEspacio(EspacioDeportivo obj){
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
            return objson;
    }
}
