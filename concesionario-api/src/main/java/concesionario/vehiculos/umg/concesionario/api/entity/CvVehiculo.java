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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "CV_VEHICULO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CvVehiculo.findAll", query = "SELECT c FROM CvVehiculo c")
    , @NamedQuery(name = "CvVehiculo.findByIdVehiculo", query = "SELECT c FROM CvVehiculo c WHERE c.idVehiculo = :idVehiculo")
    , @NamedQuery(name = "CvVehiculo.findByBastidor", query = "SELECT c FROM CvVehiculo c WHERE c.bastidor = :bastidor")
    , @NamedQuery(name = "CvVehiculo.findByMatricula", query = "SELECT c FROM CvVehiculo c WHERE c.matricula = :matricula")
    , @NamedQuery(name = "CvVehiculo.findByModelo", query = "SELECT c FROM CvVehiculo c WHERE c.modelo = :modelo")
    , @NamedQuery(name = "CvVehiculo.findByMotor", query = "SELECT c FROM CvVehiculo c WHERE c.motor = :motor")
    , @NamedQuery(name = "CvVehiculo.findByColor", query = "SELECT c FROM CvVehiculo c WHERE c.color = :color")
    , @NamedQuery(name = "CvVehiculo.findByPrecio", query = "SELECT c FROM CvVehiculo c WHERE c.precio = :precio")
    , @NamedQuery(name = "CvVehiculo.findByEstandar", query = "SELECT c FROM CvVehiculo c WHERE c.estandar = :estandar")
    , @NamedQuery(name = "CvVehiculo.findByStock", query = "SELECT c FROM CvVehiculo c WHERE c.stock = :stock")
    , @NamedQuery(name = "CvVehiculo.findByFechaCreacion", query = "SELECT c FROM CvVehiculo c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "CvVehiculo.findByUsuarioCreacion", query = "SELECT c FROM CvVehiculo c WHERE c.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "CvVehiculo.findByFechaEliminacion", query = "SELECT c FROM CvVehiculo c WHERE c.fechaEliminacion = :fechaEliminacion")
    , @NamedQuery(name = "CvVehiculo.findByUsuarioEliminacion", query = "SELECT c FROM CvVehiculo c WHERE c.usuarioEliminacion = :usuarioEliminacion")
    , @NamedQuery(name = "CvVehiculo.findByActivo", query = "SELECT c FROM CvVehiculo c WHERE c.activo = :activo")})
public class CvVehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_VEHICULO")
    private Integer idVehiculo;

    @Size(max = 1000)
    @Column(name = "BASTIDOR")
    private String bastidor;

    @Size(max = 50)
    @Column(name = "MATRICULA")
    private String matricula;

    @Column(name = "MODELO")
    private Integer modelo;

    @Size(max = 15)
    @Column(name = "MOTOR")
    private String motor;

    @Size(max = 15)
    @Column(name = "COLOR")
    private String color;

    @Column(name = "PRECIO")
    private Integer precio;

    @Column(name = "ESTANDAR")
    private boolean estandar;

    @Column(name = "STOCK")
    private Integer stock;

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
    
    @OneToMany(mappedBy = "idVehiculo", fetch = FetchType.LAZY)
    private List<CvDetalleExtraVehiculo> cvDetalleExtraVehiculoList;
    
    @JoinColumn(name = "ID_CONCESIONARIO", referencedColumnName = "ID_CONCESIONARIO")
    @ManyToOne(fetch = FetchType.LAZY)
    private CvConcesionario idConcesionario;
    
    @JoinColumn(name = "ID_MARCA", referencedColumnName = "ID_MARCA")
    @ManyToOne(fetch = FetchType.LAZY)
    private CvMarca idMarca;
    
    @JoinColumn(name = "ID_TIPO_VEHICULO", referencedColumnName = "ID_TIPO_VEHICULO")
    @ManyToOne(fetch = FetchType.LAZY)
    private CvTipoVehiculo idTipoVehiculo;
    
    @OneToMany(mappedBy = "idVehiculo", fetch = FetchType.LAZY)
    private List<CvTraspasoVehiculo> cvTraspasoVehiculoList;
    
    @OneToMany(mappedBy = "idVehiculo", fetch = FetchType.LAZY)
    private List<CvVenta> cvVentaList;

    public CvVehiculo() {
    }

    public CvVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getBastidor() {
        return bastidor;
    }

    public void setBastidor(String bastidor) {
        this.bastidor = bastidor;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getModelo() {
        return modelo;
    }

    public void setModelo(Integer modelo) {
        this.modelo = modelo;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public boolean getEstandar() {
        return estandar;
    }

    public void setEstandar(boolean estandar) {
        this.estandar = estandar;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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

    public CvConcesionario getIdConcesionario() {
        return idConcesionario;
    }

    public void setIdConcesionario(CvConcesionario idConcesionario) {
        this.idConcesionario = idConcesionario;
    }

    public CvMarca getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(CvMarca idMarca) {
        this.idMarca = idMarca;
    }

    public CvTipoVehiculo getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    public void setIdTipoVehiculo(CvTipoVehiculo idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    @XmlTransient
    public List<CvTraspasoVehiculo> getCvTraspasoVehiculoList() {
        return cvTraspasoVehiculoList;
    }

    public void setCvTraspasoVehiculoList(List<CvTraspasoVehiculo> cvTraspasoVehiculoList) {
        this.cvTraspasoVehiculoList = cvTraspasoVehiculoList;
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
        hash += (idVehiculo != null ? idVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CvVehiculo)) {
            return false;
        }
        CvVehiculo other = (CvVehiculo) object;
        if ((this.idVehiculo == null && other.idVehiculo != null) || (this.idVehiculo != null && !this.idVehiculo.equals(other.idVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "concesionario.vehiculos.umg.concesionario.api.entity.CvVehiculo[ idVehiculo=" + idVehiculo + " ]";
    }

}
