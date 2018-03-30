/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyecto2.crud_escenarios.data;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jose
 */
@Entity
@Table(name = "espacio_deportivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EspacioDeportivo.findAll", query = "SELECT e FROM EspacioDeportivo e")
    , @NamedQuery(name = "EspacioDeportivo.findByIdEspacio", query = "SELECT e FROM EspacioDeportivo e WHERE e.idEspacio = :idEspacio")
    , @NamedQuery(name = "EspacioDeportivo.findByNombre", query = "SELECT e FROM EspacioDeportivo e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "EspacioDeportivo.findByUbicacion", query = "SELECT e FROM EspacioDeportivo e WHERE e.ubicacion = :ubicacion")
    , @NamedQuery(name = "EspacioDeportivo.findByDescripcion", query = "SELECT e FROM EspacioDeportivo e WHERE e.descripcion = :descripcion")
    , @NamedQuery(name = "EspacioDeportivo.findByEstado", query = "SELECT e FROM EspacioDeportivo e WHERE e.estado = :estado")
    , @NamedQuery(name = "EspacioDeportivo.findByTipofoto", query = "SELECT e FROM EspacioDeportivo e WHERE e.tipofoto = :tipofoto")})
public class EspacioDeportivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEspacio")
    private Integer idEspacio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "ubicacion")
    private String ubicacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "foto")
    private byte[] foto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "tipofoto")
    private String tipofoto;
    @JoinTable(name = "espacio_deporte", joinColumns = {
        @JoinColumn(name = "idEspacio", referencedColumnName = "idEspacio")}, inverseJoinColumns = {
        @JoinColumn(name = "idDeporte", referencedColumnName = "idDeporte")})
    @ManyToMany
    private List<Deporte> deporteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEspacio")
    private List<ReservaEspacio> reservaEspacioList;

    public EspacioDeportivo() {
    }

    public EspacioDeportivo(Integer idEspacio) {
        this.idEspacio = idEspacio;
    }

    public EspacioDeportivo(Integer idEspacio, String nombre, String ubicacion, String descripcion, String estado, byte[] foto, String tipofoto) {
        this.idEspacio = idEspacio;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.estado = estado;
        this.foto = foto;
        this.tipofoto = tipofoto;
    }

    public Integer getIdEspacio() {
        return idEspacio;
    }

    public void setIdEspacio(Integer idEspacio) {
        this.idEspacio = idEspacio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getTipofoto() {
        return tipofoto;
    }

    public void setTipofoto(String tipofoto) {
        this.tipofoto = tipofoto;
    }

    @XmlTransient
    public List<Deporte> getDeporteList() {
        return deporteList;
    }

    public void setDeporteList(List<Deporte> deporteList) {
        this.deporteList = deporteList;
    }

    @XmlTransient
    public List<ReservaEspacio> getReservaEspacioList() {
        return reservaEspacioList;
    }

    public void setReservaEspacioList(List<ReservaEspacio> reservaEspacioList) {
        this.reservaEspacioList = reservaEspacioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEspacio != null ? idEspacio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EspacioDeportivo)) {
            return false;
        }
        EspacioDeportivo other = (EspacioDeportivo) object;
        if ((this.idEspacio == null && other.idEspacio != null) || (this.idEspacio != null && !this.idEspacio.equals(other.idEspacio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.proyecto2.crud_escenarios.data.EspacioDeportivo[ idEspacio=" + idEspacio + " ]";
    }
    
}
