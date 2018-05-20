/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concesionario.vehiculos.umg.concesionario.api.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "CV_DETALLE_EXTRA_VEHICULO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CvDetalleExtraVehiculo.findAll", query = "SELECT c FROM CvDetalleExtraVehiculo c")
    , @NamedQuery(name = "CvDetalleExtraVehiculo.findByIdDetalleExtraVehiculo", query = "SELECT c FROM CvDetalleExtraVehiculo c WHERE c.idDetalleExtraVehiculo = :idDetalleExtraVehiculo")
    , @NamedQuery(name = "CvDetalleExtraVehiculo.findByFechaCreacion", query = "SELECT c FROM CvDetalleExtraVehiculo c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "CvDetalleExtraVehiculo.findByUsuarioCreacion", query = "SELECT c FROM CvDetalleExtraVehiculo c WHERE c.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "CvDetalleExtraVehiculo.findByFechaEliminacion", query = "SELECT c FROM CvDetalleExtraVehiculo c WHERE c.fechaEliminacion = :fechaEliminacion")
    , @NamedQuery(name = "CvDetalleExtraVehiculo.findByUsuarioEliminacion", query = "SELECT c FROM CvDetalleExtraVehiculo c WHERE c.usuarioEliminacion = :usuarioEliminacion")
    , @NamedQuery(name = "CvDetalleExtraVehiculo.findByActivo", query = "SELECT c FROM CvDetalleExtraVehiculo c WHERE c.activo = :activo")})
public class CvDetalleExtraVehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_DETALLE_EXTRA_VEHICULO")
    private Long idDetalleExtraVehiculo;
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
    @JoinColumn(name = "ID_EXTRA_VEHICULO", referencedColumnName = "ID_EXTRA_VEHICULO")
    @ManyToOne(fetch = FetchType.EAGER)
    private CvExtraVehiculo idExtraVehiculo;
    @JoinColumn(name = "ID_VEHICULO", referencedColumnName = "ID_VEHICULO")
    @ManyToOne(fetch = FetchType.EAGER)
    private CvVehiculo idVehiculo;
    @OneToMany(mappedBy = "idDetalleExtraVehiculo", fetch = FetchType.EAGER)
    private List<CvVenta> cvVentaList;

    public CvDetalleExtraVehiculo() {
    }

    public CvDetalleExtraVehiculo(Long idDetalleExtraVehiculo) {
        this.idDetalleExtraVehiculo = idDetalleExtraVehiculo;
    }

    public Long getIdDetalleExtraVehiculo() {
        return idDetalleExtraVehiculo;
    }

    public void setIdDetalleExtraVehiculo(Long idDetalleExtraVehiculo) {
        this.idDetalleExtraVehiculo = idDetalleExtraVehiculo;
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

    public CvExtraVehiculo getIdExtraVehiculo() {
        return idExtraVehiculo;
    }

    public void setIdExtraVehiculo(CvExtraVehiculo idExtraVehiculo) {
        this.idExtraVehiculo = idExtraVehiculo;
    }

    public CvVehiculo getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(CvVehiculo idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    @XmlTransient
    public List<CvVenta> getCvVentaList() {
        return cvVentaList;
    }

    public void setCvVentaList(List<CvVenta> cvVentaList) {
        this.cvVentaList = cvVentaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleExtraVehiculo != null ? idDetalleExtraVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CvDetalleExtraVehiculo)) {
            return false;
        }
        CvDetalleExtraVehiculo other = (CvDetalleExtraVehiculo) object;
        if ((this.idDetalleExtraVehiculo == null && other.idDetalleExtraVehiculo != null) || (this.idDetalleExtraVehiculo != null && !this.idDetalleExtraVehiculo.equals(other.idDetalleExtraVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "concesionario.vehiculos.umg.concesionario.api.entity.CvDetalleExtraVehiculo[ idDetalleExtraVehiculo=" + idDetalleExtraVehiculo + " ]";
    }
    
}
