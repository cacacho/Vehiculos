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
@Table(name = "CV_VENTA_VEHICULO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CvVentaVehiculo.findAll", query = "SELECT c FROM CvVentaVehiculo c")
    , @NamedQuery(name = "CvVentaVehiculo.findByIdVentaVehiculo", query = "SELECT c FROM CvVentaVehiculo c WHERE c.idVentaVehiculo = :idVentaVehiculo")
    , @NamedQuery(name = "CvVentaVehiculo.findByUsuarioCreacion", query = "SELECT c FROM CvVentaVehiculo c WHERE c.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "CvVentaVehiculo.findByFechaCreacion", query = "SELECT c FROM CvVentaVehiculo c WHERE c.fechaCreacion = :fechaCreacion")})
public class CvVentaVehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_VENTA_VEHICULO")
    private Integer idVentaVehiculo;

    @Size(max = 50)
    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;

    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @JoinColumn(name = "ID_VEHICULO", referencedColumnName = "ID_VEHICULO")
    @ManyToOne(fetch = FetchType.LAZY)
    private CvVehiculo idVehiculo;

    @JoinColumn(name = "ID_VENTA", referencedColumnName = "ID_VENTA")
    @ManyToOne(fetch = FetchType.LAZY)
    private CvVenta idVenta;

    public CvVentaVehiculo() {
    }

    public CvVentaVehiculo(Integer idVentaVehiculo) {
        this.idVentaVehiculo = idVentaVehiculo;
    }

    public Integer getIdVentaVehiculo() {
        return idVentaVehiculo;
    }

    public void setIdVentaVehiculo(Integer idVentaVehiculo) {
        this.idVentaVehiculo = idVentaVehiculo;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public CvVehiculo getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(CvVehiculo idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public CvVenta getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(CvVenta idVenta) {
        this.idVenta = idVenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVentaVehiculo != null ? idVentaVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CvVentaVehiculo)) {
            return false;
        }
        CvVentaVehiculo other = (CvVentaVehiculo) object;
        if ((this.idVentaVehiculo == null && other.idVentaVehiculo != null) || (this.idVentaVehiculo != null && !this.idVentaVehiculo.equals(other.idVentaVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "concesionario.vehiculos.umg.concesionario.api.entity.CvVentaVehiculo[ idVentaVehiculo=" + idVentaVehiculo + " ]";
    }

}
