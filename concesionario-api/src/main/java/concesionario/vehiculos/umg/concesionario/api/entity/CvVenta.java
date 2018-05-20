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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "CV_VENTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CvVenta.findAll", query = "SELECT c FROM CvVenta c")
    , @NamedQuery(name = "CvVenta.findByIdVenta", query = "SELECT c FROM CvVenta c WHERE c.idVenta = :idVenta")
    , @NamedQuery(name = "CvVenta.findByIdTipoPedido", query = "SELECT c FROM CvVenta c WHERE c.idTipoPedido = :idTipoPedido")
    , @NamedQuery(name = "CvVenta.findByCantidad", query = "SELECT c FROM CvVenta c WHERE c.cantidad = :cantidad")
    , @NamedQuery(name = "CvVenta.findByPrecio", query = "SELECT c FROM CvVenta c WHERE c.precio = :precio")
    , @NamedQuery(name = "CvVenta.findByTotalExtra", query = "SELECT c FROM CvVenta c WHERE c.totalExtra = :totalExtra")
    , @NamedQuery(name = "CvVenta.findByTotal", query = "SELECT c FROM CvVenta c WHERE c.total = :total")
    , @NamedQuery(name = "CvVenta.findByMatricula", query = "SELECT c FROM CvVenta c WHERE c.matricula = :matricula")
    , @NamedQuery(name = "CvVenta.findByFechaEntrega", query = "SELECT c FROM CvVenta c WHERE c.fechaEntrega = :fechaEntrega")
    , @NamedQuery(name = "CvVenta.findByFechaCreacion", query = "SELECT c FROM CvVenta c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "CvVenta.findByUsuarioCreacion", query = "SELECT c FROM CvVenta c WHERE c.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "CvVenta.findByFechaEliminacion", query = "SELECT c FROM CvVenta c WHERE c.fechaEliminacion = :fechaEliminacion")
    , @NamedQuery(name = "CvVenta.findByUsuarioEliminacion", query = "SELECT c FROM CvVenta c WHERE c.usuarioEliminacion = :usuarioEliminacion")
    , @NamedQuery(name = "CvVenta.findByActivo", query = "SELECT c FROM CvVenta c WHERE c.activo = :activo")})
public class CvVenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_VENTA")
    private Long idVenta;
    @Column(name = "ID_TIPO_PEDIDO")
    private Long idTipoPedido;
    @Column(name = "CANTIDAD")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRECIO")
    private Double precio;
    @Column(name = "TOTAL_EXTRA")
    private Double totalExtra;
    @Column(name = "TOTAL")
    private Double total;
    @Column(name = "MATRICULA")
    private String matricula;
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
    private Short activo;
    @OneToMany(mappedBy = "idVenta", fetch = FetchType.EAGER)
    private List<CvPedido> cvPedidoList;
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
    @ManyToOne(fetch = FetchType.EAGER)
    private CvCliente idCliente;
    @JoinColumn(name = "ID_COLABORADOR", referencedColumnName = "ID_COLABORADOR")
    @ManyToOne(fetch = FetchType.EAGER)
    private CvColaborador idColaborador;
    @JoinColumn(name = "ID_DETALLE_EXTRA_VEHICULO", referencedColumnName = "ID_DETALLE_EXTRA_VEHICULO")
    @ManyToOne(fetch = FetchType.EAGER)
    private CvDetalleExtraVehiculo idDetalleExtraVehiculo;
    @JoinColumn(name = "ID_TIPO_PAGO", referencedColumnName = "ID_TIPO_PAGO")
    @ManyToOne(fetch = FetchType.EAGER)
    private CvTipoPago idTipoPago;
    @JoinColumn(name = "ID_TIPO_SUCURSAL", referencedColumnName = "ID_TIPO_SUCURSAL")
    @ManyToOne(fetch = FetchType.EAGER)
    private CvTipoSucursal idTipoSucursal;
    @JoinColumn(name = "ID_VEHICULO", referencedColumnName = "ID_VEHICULO")
    @ManyToOne(fetch = FetchType.EAGER)
    private CvVehiculo idVehiculo;

    public CvVenta() {
    }

    public CvVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public Long getIdTipoPedido() {
        return idTipoPedido;
    }

    public void setIdTipoPedido(Long idTipoPedido) {
        this.idTipoPedido = idTipoPedido;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getTotalExtra() {
        return totalExtra;
    }

    public void setTotalExtra(Double totalExtra) {
        this.totalExtra = totalExtra;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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

    public Short getActivo() {
        return activo;
    }

    public void setActivo(Short activo) {
        this.activo = activo;
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

    public CvTipoSucursal getIdTipoSucursal() {
        return idTipoSucursal;
    }

    public void setIdTipoSucursal(CvTipoSucursal idTipoSucursal) {
        this.idTipoSucursal = idTipoSucursal;
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
