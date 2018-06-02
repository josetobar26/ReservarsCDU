/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyecto2.crud_escenarios.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jose
 */
@Entity
@Table(name = "reserva_espacio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReservaEspacio.findAll", query = "SELECT r FROM ReservaEspacio r")
    , @NamedQuery(name = "ReservaEspacio.findByIdReserva", query = "SELECT r FROM ReservaEspacio r WHERE r.idReserva = :idReserva")
    , @NamedQuery(name = "ReservaEspacio.findByNombre", query = "SELECT r FROM ReservaEspacio r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "ReservaEspacio.findByFechaini", query = "SELECT r FROM ReservaEspacio r WHERE r.fechaini = :fechaini")
    , @NamedQuery(name = "ReservaEspacio.findByFechafin", query = "SELECT r FROM ReservaEspacio r WHERE r.fechafin = :fechafin")
    , @NamedQuery(name = "ReservaEspacio.findByTipo", query = "SELECT r FROM ReservaEspacio r WHERE r.tipo = :tipo")
    , @NamedQuery(name = "ReservaEspacio.findByEsfija", query = "SELECT r FROM ReservaEspacio r WHERE r.esfija = :esfija")
    , @NamedQuery(name = "ReservaEspacio.findByDescripcion", query = "SELECT r FROM ReservaEspacio r WHERE r.descripcion = :descripcion")
    , @NamedQuery(name = "ReservaEspacio.findByRegistradopor", query = "SELECT r FROM ReservaEspacio r WHERE r.registradopor = :registradopor")
    , @NamedQuery(name = "ReservaEspacio.findByModificadopor", query = "SELECT r FROM ReservaEspacio r WHERE r.modificadopor = :modificadopor")
    , @NamedQuery(name = "ReservaEspacio.findByFechahorareg", query = "SELECT r FROM ReservaEspacio r WHERE r.fechahorareg = :fechahorareg")
    , @NamedQuery(name = "ReservaEspacio.findByFechahoramod", query = "SELECT r FROM ReservaEspacio r WHERE r.fechahoramod = :fechahoramod")})
public class ReservaEspacio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idReserva")
    private Integer idReserva;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaini")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaini;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechafin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechafin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "esfija")
    private boolean esfija;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "registradopor")
    private String registradopor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "modificadopor")
    private String modificadopor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechahorareg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahorareg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechahoramod")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahoramod;
    @JoinColumn(name = "idEspacio", referencedColumnName = "idEspacio")
    @ManyToOne(optional = false)
    private EspacioDeportivo idEspacio;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public ReservaEspacio() {
    }

    public ReservaEspacio(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public ReservaEspacio(Integer idReserva, String nombre, Date fechaini, Date fechafin, String tipo, boolean esfija, String descripcion, String registradopor, String modificadopor, Date fechahorareg, Date fechahoramod) {
        this.idReserva = idReserva;
        this.nombre = nombre;
        this.fechaini = fechaini;
        this.fechafin = fechafin;
        this.tipo = tipo;
        this.esfija = esfija;
        this.descripcion = descripcion;
        this.registradopor = registradopor;
        this.modificadopor = modificadopor;
        this.fechahorareg = fechahorareg;
        this.fechahoramod = fechahoramod;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaini() {
        return fechaini;
    }

    public void setFechaini(Date fechaini) {
        this.fechaini = fechaini;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean getEsfija() {
        return esfija;
    }

    public void setEsfija(boolean esfija) {
        this.esfija = esfija;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRegistradopor() {
        return registradopor;
    }

    public void setRegistradopor(String registradopor) {
        this.registradopor = registradopor;
    }

    public String getModificadopor() {
        return modificadopor;
    }

    public void setModificadopor(String modificadopor) {
        this.modificadopor = modificadopor;
    }

    public Date getFechahorareg() {
        return fechahorareg;
    }

    public void setFechahorareg(Date fechahorareg) {
        this.fechahorareg = fechahorareg;
    }

    public Date getFechahoramod() {
        return fechahoramod;
    }

    public void setFechahoramod(Date fechahoramod) {
        this.fechahoramod = fechahoramod;
    }

    public EspacioDeportivo getIdEspacio() {
        return idEspacio;
    }

    public void setIdEspacio(EspacioDeportivo idEspacio) {
        this.idEspacio = idEspacio;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReserva != null ? idReserva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservaEspacio)) {
            return false;
        }
        ReservaEspacio other = (ReservaEspacio) object;
        if ((this.idReserva == null && other.idReserva != null) || (this.idReserva != null && !this.idReserva.equals(other.idReserva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.proyecto2.crud_escenarios.data.ReservaEspacio[ idReserva=" + idReserva + " ]";
    }
    
}
