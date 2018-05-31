package concesionario.vehiculos.umg.concesionario.api.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "CV_TRASPASO_VEHICULO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CvTraspasoVehiculo.findAll", query = "SELECT c FROM CvTraspasoVehiculo c")
    , @NamedQuery(name = "CvTraspasoVehiculo.findByIdTraspasoVehiculo", query = "SELECT c FROM CvTraspasoVehiculo c WHERE c.idTraspasoVehiculo = :idTraspasoVehiculo")
    , @NamedQuery(name = "CvTraspasoVehiculo.findByIdConcesionarioNuevo", query = "SELECT c FROM CvTraspasoVehiculo c WHERE c.idConcesionarioNuevo = :idConcesionarioNuevo")
    , @NamedQuery(name = "CvTraspasoVehiculo.findByFechaCreacion", query = "SELECT c FROM CvTraspasoVehiculo c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "CvTraspasoVehiculo.findByUsuarioCreacion", query = "SELECT c FROM CvTraspasoVehiculo c WHERE c.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "CvTraspasoVehiculo.findByFechaEliminacion", query = "SELECT c FROM CvTraspasoVehiculo c WHERE c.fechaEliminacion = :fechaEliminacion")})
public class CvTraspasoVehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TRASPASO_VEHICULO")
    private Integer idTraspasoVehiculo;

    @Column(name = "ID_CONCESIONARIO_NUEVO")
    private Long idConcesionarioNuevo;

    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Size(max = 50)
    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;

    @Column(name = "FECHA_ELIMINACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEliminacion;

    @JoinColumn(name = "ID_CONCESIONARIO_ACTUAL", referencedColumnName = "ID_CONCESIONARIO")
    @ManyToOne(fetch = FetchType.LAZY)
    private CvConcesionario idConcesionarioActual;

    @JoinColumn(name = "ID_VEHICULO", referencedColumnName = "ID_VEHICULO")
    @ManyToOne(fetch = FetchType.LAZY)
    private CvVehiculo idVehiculo;

    public CvTraspasoVehiculo() {
    }

    public CvTraspasoVehiculo(Integer idTraspasoVehiculo) {
        this.idTraspasoVehiculo = idTraspasoVehiculo;
    }

    public Integer getIdTraspasoVehiculo() {
        return idTraspasoVehiculo;
    }

    public void setIdTraspasoVehiculo(Integer idTraspasoVehiculo) {
        this.idTraspasoVehiculo = idTraspasoVehiculo;
    }

    public Long getIdConcesionarioNuevo() {
        return idConcesionarioNuevo;
    }

    public void setIdConcesionarioNuevo(Long idConcesionarioNuevo) {
        this.idConcesionarioNuevo = idConcesionarioNuevo;
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

    public CvConcesionario getIdConcesionarioActual() {
        return idConcesionarioActual;
    }

    public void setIdConcesionarioActual(CvConcesionario idConcesionarioActual) {
        this.idConcesionarioActual = idConcesionarioActual;
    }

    public CvVehiculo getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(CvVehiculo idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTraspasoVehiculo != null ? idTraspasoVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CvTraspasoVehiculo)) {
            return false;
        }
        CvTraspasoVehiculo other = (CvTraspasoVehiculo) object;
        if ((this.idTraspasoVehiculo == null && other.idTraspasoVehiculo != null) || (this.idTraspasoVehiculo != null && !this.idTraspasoVehiculo.equals(other.idTraspasoVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "concesionario.vehiculos.umg.concesionario.api.entity.CvTraspasoVehiculo[ idTraspasoVehiculo=" + idTraspasoVehiculo + " ]";
    }

}
