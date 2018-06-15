/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyecto2.crud_escenarios.bean;

import edu.proyecto2.crud_escenarios.data.Usuario;
import edu.proyecto2.crud_escenarios.jpa.EspacioDeportivoJpaController;
import edu.proyecto2.crud_escenarios.jpa.UsuarioJpaController;
import edu.proyecto2.crud_escenarios.jpa.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jose
 */
@Named(value = "usuarioBean")
@ViewScoped
public class UsuarioBean {

   
    private String cambio; 
    private EntityManagerFactory emf;
    @PostConstruct
    public void init() {
        cambio="valor";
    }
    public List<Usuario> getUsuariosNoValidos(){
        List<Usuario> usuariosNoValidos = new ArrayList<Usuario>();
        emf=Persistence.createEntityManagerFactory("CRUD_EscenariosPU");
        UsuarioJpaController ctrl= new UsuarioJpaController(emf);
        List<Usuario> auxUsuarios=ctrl.findUsuarioEntities();
        for(Usuario objUsuario:auxUsuarios){
                if(objUsuario.getEstado().equals("Registrado")||objUsuario.getEstado().equals("Inactivo")){
                   usuariosNoValidos.add(objUsuario);
                }
        }
        return usuariosNoValidos;
        
    }

    public void edit(Usuario usuarioObj) {
        emf=Persistence.createEntityManagerFactory("CRUD_EscenariosPU");
        UsuarioJpaController ctrl= new UsuarioJpaController(emf);
        System.out.println(usuarioObj.getEstado());
        try {
            ctrl.edit(usuarioObj);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
    }

    
}
