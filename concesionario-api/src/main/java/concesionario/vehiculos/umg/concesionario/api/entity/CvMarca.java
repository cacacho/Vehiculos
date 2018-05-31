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
@Table(name = "CV_MARCA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CvMarca.findAll", query = "SELECT c FROM CvMarca c")
    , @NamedQuery(name = "CvMarca.findByIdMarca", query = "SELECT c FROM CvMarca c WHERE c.idMarca = :idMarca")
    , @NamedQuery(name = "CvMarca.findByNombre", query = "SELECT c FROM CvMarca c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "CvMarca.findByFechaCreacion", query = "SELECT c FROM CvMarca c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "CvMarca.findByUsuarioCreacion", query = "SELECT c FROM CvMarca c WHERE c.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "CvMarca.findByFechaEliminacion", query = "SELECT c FROM CvMarca c WHERE c.fechaEliminacion = :fechaEliminacion")
    , @NamedQuery(name = "CvMarca.findByUsuarioEliminacion", query = "SELECT c FROM CvMarca c WHERE c.usuarioEliminacion = :usuarioEliminacion")
    , @NamedQuery(name = "CvMarca.findByActivo", query = "SELECT c FROM CvMarca c WHERE c.activo = :activo")})
public class CvMarca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MARCA")
    private Integer idMarca;

    @Size(max = 500)
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

    @OneToMany(mappedBy = "idMarca", fetch = FetchType.LAZY)
    private List<CvVehiculo> cvVehiculoList;

    public CvMarca() {
    }

    public CvMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
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
    public List<CvVehiculo> getCvVehiculoList() {
        return cvVehiculoList;
    }

    public void setCvVehiculoList(List<CvVehiculo> cvVehiculoList) {
        this.cvVehiculoList = cvVehiculoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMarca != null ? idMarca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CvMarca)) {
            return false;
        }
        CvMarca other = (CvMarca) object;
        if ((this.idMarca == null && other.idMarca != null) || (this.idMarca != null && !this.idMarca.equals(other.idMarca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "concesionario.vehiculos.umg.concesionario.api.entity.CvMarca[ idMarca=" + idMarca + " ]";
    }

}
