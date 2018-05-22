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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "CV_COLABORADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CvColaborador.findAll", query = "SELECT c FROM CvColaborador c")
    , @NamedQuery(name = "CvColaborador.findByIdColaborador", query = "SELECT c FROM CvColaborador c WHERE c.idColaborador = :idColaborador")
    , @NamedQuery(name = "CvColaborador.findByCui", query = "SELECT c FROM CvColaborador c WHERE c.cui = :cui")
    , @NamedQuery(name = "CvColaborador.findByNif", query = "SELECT c FROM CvColaborador c WHERE c.nif = :nif")
    , @NamedQuery(name = "CvColaborador.findByDireccion", query = "SELECT c FROM CvColaborador c WHERE c.direccion = :direccion")
    , @NamedQuery(name = "CvColaborador.findByTelefono", query = "SELECT c FROM CvColaborador c WHERE c.telefono = :telefono")
    , @NamedQuery(name = "CvColaborador.findByFechaCreacion", query = "SELECT c FROM CvColaborador c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "CvColaborador.findByUsuarioCreacion", query = "SELECT c FROM CvColaborador c WHERE c.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "CvColaborador.findByFechaEliminacion", query = "SELECT c FROM CvColaborador c WHERE c.fechaEliminacion = :fechaEliminacion")
    , @NamedQuery(name = "CvColaborador.findByUsuarioEliminacion", query = "SELECT c FROM CvColaborador c WHERE c.usuarioEliminacion = :usuarioEliminacion")
    , @NamedQuery(name = "CvColaborador.findByActivo", query = "SELECT c FROM CvColaborador c WHERE c.activo = :activo")})
public class CvColaborador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COLABORADOR")
    private Integer idColaborador;

    @Column(name = "PRIMER_NOMBRE")
    private String primerNombre;

    @Column(name = "SEGUNDO_NOMBRE")
    private String segundoNombre;

    @Column(name = "PRIMER_APELLIDO")
    private String primerApellido;

    @Column(name = "SEGUNDO_APELLIDO")
    private String segundoApellido;

    @Column(name = "CUI")
    private Long cui;

    @Column(name = "NIF")
    private String nif;

    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "TELEFONO")
    private String telefono;

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

    @OneToMany(mappedBy = "idColaborador", fetch = FetchType.LAZY)
    private List<CvUsuarios> cvUsuariosList;

    @JoinColumn(name = "ID_TIPO_COLABORADOR", referencedColumnName = "ID_TIPO_COLABORADOR")
    @ManyToOne(fetch = FetchType.LAZY)
    private CvTipoColaborador idTipoColaborador;

    @OneToMany(mappedBy = "idColaborador", fetch = FetchType.LAZY)
    private List<CvVenta> cvVentaList;

    public CvColaborador() {
    }

    public CvColaborador(Integer idColaborador) {
        this.idColaborador = idColaborador;
    }

    public Integer getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Integer idColaborador) {
        this.idColaborador = idColaborador;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Long getCui() {
        return cui;
    }

    public void setCui(Long cui) {
        this.cui = cui;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
    public List<CvUsuarios> getCvUsuariosList() {
        return cvUsuariosList;
    }

    public void setCvUsuariosList(List<CvUsuarios> cvUsuariosList) {
        this.cvUsuariosList = cvUsuariosList;
    }

    public CvTipoColaborador getIdTipoColaborador() {
        return idTipoColaborador;
    }

    public void setIdTipoColaborador(CvTipoColaborador idTipoColaborador) {
        this.idTipoColaborador = idTipoColaborador;
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
        hash += (idColaborador != null ? idColaborador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CvColaborador)) {
            return false;
        }
        CvColaborador other = (CvColaborador) object;
        if ((this.idColaborador == null && other.idColaborador != null) || (this.idColaborador != null && !this.idColaborador.equals(other.idColaborador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "concesionario.vehiculos.umg.concesionario.api.entity.CvColaborador[ idColaborador=" + idColaborador + " ]";
    }

}
