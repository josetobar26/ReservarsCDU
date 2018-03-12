/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyecto2.crud_escenarios.data;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jose
 */
@Entity
@Table(name = "deporte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Deporte.findAll", query = "SELECT d FROM Deporte d")
    , @NamedQuery(name = "Deporte.findByIdDeporte", query = "SELECT d FROM Deporte d WHERE d.idDeporte = :idDeporte")
    , @NamedQuery(name = "Deporte.findByNombre", query = "SELECT d FROM Deporte d WHERE d.nombre = :nombre")})
public class Deporte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDeporte")
    private Integer idDeporte;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @ManyToMany(mappedBy = "deporteList")
    private List<EspacioDeportivo> espacioDeportivoList;

    public Deporte() {
    }

    public Deporte(Integer idDeporte) {
        this.idDeporte = idDeporte;
    }

    public Deporte(Integer idDeporte, String nombre) {
        this.idDeporte = idDeporte;
        this.nombre = nombre;
    }

    public Integer getIdDeporte() {
        return idDeporte;
    }

    public void setIdDeporte(Integer idDeporte) {
        this.idDeporte = idDeporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<EspacioDeportivo> getEspacioDeportivoList() {
        return espacioDeportivoList;
    }

    public void setEspacioDeportivoList(List<EspacioDeportivo> espacioDeportivoList) {
        this.espacioDeportivoList = espacioDeportivoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDeporte != null ? idDeporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Deporte)) {
            return false;
        }
        Deporte other = (Deporte) object;
        if ((this.idDeporte == null && other.idDeporte != null) || (this.idDeporte != null && !this.idDeporte.equals(other.idDeporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.proyecto2.crud_escenarios.data.Deporte[ idDeporte=" + idDeporte + " ]";
    }
    
}
