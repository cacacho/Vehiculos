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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "CV_CLIENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CvCliente.findAll", query = "SELECT c FROM CvCliente c")
    , @NamedQuery(name = "CvCliente.findByIdCliente", query = "SELECT c FROM CvCliente c WHERE c.idCliente = :idCliente")
    , @NamedQuery(name = "CvCliente.findByNombres", query = "SELECT c FROM CvCliente c WHERE c.nombres = :nombres")
    , @NamedQuery(name = "CvCliente.findByApellidos", query = "SELECT c FROM CvCliente c WHERE c.apellidos = :apellidos")
    , @NamedQuery(name = "CvCliente.findByGenero", query = "SELECT c FROM CvCliente c WHERE c.genero = :genero")
    , @NamedQuery(name = "CvCliente.findByDireccion", query = "SELECT c FROM CvCliente c WHERE c.direccion = :direccion")
    , @NamedQuery(name = "CvCliente.findByCorreoElectronico", query = "SELECT c FROM CvCliente c WHERE c.correoElectronico = :correoElectronico")
    , @NamedQuery(name = "CvCliente.findByFechaCreacion", query = "SELECT c FROM CvCliente c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "CvCliente.findByUsuarioCreacion", query = "SELECT c FROM CvCliente c WHERE c.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "CvCliente.findByFechaEliminacion", query = "SELECT c FROM CvCliente c WHERE c.fechaEliminacion = :fechaEliminacion")
    , @NamedQuery(name = "CvCliente.findByUsuarioEliminacion", query = "SELECT c FROM CvCliente c WHERE c.usuarioEliminacion = :usuarioEliminacion")
    , @NamedQuery(name = "CvCliente.findByActivo", query = "SELECT c FROM CvCliente c WHERE c.activo = :activo")})
public class CvCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE")
    private Integer idCliente;

    @Column(name = "NIT")
    private String nit;

    @Basic(optional = false)
    @Column(name = "NOMBRES")
    private String nombres;

    @Basic(optional = false)
    @Column(name = "APELLIDOS")
    private String apellidos;

    @Column(name = "GENERO")
    private String genero;

    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "CORREO_ELECTRONICO")
    private String correoElectronico;

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

    @OneToMany(mappedBy = "idCliente", fetch = FetchType.LAZY)
    private List<CvVenta> cvVentaList;

    public CvCliente() {
    }

    public CvCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public CvCliente(Integer idCliente, String nombres, String apellidos) {
        this.idCliente = idCliente;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
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

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
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
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CvCliente)) {
            return false;
        }
        CvCliente other = (CvCliente) object;
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "concesionario.vehiculos.umg.concesionario.api.entity.CvCliente[ idCliente=" + idCliente + " ]";
    }

}
