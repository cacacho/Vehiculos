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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "CV_VENTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CvVenta.findAll", query = "SELECT c FROM CvVenta c")
    , @NamedQuery(name = "CvVenta.findByIdVenta", query = "SELECT c FROM CvVenta c WHERE c.idVenta = :idVenta")
    , @NamedQuery(name = "CvVenta.findByIdTipoPedido", query = "SELECT c FROM CvVenta c WHERE c.idTipoPedido = :idTipoPedido")
    , @NamedQuery(name = "CvVenta.findByCantidad", query = "SELECT c FROM CvVenta c WHERE c.cantidad = :cantidad")
    , @NamedQuery(name = "CvVenta.findByPrecio", query = "SELECT c FROM CvVenta c WHERE c.precio = :precio")
    , @NamedQuery(name = "CvVenta.findByTotalExtra", query = "SELECT c FROM CvVenta c WHERE c.totalExtra = :totalExtra")
    , @NamedQuery(name = "CvVenta.findByMatricula", query = "SELECT c FROM CvVenta c WHERE c.matricula = :matricula")
    , @NamedQuery(name = "CvVenta.findByTotal", query = "SELECT c FROM CvVenta c WHERE c.total = :total")
    , @NamedQuery(name = "CvVenta.findByFechaEntrega", query = "SELECT c FROM CvVenta c WHERE c.fechaEntrega = :fechaEntrega")
    , @NamedQuery(name = "CvVenta.findByFechaCreacion", query = "SELECT c FROM CvVenta c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "CvVenta.findByUsuarioCreacion", query = "SELECT c FROM CvVenta c WHERE c.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "CvVenta.findByFechaEliminacion", query = "SELECT c FROM CvVenta c WHERE c.fechaEliminacion = :fechaEliminacion")
    , @NamedQuery(name = "CvVenta.findByUsuarioEliminacion", query = "SELECT c FROM CvVenta c WHERE c.usuarioEliminacion = :usuarioEliminacion")
    , @NamedQuery(name = "CvVenta.findByActivo", query = "SELECT c FROM CvVenta c WHERE c.activo = :activo")})
public class CvVenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_VENTA")
    private Integer idVenta;

    @Column(name = "ID_TIPO_PEDIDO")
    private Integer idTipoPedido;

    @Column(name = "CANTIDAD")
    private Integer cantidad;

    @Column(name = "PRECIO")
    private Integer precio;

    @Column(name = "TOTAL_EXTRA")
    private Integer totalExtra;

    @Size(max = 30)
    @Column(name = "MATRICULA")
    private String matricula;

    @Column(name = "TOTAL")
    private Integer total;

    @Column(name = "FECHA_ENTREGA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrega;

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

    @OneToMany(mappedBy = "idVenta", fetch = FetchType.LAZY)
    private List<CvPedido> cvPedidoList;

    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
    @ManyToOne(fetch = FetchType.LAZY)
    private CvCliente idCliente;

    @JoinColumn(name = "ID_COLABORADOR", referencedColumnName = "ID_COLABORADOR")
    @ManyToOne(fetch = FetchType.LAZY)
    private CvColaborador idColaborador;

    @JoinColumn(name = "ID_DETALLE_EXTRA_VEHICULO", referencedColumnName = "ID_DETALLE_EXTRA_VEHICULO")
    @ManyToOne(fetch = FetchType.LAZY)
    private CvDetalleExtraVehiculo idDetalleExtraVehiculo;

    @JoinColumn(name = "ID_TIPO_PAGO", referencedColumnName = "ID_TIPO_PAGO")
    @ManyToOne(fetch = FetchType.LAZY)
    private CvTipoPago idTipoPago;

    @JoinColumn(name = "ID_VEHICULO", referencedColumnName = "ID_VEHICULO")
    @ManyToOne(fetch = FetchType.LAZY)
    private CvVehiculo idVehiculo;

    @JoinColumn(name = "ID_CONCESIONARIO", referencedColumnName = "ID_CONCESIONARIO")
    @ManyToOne(fetch = FetchType.LAZY)
    private CvConcesionario idConcesionario;

    public CvVenta() {
    }

    public CvVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getIdTipoPedido() {
        return idTipoPedido;
    }

    public void setIdTipoPedido(Integer idTipoPedido) {
        this.idTipoPedido = idTipoPedido;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getTotalExtra() {
        return totalExtra;
    }

    public void setTotalExtra(Integer totalExtra) {
        this.totalExtra = totalExtra;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
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

    @XmlTransient
    public List<CvPedido> getCvPedidoList() {
        return cvPedidoList;
    }

    public void setCvPedidoList(List<CvPedido> cvPedidoList) {
        this.cvPedidoList = cvPedidoList;
    }

    public CvCliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(CvCliente idCliente) {
        this.idCliente = idCliente;
    }

    public CvColaborador getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(CvColaborador idColaborador) {
        this.idColaborador = idColaborador;
    }

    public CvDetalleExtraVehiculo getIdDetalleExtraVehiculo() {
        return idDetalleExtraVehiculo;
    }

    public void setIdDetalleExtraVehiculo(CvDetalleExtraVehiculo idDetalleExtraVehiculo) {
        this.idDetalleExtraVehiculo = idDetalleExtraVehiculo;
    }

    public CvTipoPago getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(CvTipoPago idTipoPago) {
        this.idTipoPago = idTipoPago;
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
        hash += (idVenta != null ? idVenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CvVenta)) {
            return false;
        }
        CvVenta other = (CvVenta) object;
        if ((this.idVenta == null && other.idVenta != null) || (this.idVenta != null && !this.idVenta.equals(other.idVenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "concesionario.vehiculos.umg.concesionario.api.entity.CvVenta[ idVenta=" + idVenta + " ]";
    }

}
