/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyecto2.crud_escenarios.bean;

import edu.proyecto2.crud_escenarios.data.Deporte;
import edu.proyecto2.crud_escenarios.data.EspacioDeportivo;
import edu.proyecto2.crud_escenarios.jpa.EspacioDeportivoJpaController;

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
enum estados{ CDU,Diamante}

@ManagedBean
@ViewScoped
public class EscenarioBean  implements Serializable{

    public static final String servicio = "En Servicio";
    public static final String mantenimiento = "Mantenimiento";
    
    private List<EspacioDeportivo> list=new ArrayList();
    private Deporte selectDe;
    private EntityManagerFactory emf;
    private List<String> estado= new ArrayList();
    private List<String> ubicacion= new ArrayList();
    private List<Deporte> deportes=new ArrayList();
    private String cambio;
    private EspacioDeportivo create=new EspacioDeportivo();
    @PostConstruct
    public void init() {
        cambio="valor";
    }

    public List<String> getUbicacion() {
        ubicacion.add(servicio);
        ubicacion.add(mantenimiento);
        return ubicacion;
    }

    public void setUbicacion(List<String> ubicacion) {
        this.ubicacion = ubicacion;
    }

    
    public EspacioDeportivo getCreate() {
        return create;
    }

    public void setCreate(EspacioDeportivo create) {
        this.create = create;
    }
    
    
    public String getCambio() {
       
        return cambio;
    }

    public void setCambio(String cambio) {
        this.cambio = cambio;
    }
    
    public Deporte getSelectDe() {
        return selectDe;
    }

    public void setSelectDe(Deporte selectDe) {
        
        this.selectDe = selectDe;
    }
    public void changeDeporte(){
        cambio="valor1";
        
    }

    public List<Deporte> getDeportes() {
        return deportes;
    }

    public void setDeportes(List<Deporte> deportes) {
        this.deportes = deportes;
    }
    
  
    
   
    
    public List<String>  getEstado() {
       estados aux[]=estados.values();
       for(int i=0;i<aux.length;i++){
           estado.add(aux[i].name());
       }
       return estado;
    }

    
    public List<EspacioDeportivo> getList() {
        emf=Persistence.createEntityManagerFactory("CRUD_EscenariosPU");
        EspacioDeportivoJpaController ctrl= new EspacioDeportivoJpaController(emf);
        list = ctrl.findEspacioDeportivoEntities();
        return list;
    }
    public List<EspacioDeportivo> getEspaciosDeportes(Integer idDeporte){
        List<EspacioDeportivo> espacioDeportes=new ArrayList<EspacioDeportivo>();
        emf=Persistence.createEntityManagerFactory("CRUD_EscenariosPU");
        EspacioDeportivoJpaController ctrl= new EspacioDeportivoJpaController(emf);
        list= ctrl.findEspacioDeportivoEntities();
        
        for(int i=0;i<list.size();i++){
            List<Deporte> deportes_1= list.get(i).getDeporteList();
            for(int j=0;j<deportes_1.size();j++){
                if(deportes_1.get(j).getIdDeporte().equals(idDeporte)){
                    espacioDeportes.add(list.get(i));
                 
                }
            }
        }
        return espacioDeportes;
    }
    public void setList(List<EspacioDeportivo> list) {
        this.list = list;
    }
    public String create(){
        return "create";
    }
    public void agregarDeporte(){
        this.deportes.add(selectDe);
        
    }
    public void save(){
        emf=Persistence.createEntityManagerFactory("CRUD_EscenariosPU");
        EspacioDeportivoJpaController ctrl= new EspacioDeportivoJpaController(emf);
        System.out.println("nombre:"+create.getNombre());
        System.out.println("Estado:"+create.getUbicacion());
        System.out.println("Descripcion:"+create.getDescripcion());
        System.out.println("Estado:"+create.getEstado());
        create.setDeporteList(deportes);
        ctrl.create(create);
        
       
    }
    public String confirmar(){
        return "listardeportes";
    }
    public String gestionEscenarios(){
        return "listardeportes";
    }
    
    
}
