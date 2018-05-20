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
@Table(name = "CV_PROVEEDOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CvProveedor.findAll", query = "SELECT c FROM CvProveedor c")
    , @NamedQuery(name = "CvProveedor.findByIdProveedor", query = "SELECT c FROM CvProveedor c WHERE c.idProveedor = :idProveedor")
    , @NamedQuery(name = "CvProveedor.findByNombre", query = "SELECT c FROM CvProveedor c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "CvProveedor.findByDireccion", query = "SELECT c FROM CvProveedor c WHERE c.direccion = :direccion")
    , @NamedQuery(name = "CvProveedor.findByTelefono", query = "SELECT c FROM CvProveedor c WHERE c.telefono = :telefono")
    , @NamedQuery(name = "CvProveedor.findByCorreoElectronico", query = "SELECT c FROM CvProveedor c WHERE c.correoElectronico = :correoElectronico")
    , @NamedQuery(name = "CvProveedor.findByFechaCreacion", query = "SELECT c FROM CvProveedor c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "CvProveedor.findByUsuarioCreacion", query = "SELECT c FROM CvProveedor c WHERE c.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "CvProveedor.findByFechaEliminacion", query = "SELECT c FROM CvProveedor c WHERE c.fechaEliminacion = :fechaEliminacion")
    , @NamedQuery(name = "CvProveedor.findByUsuarioEliminacion", query = "SELECT c FROM CvProveedor c WHERE c.usuarioEliminacion = :usuarioEliminacion")
    , @NamedQuery(name = "CvProveedor.findByActivo", query = "SELECT c FROM CvProveedor c WHERE c.activo = :activo")})
public class CvProveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PROVEEDOR")
    private Long idProveedor;
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
    @OneToMany(mappedBy = "idProveedor", fetch = FetchType.EAGER)
    private List<CvConcesionario> cvConcesionarioList;

    public CvProveedor() {
    }

    public CvProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
    }

    public CvProveedor(Long idProveedor, String nombre) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
    }

    public Long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
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

    @XmlTransient
    public List<CvConcesionario> getCvConcesionarioList() {
        return cvConcesionarioList;
    }

    public void setCvConcesionarioList(List<CvConcesionario> cvConcesionarioList) {
        this.cvConcesionarioList = cvConcesionarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedor != null ? idProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CvProveedor)) {
            return false;
        }
        CvProveedor other = (CvProveedor) object;
        if ((this.idProveedor == null && other.idProveedor != null) || (this.idProveedor != null && !this.idProveedor.equals(other.idProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "concesionario.vehiculos.umg.concesionario.api.entity.CvProveedor[ idProveedor=" + idProveedor + " ]";
    }
    
}
