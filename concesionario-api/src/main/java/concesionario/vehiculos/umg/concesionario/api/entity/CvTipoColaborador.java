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
@Table(name = "CV_TIPO_COLABORADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CvTipoColaborador.findAll", query = "SELECT c FROM CvTipoColaborador c")
    , @NamedQuery(name = "CvTipoColaborador.findByIdTipoColaborador", query = "SELECT c FROM CvTipoColaborador c WHERE c.idTipoColaborador = :idTipoColaborador")
    , @NamedQuery(name = "CvTipoColaborador.findByDescripcion", query = "SELECT c FROM CvTipoColaborador c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "CvTipoColaborador.findByFechaCreacion", query = "SELECT c FROM CvTipoColaborador c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "CvTipoColaborador.findByUsuarioCreacion", query = "SELECT c FROM CvTipoColaborador c WHERE c.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "CvTipoColaborador.findByFechaEliminacion", query = "SELECT c FROM CvTipoColaborador c WHERE c.fechaEliminacion = :fechaEliminacion")
    , @NamedQuery(name = "CvTipoColaborador.findByUsuarioEliminacion", query = "SELECT c FROM CvTipoColaborador c WHERE c.usuarioEliminacion = :usuarioEliminacion")
    , @NamedQuery(name = "CvTipoColaborador.findByActivo", query = "SELECT c FROM CvTipoColaborador c WHERE c.activo = :activo")})
public class CvTipoColaborador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_TIPO_COLABORADOR")
    private Long idTipoColaborador;
    @Column(name = "DESCRIPCION")
    private String descripcion;
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
    @OneToMany(mappedBy = "idTipoColaborador", fetch = FetchType.EAGER)
    private List<CvColaborador> cvColaboradorList;

    public CvTipoColaborador() {
    }

    public CvTipoColaborador(Long idTipoColaborador) {
        this.idTipoColaborador = idTipoColaborador;
    }

    public Long getIdTipoColaborador() {
        return idTipoColaborador;
    }

    public void setIdTipoColaborador(Long idTipoColaborador) {
        this.idTipoColaborador = idTipoColaborador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    @XmlTransient
    public List<CvColaborador> getCvColaboradorList() {
        return cvColaboradorList;
    }

    public void setCvColaboradorList(List<CvColaborador> cvColaboradorList) {
        this.cvColaboradorList = cvColaboradorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoColaborador != null ? idTipoColaborador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CvTipoColaborador)) {
            return false;
        }
        CvTipoColaborador other = (CvTipoColaborador) object;
        if ((this.idTipoColaborador == null && other.idTipoColaborador != null) || (this.idTipoColaborador != null && !this.idTipoColaborador.equals(other.idTipoColaborador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "concesionario.vehiculos.umg.concesionario.api.entity.CvTipoColaborador[ idTipoColaborador=" + idTipoColaborador + " ]";
    }
    
}
