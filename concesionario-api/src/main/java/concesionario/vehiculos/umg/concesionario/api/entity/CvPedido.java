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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "CV_PEDIDO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CvPedido.findAll", query = "SELECT c FROM CvPedido c")
    , @NamedQuery(name = "CvPedido.findByIdPedido", query = "SELECT c FROM CvPedido c WHERE c.idPedido = :idPedido")
    , @NamedQuery(name = "CvPedido.findByIdProveedor", query = "SELECT c FROM CvPedido c WHERE c.idProveedor = :idProveedor")
    , @NamedQuery(name = "CvPedido.findByFechaEntrega", query = "SELECT c FROM CvPedido c WHERE c.fechaEntrega = :fechaEntrega")
    , @NamedQuery(name = "CvPedido.findByFechaCreacion", query = "SELECT c FROM CvPedido c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "CvPedido.findByUsuarioCreacion", query = "SELECT c FROM CvPedido c WHERE c.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "CvPedido.findByFechaEliminacion", query = "SELECT c FROM CvPedido c WHERE c.fechaEliminacion = :fechaEliminacion")
    , @NamedQuery(name = "CvPedido.findByUsuarioEliminacion", query = "SELECT c FROM CvPedido c WHERE c.usuarioEliminacion = :usuarioEliminacion")
    , @NamedQuery(name = "CvPedido.findByActivo", query = "SELECT c FROM CvPedido c WHERE c.activo = :activo")})
public class CvPedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PEDIDO")
    private Integer idPedido;

    @Column(name = "ID_PROVEEDOR")
    private Integer idProveedor;

    @Column(name = "FECHA_ENTREGA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrega;

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
    private boolean activo;

    @JoinColumn(name = "ID_CONCESIONARIO", referencedColumnName = "ID_CONCESIONARIO")
    @ManyToOne(fetch = FetchType.LAZY)
    private CvConcesionario idConcesionario;

    @JoinColumn(name = "ID_TIPO_PEDIDO", referencedColumnName = "ID_TIPO_PEDIDO")
    @ManyToOne(fetch = FetchType.LAZY)
    private CvTipoPedido idTipoPedido;

    @JoinColumn(name = "ID_VENTA", referencedColumnName = "ID_VENTA")
    @ManyToOne(fetch = FetchType.LAZY)
    private CvVenta idVenta;

    public CvPedido() {
    }

    public CvPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
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

    public CvConcesionario getIdConcesionario() {
        return idConcesionario;
    }

    public void setIdConcesionario(CvConcesionario idConcesionario) {
        this.idConcesionario = idConcesionario;
    }

    public CvTipoPedido getIdTipoPedido() {
        return idTipoPedido;
    }

    public void setIdTipoPedido(CvTipoPedido idTipoPedido) {
        this.idTipoPedido = idTipoPedido;
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
        hash += (idPedido != null ? idPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CvPedido)) {
            return false;
        }
        CvPedido other = (CvPedido) object;
        if ((this.idPedido == null && other.idPedido != null) || (this.idPedido != null && !this.idPedido.equals(other.idPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "concesionario.vehiculos.umg.concesionario.api.entity.CvPedido[ idPedido=" + idPedido + " ]";
    }

}
