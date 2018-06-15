/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UsuarioNoValidoTest;

import edu.proyecto2.crud_escenarios.bean.UsuarioBean;
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
public class UsuarioNoValidoTest {
    
    UsuarioBean usuarioBean;
    List<Usuario> usuarios;
    List<Usuario> usuariosTest;
    
    public UsuarioNoValidoTest() throws ParseException {
        usuarios = new ArrayList<Usuario>();
        usuariosTest = new ArrayList<Usuario>();
        llenar();
    }
    
    @Before
    public void before() {
        usuarioBean = new UsuarioBean();
    }
    
    @Test
    public void getUsuarios() {
        List<Usuario> usuarios = usuarioBean.getUsuariosNoValidos();
        assertNotNull(usuarios);
        assertTrue(usuarios.get(0).equals(usuariosTest.get(0)));
    }

    private void llenar() throws ParseException{
        Usuario usuario = new Usuario();
        Integer id = new Integer("2");
        DateFormat format = new SimpleDateFormat("YYYY-MM-dd");
        String activacion = "2018-06-04";
        Date fechaActivacion = format.parse(activacion);
        
        usuario.setIdUsuario(id);
        usuario.setCodigo("123456789012");
        usuario.setLogin("usuario1");
        usuario.setPassword("mortadela1");
        usuario.setNombres("albert");
        usuario.setApellidos("Galarga");
        usuario.setEstado("Inactivo");
        usuario.setFechaactivacion(fechaActivacion);
        usuario.setActivadopor("gustavo");  
        usuario.setRol("invitado");
        
        for (int i=1;i<5;i++){
            this.usuariosTest.add(usuario);
        }
    }
}
