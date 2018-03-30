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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jose
 */
@Entity
@Table(name = "festivos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Festivos.findAll", query = "SELECT f FROM Festivos f")
    , @NamedQuery(name = "Festivos.findByIdFestivo", query = "SELECT f FROM Festivos f WHERE f.idFestivo = :idFestivo")
    , @NamedQuery(name = "Festivos.findByFecha", query = "SELECT f FROM Festivos f WHERE f.fecha = :fecha")})
public class Festivos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFestivo")
    private Integer idFestivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    public Festivos() {
    }

    public Festivos(Integer idFestivo) {
        this.idFestivo = idFestivo;
    }

    public Festivos(Integer idFestivo, Date fecha) {
        this.idFestivo = idFestivo;
        this.fecha = fecha;
    }

    public Integer getIdFestivo() {
        return idFestivo;
    }

    public void setIdFestivo(Integer idFestivo) {
        this.idFestivo = idFestivo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFestivo != null ? idFestivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Festivos)) {
            return false;
        }
        Festivos other = (Festivos) object;
        if ((this.idFestivo == null && other.idFestivo != null) || (this.idFestivo != null && !this.idFestivo.equals(other.idFestivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.proyecto2.crud_escenarios.data.Festivos[ idFestivo=" + idFestivo + " ]";
    }
    
}
