package concesionario.vehiculos.umg.concesionario.api.entity;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "CV_CONCESIONARIO_PROVEEDOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CvConcesionarioProveedor.findAll", query = "SELECT c FROM CvConcesionarioProveedor c")
    , @NamedQuery(name = "CvConcesionarioProveedor.findByIdConcesionarioProveedor", query = "SELECT c FROM CvConcesionarioProveedor c WHERE c.idConcesionarioProveedor = :idConcesionarioProveedor")})
public class CvConcesionarioProveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONCESIONARIO_PROVEEDOR")
    private Integer idConcesionarioProveedor;

    @JoinColumn(name = "ID_CONCESIONARIO", referencedColumnName = "ID_CONCESIONARIO")
    @ManyToOne(fetch = FetchType.LAZY)
    private CvConcesionario idConcesionario;

    @JoinColumn(name = "ID_PROVEEDOR", referencedColumnName = "ID_PROVEEDOR")
    @ManyToOne(fetch = FetchType.LAZY)
    private CvProveedor idProveedor;

    public CvConcesionarioProveedor() {
    }

    public CvConcesionarioProveedor(Integer idConcesionarioProveedor) {
        this.idConcesionarioProveedor = idConcesionarioProveedor;
    }

    public Integer getIdConcesionarioProveedor() {
        return idConcesionarioProveedor;
    }

    public void setIdConcesionarioProveedor(Integer idConcesionarioProveedor) {
        this.idConcesionarioProveedor = idConcesionarioProveedor;
    }

    public CvConcesionario getIdConcesionario() {
        return idConcesionario;
    }

    public void setIdConcesionario(CvConcesionario idConcesionario) {
        this.idConcesionario = idConcesionario;
    }

    public CvProveedor getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(CvProveedor idProveedor) {
        this.idProveedor = idProveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConcesionarioProveedor != null ? idConcesionarioProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CvConcesionarioProveedor)) {
            return false;
        }
        CvConcesionarioProveedor other = (CvConcesionarioProveedor) object;
        if ((this.idConcesionarioProveedor == null && other.idConcesionarioProveedor != null) || (this.idConcesionarioProveedor != null && !this.idConcesionarioProveedor.equals(other.idConcesionarioProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "concesionario.vehiculos.umg.concesionario.api.entity.CvConcesionarioProveedor[ idConcesionarioProveedor=" + idConcesionarioProveedor + " ]";
    }

}
