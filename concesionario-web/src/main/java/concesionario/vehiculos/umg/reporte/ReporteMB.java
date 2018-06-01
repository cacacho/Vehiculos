package concesionario.vehiculos.umg.reporte;

import concesionario.vehiculos.umg.login.LoginMB;
import concesionario.vehiculos.umg.utilidades.JsfUtil;
import concesionario.vehiculos.umg.venta.utils.JasperUtil;
import concesionario.vehiculos.umg.venta.utils.ReporteJasper;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.HashMap;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author DELL
 */
@ManagedBean(name = "reporteMB")
@ViewScoped
public class ReporteMB implements Serializable {

    private static final Logger log = Logger.getLogger(ReporteMB.class);

    @Resource(lookup = "jdbc/vehiculos")
    private DataSource dataSource;

    public StreamedContent generarPdfMasVendido() {
        try {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String realPath = servletContext.getRealPath("/");
            String nombreReporte = "rptVehiculoVendido";
            String nombreArchivo = "Vehículo_más_Vendido.pdf";
            HashMap parametros = new HashMap();
            parametros.put("IMAGE", "umg.png");
            parametros.put("DIRECTORIO", realPath + File.separator + "resources" + File.separator + "images" + File.separator);
            parametros.put("USUARIO", LoginMB.usuario);
            ReporteJasper reporteJasper = JasperUtil.jasperReportPDF(nombreReporte, nombreArchivo, parametros, dataSource);
            StreamedContent streamedContent;
            FileInputStream stream = new FileInputStream(realPath + "resources/reports/" + reporteJasper.getFileName());
            streamedContent = new DefaultStreamedContent(stream, "application/pdf", reporteJasper.getFileName());
            return streamedContent;
        } catch (Exception ex) {
            log.error(ex);
            JsfUtil.addErrorMessage("Ocurrio un error al generar el pdf del reporte");
        }
        return null;
    }

}
