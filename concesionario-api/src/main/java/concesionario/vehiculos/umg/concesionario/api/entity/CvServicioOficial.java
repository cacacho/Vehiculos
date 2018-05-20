/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concesionario.vehiculos.umg.concesionario.api.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "CV_SERVICIO_OFICIAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CvServicioOficial.findAll", query = "SELECT c FROM CvServicioOficial c")
    , @NamedQuery(name = "CvServicioOficial.findByIdServicioOficial", query = "SELECT c FROM CvServicioOficial c WHERE c.idServicioOficial = :idServicioOficial")
    , @NamedQuery(name = "CvServicioOficial.findByNif", query = "SELECT c FROM CvServicioOficial c WHERE c.nif = :nif")
    , @NamedQuery(name = "CvServicioOficial.findByNombre", query = "SELECT c FROM CvServicioOficial c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "CvServicioOficial.findByDireccion", query = "SELECT c FROM CvServicioOficial c WHERE c.direccion = :direccion")
    , @NamedQuery(name = "CvServicioOficial.findByTelefono", query = "SELECT c FROM CvServicioOficial c WHERE c.telefono = :telefono")
    , @NamedQuery(name = "CvServicioOficial.findByCorreoElectronico", query = "SELECT c FROM CvServicioOficial c WHERE c.correoElectronico = :correoElectronico")
    , @NamedQuery(name = "CvServicioOficial.findByFechaCreacion", query = "SELECT c FROM CvServicioOficial c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "CvServicioOficial.findByUsuarioCreacion", query = "SELECT c FROM CvServicioOficial c WHERE c.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "CvServicioOficial.findByFechaEliminacion", query = "SELECT c FROM CvServicioOficial c WHERE c.fechaEliminacion = :fechaEliminacion")
    , @NamedQuery(name = "CvServicioOficial.findByUsuarioEliminacion", query = "SELECT c FROM CvServicioOficial c WHERE c.usuarioEliminacion = :usuarioEliminacion")
    , @NamedQuery(name = "CvServicioOficial.findByActivo", query = "SELECT c FROM CvServicioOficial c WHERE c.activo = :activo")})
public class CvServicioOficial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_SERVICIO_OFICIAL")
    private Long idServicioOficial;
    @Column(name = "NIF")
    private String nif;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "CORREO_ELECTRONICO")
    private String correoElectronico;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;
    @Column(name = "FECHA_ELIMINACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEliminacion;
    @Column(name = "USUARIO_ELIMINACION")
    private String usuarioEliminacion;
    @Column(name = "ACTIVO")
    private Short activo;
    @JoinColumn(name = "ID_CONCESIONARIO", referencedColumnName = "ID_CONCESIONARIO")
    @ManyToOne(fetch = FetchType.EAGER)
    private CvConcesionario idConcesionario;

    public CvServicioOficial() {
    }

    public CvServicioOficial(Long idServicioOficial) {
        this.idServicioOficial = idServicioOficial;
    }

    public CvServicioOficial(Long idServicioOficial, String nombre) {
        this.idServicioOficial = idServicioOficial;
        this.nombre = nombre;
    }

    public Long getIdServicioOficial() {
        return idServicioOficial;
    }

    public void setIdServicioOficial(Long idServicioOficial) {
        this.idServicioOficial = idServicioOficial;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFechaEliminacion() {
        return fechaEliminacion;
    }

    public void setFechaEliminacion(Date fechaEliminacion) {
        this.fechaEliminacion = fechaEliminacion;
    }

    public String getUsuarioEliminacion() {
        return usuarioEliminacion;
    }

    public void setUsuarioEliminacion(String usuarioEliminacion) {
        this.usuarioEliminacion = usuarioEliminacion;
    }

    public Short getActivo() {
        return activo;
    }

    public void setActivo(Short activo) {
        this.activo = activo;
    }

    public CvConcesionario getIdConcesionario() {
        return idConcesionario;
    }

    public void setIdConcesionario(CvConcesionario idConcesionario) {
        this.idConcesionario = idConcesionario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServicioOficial != null ? idServicioOficial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CvServicioOficial)) {
            return false;
        }
        CvServicioOficial other = (CvServicioOficial) object;
        if ((this.idServicioOficial == null && other.idServicioOficial != null) || (this.idServicioOficial != null && !this.idServicioOficial.equals(other.idServicioOficial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "concesionario.vehiculos.umg.concesionario.api.entity.CvServicioOficial[ idServicioOficial=" + idServicioOficial + " ]";
    }
    
}
