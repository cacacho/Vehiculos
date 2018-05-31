package concesionario.vehiculos.umg.concesionario.api.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "CV_TIPO_SUCURSAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CvTipoSucursal.findAll", query = "SELECT c FROM CvTipoSucursal c")
    , @NamedQuery(name = "CvTipoSucursal.findByIdTipoSucursal", query = "SELECT c FROM CvTipoSucursal c WHERE c.idTipoSucursal = :idTipoSucursal")
    , @NamedQuery(name = "CvTipoSucursal.findByNombre", query = "SELECT c FROM CvTipoSucursal c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "CvTipoSucursal.findByFechaCreacion", query = "SELECT c FROM CvTipoSucursal c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "CvTipoSucursal.findByUsuarioCreacion", query = "SELECT c FROM CvTipoSucursal c WHERE c.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "CvTipoSucursal.findByFechaEliminacion", query = "SELECT c FROM CvTipoSucursal c WHERE c.fechaEliminacion = :fechaEliminacion")
    , @NamedQuery(name = "CvTipoSucursal.findByUsuarioEliminacion", query = "SELECT c FROM CvTipoSucursal c WHERE c.usuarioEliminacion = :usuarioEliminacion")
    , @NamedQuery(name = "CvTipoSucursal.findByActivo", query = "SELECT c FROM CvTipoSucursal c WHERE c.activo = :activo")})
public class CvTipoSucursal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_SUCURSAL")
    private Integer idTipoSucursal;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;

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

    @OneToMany(mappedBy = "idTipoSucursal", fetch = FetchType.LAZY)
    private List<CvVenta> cvVentaList;

    public CvTipoSucursal() {
    }

    public CvTipoSucursal(Integer idTipoSucursal) {
        this.idTipoSucursal = idTipoSucursal;
    }

    public CvTipoSucursal(Integer idTipoSucursal, String nombre) {
        this.idTipoSucursal = idTipoSucursal;
        this.nombre = nombre;
    }

    public Integer getIdTipoSucursal() {
        return idTipoSucursal;
    }

    public void setIdTipoSucursal(Integer idTipoSucursal) {
        this.idTipoSucursal = idTipoSucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    public List<CvVenta> getCvVentaList() {
        return cvVentaList;
    }

    public void setCvVentaList(List<CvVenta> cvVentaList) {
        this.cvVentaList = cvVentaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoSucursal != null ? idTipoSucursal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CvTipoSucursal)) {
            return false;
        }
        CvTipoSucursal other = (CvTipoSucursal) object;
        if ((this.idTipoSucursal == null && other.idTipoSucursal != null) || (this.idTipoSucursal != null && !this.idTipoSucursal.equals(other.idTipoSucursal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "concesionario.vehiculos.umg.concesionario.api.entity.CvTipoSucursal[ idTipoSucursal=" + idTipoSucursal + " ]";
    }

}
