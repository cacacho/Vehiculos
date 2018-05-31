package concesionario.vehiculos.umg.concesionario.api.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "CV_USUARIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CvUsuarios.findAll", query = "SELECT c FROM CvUsuarios c")
    , @NamedQuery(name = "CvUsuarios.findByIdUsuario", query = "SELECT c FROM CvUsuarios c WHERE c.idUsuario = :idUsuario")
    , @NamedQuery(name = "CvUsuarios.findByUsuario", query = "SELECT c FROM CvUsuarios c WHERE c.usuario = :usuario")
    , @NamedQuery(name = "CvUsuarios.findByPassword", query = "SELECT c FROM CvUsuarios c WHERE c.password = :password")
    , @NamedQuery(name = "CvUsuarios.findByFechaCreacion", query = "SELECT c FROM CvUsuarios c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "CvUsuarios.findByUsuarioCreacion", query = "SELECT c FROM CvUsuarios c WHERE c.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "CvUsuarios.findByFechaEliminacion", query = "SELECT c FROM CvUsuarios c WHERE c.fechaEliminacion = :fechaEliminacion")
    , @NamedQuery(name = "CvUsuarios.findByUsuarioEliminacion", query = "SELECT c FROM CvUsuarios c WHERE c.usuarioEliminacion = :usuarioEliminacion")
    , @NamedQuery(name = "CvUsuarios.findByActivo", query = "SELECT c FROM CvUsuarios c WHERE c.activo = :activo")})
public class CvUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "USUARIO")
    private String usuario;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PASSWORD")
    private String password;
    
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
    
    @JoinColumn(name = "ID_COLABORADOR", referencedColumnName = "ID_COLABORADOR")
    @ManyToOne(fetch = FetchType.LAZY)
    private CvColaborador idColaborador;

    public CvUsuarios() {
    }

    public CvUsuarios(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public CvUsuarios(Integer idUsuario, String usuario, String password) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.password = password;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public CvColaborador getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(CvColaborador idColaborador) {
        this.idColaborador = idColaborador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CvUsuarios)) {
            return false;
        }
        CvUsuarios other = (CvUsuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "concesionario.vehiculos.umg.concesionario.api.entity.CvUsuarios[ idUsuario=" + idUsuario + " ]";
    }

}
