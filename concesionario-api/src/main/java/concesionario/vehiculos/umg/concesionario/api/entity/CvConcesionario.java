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
@Table(name = "CV_CONCESIONARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CvConcesionario.findAll", query = "SELECT c FROM CvConcesionario c")
    , @NamedQuery(name = "CvConcesionario.findByIdConcesionario", query = "SELECT c FROM CvConcesionario c WHERE c.idConcesionario = :idConcesionario")
    , @NamedQuery(name = "CvConcesionario.findByNombre", query = "SELECT c FROM CvConcesionario c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "CvConcesionario.findByDireccion", query = "SELECT c FROM CvConcesionario c WHERE c.direccion = :direccion")
    , @NamedQuery(name = "CvConcesionario.findByFechaCreacion", query = "SELECT c FROM CvConcesionario c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "CvConcesionario.findByUsuarioCreacion", query = "SELECT c FROM CvConcesionario c WHERE c.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "CvConcesionario.findByFechaEliminacion", query = "SELECT c FROM CvConcesionario c WHERE c.fechaEliminacion = :fechaEliminacion")
    , @NamedQuery(name = "CvConcesionario.findByUsuarioEliminacion", query = "SELECT c FROM CvConcesionario c WHERE c.usuarioEliminacion = :usuarioEliminacion")
    , @NamedQuery(name = "CvConcesionario.findByActivo", query = "SELECT c FROM CvConcesionario c WHERE c.activo = :activo")})
public class CvConcesionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONCESIONARIO")
    private Integer idConcesionario;

    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;

    @Size(max = 250)
    @Column(name = "DIRECCION")
    private String direccion;

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

    @OneToMany(mappedBy = "idConcesionario", fetch = FetchType.LAZY)
    private List<CvServicioOficial> cvServicioOficialList;

    @OneToMany(mappedBy = "idConcesionario", fetch = FetchType.LAZY)
    private List<CvVehiculo> cvVehiculoList;

    @OneToMany(mappedBy = "idConcesionarioActual", fetch = FetchType.LAZY)
    private List<CvTraspasoVehiculo> cvTraspasoVehiculoList;

    @OneToMany(mappedBy = "idConcesionario", fetch = FetchType.LAZY)
    private List<CvPedido> cvPedidoList;

    public CvConcesionario() {
    }

    public CvConcesionario(Integer idConcesionario) {
        this.idConcesionario = idConcesionario;
    }

    public CvConcesionario(Integer idConcesionario, String nombre) {
        this.idConcesionario = idConcesionario;
        this.nombre = nombre;
    }

    public Integer getIdConcesionario() {
        return idConcesionario;
    }

    public void setIdConcesionario(Integer idConcesionario) {
        this.idConcesionario = idConcesionario;
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
    public List<CvServicioOficial> getCvServicioOficialList() {
        return cvServicioOficialList;
    }

    public void setCvServicioOficialList(List<CvServicioOficial> cvServicioOficialList) {
        this.cvServicioOficialList = cvServicioOficialList;
    }

    @XmlTransient
    public List<CvVehiculo> getCvVehiculoList() {
        return cvVehiculoList;
    }

    public void setCvVehiculoList(List<CvVehiculo> cvVehiculoList) {
        this.cvVehiculoList = cvVehiculoList;
    }

    @XmlTransient
    public List<CvTraspasoVehiculo> getCvTraspasoVehiculoList() {
        return cvTraspasoVehiculoList;
    }

    public void setCvTraspasoVehiculoList(List<CvTraspasoVehiculo> cvTraspasoVehiculoList) {
        this.cvTraspasoVehiculoList = cvTraspasoVehiculoList;
    }

    @XmlTransient
    public List<CvPedido> getCvPedidoList() {
        return cvPedidoList;
    }

    public void setCvPedidoList(List<CvPedido> cvPedidoList) {
        this.cvPedidoList = cvPedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConcesionario != null ? idConcesionario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CvConcesionario)) {
            return false;
        }
        CvConcesionario other = (CvConcesionario) object;
        if ((this.idConcesionario == null && other.idConcesionario != null) || (this.idConcesionario != null && !this.idConcesionario.equals(other.idConcesionario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "concesionario.vehiculos.umg.concesionario.api.entity.CvConcesionario[ idConcesionario=" + idConcesionario + " ]";
    }

}
