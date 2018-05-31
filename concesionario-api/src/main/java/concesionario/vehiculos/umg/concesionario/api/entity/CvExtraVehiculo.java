package concesionario.vehiculos.umg.concesionario.api.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "CV_EXTRA_VEHICULO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CvExtraVehiculo.findAll", query = "SELECT c FROM CvExtraVehiculo c")
    , @NamedQuery(name = "CvExtraVehiculo.findByIdExtraVehiculo", query = "SELECT c FROM CvExtraVehiculo c WHERE c.idExtraVehiculo = :idExtraVehiculo")
    , @NamedQuery(name = "CvExtraVehiculo.findByDescripcion", query = "SELECT c FROM CvExtraVehiculo c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "CvExtraVehiculo.findByPrecio", query = "SELECT c FROM CvExtraVehiculo c WHERE c.precio = :precio")
    , @NamedQuery(name = "CvExtraVehiculo.findByFechaCreacion", query = "SELECT c FROM CvExtraVehiculo c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "CvExtraVehiculo.findByUsuarioCreacion", query = "SELECT c FROM CvExtraVehiculo c WHERE c.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "CvExtraVehiculo.findByFechaEliminacion", query = "SELECT c FROM CvExtraVehiculo c WHERE c.fechaEliminacion = :fechaEliminacion")
    , @NamedQuery(name = "CvExtraVehiculo.findByUsuarioEliminacion", query = "SELECT c FROM CvExtraVehiculo c WHERE c.usuarioEliminacion = :usuarioEliminacion")
    , @NamedQuery(name = "CvExtraVehiculo.findByActivo", query = "SELECT c FROM CvExtraVehiculo c WHERE c.activo = :activo")})
public class CvExtraVehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EXTRA_VEHICULO")
    private Integer idExtraVehiculo;

    @Size(max = 1000)
    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "PRECIO")
    private Double precio;

    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Size(max = 50)
    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;

    @Column(name = "FECHA_ELIMINACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEliminacion;

    @Size(max = 50)
    @Column(name = "USUARIO_ELIMINACION")
    private String usuarioEliminacion;

    @Column(name = "ACTIVO")
    private boolean activo;

    @OneToMany(mappedBy = "idExtraVehiculo", fetch = FetchType.LAZY)
    private List<CvDetalleExtraVehiculo> cvDetalleExtraVehiculoList;

    public CvExtraVehiculo() {
    }

    public CvExtraVehiculo(Integer idExtraVehiculo) {
        this.idExtraVehiculo = idExtraVehiculo;
    }

    public Integer getIdExtraVehiculo() {
        return idExtraVehiculo;
    }

    public void setIdExtraVehiculo(Integer idExtraVehiculo) {
        this.idExtraVehiculo = idExtraVehiculo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
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

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<CvDetalleExtraVehiculo> getCvDetalleExtraVehiculoList() {
        return cvDetalleExtraVehiculoList;
    }

    public void setCvDetalleExtraVehiculoList(List<CvDetalleExtraVehiculo> cvDetalleExtraVehiculoList) {
        this.cvDetalleExtraVehiculoList = cvDetalleExtraVehiculoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExtraVehiculo != null ? idExtraVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CvExtraVehiculo)) {
            return false;
        }
        CvExtraVehiculo other = (CvExtraVehiculo) object;
        if ((this.idExtraVehiculo == null && other.idExtraVehiculo != null) || (this.idExtraVehiculo != null && !this.idExtraVehiculo.equals(other.idExtraVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "concesionario.vehiculos.umg.concesionario.api.entity.CvExtraVehiculo[ idExtraVehiculo=" + idExtraVehiculo + " ]";
    }

}
