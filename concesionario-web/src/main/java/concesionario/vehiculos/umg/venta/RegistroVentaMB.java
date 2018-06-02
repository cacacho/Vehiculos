package concesionario.vehiculos.umg.venta;

import concesionario.vehiculos.umg.concesionario.api.ejb.CatalogoBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.ejb.VehiculoBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.ejb.VentaBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvCliente;
import concesionario.vehiculos.umg.concesionario.api.entity.CvExtraVehiculo;
import concesionario.vehiculos.umg.concesionario.api.entity.CvPedido;
import concesionario.vehiculos.umg.concesionario.api.entity.CvTipoPago;
import concesionario.vehiculos.umg.concesionario.api.entity.CvTipoPedido;
import concesionario.vehiculos.umg.concesionario.api.entity.CvVehiculo;
import concesionario.vehiculos.umg.concesionario.api.entity.CvVenta;
import concesionario.vehiculos.umg.concesionario.api.enums.TipoPedidoEnum;
import concesionario.vehiculos.umg.login.LoginMB;
import concesionario.vehiculos.umg.utilidades.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
@ManagedBean(name = "registroVentaMB")
@ViewScoped
public class RegistroVentaMB implements Serializable {

    private static final Logger log = Logger.getLogger(RegistroVentaMB.class);

    @EJB
    private VentaBeanLocal ventaBeanLocal;
    @EJB
    private VehiculoBeanLocal vehiculoBeanLocal;
    @EJB
    private CatalogoBeanLocal catalogoBean;

    private List<CvVehiculo> listSelectedVehiculo;
    private List<CvVehiculo> listVehiculo;
    private String bastidor;
    private String placa;
    private String marca;
    private List<CvVenta> listVenta;
    private CvVenta ventaSeleccionada;
    private boolean mostrarClienteExistente;
    private boolean mostrarCliente;
    private boolean ocultarCliente;
    private CvCliente cliente;
    private CvCliente clienteNuevo;
    private String nit;
    private CvTipoPago selectedTipoPago;
    private List<CvTipoPago> listTipoPago;
    private List<CvExtraVehiculo> listExtraVehiculo;
    private List<CvExtraVehiculo> selectedListExtraVehiculo;
    private Integer totalExtras;

    public RegistroVentaMB() {
        listVenta = new ArrayList<>();
        cliente = new CvCliente();
        clienteNuevo = new CvCliente();
    }

    @PostConstruct
    public void init() {
        listTipoPago = catalogoBean.listAllTipoPago();
        listaExtraVehiculo();
    }

    public void buscarVehiculo() {
        if (bastidor != null && placa != null && marca != null) {
            listVehiculo = vehiculoBeanLocal.ListaVehiculosByBastidorAndPlacaAndMarca(bastidor, placa, marca);
            limpiarCampos();
        } else if (bastidor != null && placa != null) {
            listVehiculo = vehiculoBeanLocal.ListaVehiculosByBastidorAndPlaca(bastidor, placa);
            limpiarCampos();
        } else if (bastidor != null && marca != null) {
            listVehiculo = vehiculoBeanLocal.ListaVehiculosByBastidorAndMarca(bastidor, marca);
            limpiarCampos();
        } else if (placa != null && marca != null) {
            listVehiculo = vehiculoBeanLocal.ListaVehiculosByPlacaAndMarca(placa, marca);
            limpiarCampos();
        } else if (bastidor != null) {
            listVehiculo = vehiculoBeanLocal.ListaVehiculosByBastido(bastidor);
            limpiarCampos();
        } else if (placa != null) {
            listVehiculo = vehiculoBeanLocal.ListaVehiculosByPlaca(placa);
            limpiarCampos();
        } else if (marca != null) {
            listVehiculo = vehiculoBeanLocal.ListaVehiculosByMarca(marca);
            limpiarCampos();
        } else {
            JsfUtil.addErrorMessage("Debe ingresar un filro");
        }

    }

    public void seleccionaVehiculo() {
        if (listSelectedVehiculo == null || listSelectedVehiculo.isEmpty()) {
            JsfUtil.addErrorMessage("Debe Seleccionar un vehículo");
            return;
        }

        CvVenta vent = new CvVenta();
        for (CvVehiculo vehi : listSelectedVehiculo) {
            if (cliente != null) {
                vent.setIdCliente(cliente);
            }
            vent.setTotalExtra(totalExtras);
            vent.setIdVehiculo(vehi);
            vent.setPrecio(vehi.getPrecio());
            listVenta.add(vent);
        }
    }

    public void seleccionaExtraVehiculo() {
        if (selectedListExtraVehiculo == null || selectedListExtraVehiculo.isEmpty()) {
            JsfUtil.addErrorMessage("Debe Seleccionar un extra para el vehículo");
            return;
        }

        totalExtras = 0;
        CvExtraVehiculo extra = new CvExtraVehiculo();
        for (CvExtraVehiculo ext : selectedListExtraVehiculo) {
            totalExtras += ext.getPrecio().intValue();
        }
        JsfUtil.addSuccessMessage("Extra añadido correctamente");
    }

    public void limpiarCampos() {
        bastidor = null;
        marca = null;
        placa = null;
    }

    public void limpiarCampoCliente() {
        nit = null;
    }

    public void visualizarAgregarCliente() {
        mostrarCliente = true;
    }

    public void visualizarAgregarClienteExistente() {
        mostrarClienteExistente = true;
    }

    public void buscarCliente() {
        cliente = ventaBeanLocal.findCliente(nit);
        if (cliente != null) {
            visualizarAgregarClienteExistente();
            limpiarCampoCliente();
            ocultarCliente = true;
        } else {
            visualizarAgregarCliente();
            limpiarCampoCliente();
            ocultarCliente = true;
        }
    }

    public void cancelarAgregarCliente() {
        mostrarCliente = false;
        ocultarCliente = false;
    }

    public void cancelarClienteExistente() {
        mostrarClienteExistente = false;
        ocultarCliente = false;
    }

    public void guardarCliente() {
        clienteNuevo.setUsuarioCreacion(LoginMB.usuario);
        clienteNuevo = ventaBeanLocal.saveCliente(clienteNuevo);
        if (clienteNuevo.getIdCliente() != null) {
            mostrarClienteExistente = true;
            ocultarCliente = true;
            mostrarCliente = false;
            cliente = clienteNuevo;
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
        } else {
            mostrarCliente = false;
            ocultarCliente = true;
            JsfUtil.addErrorMessage("Sucedio un error inesperado");
        }
    }

    public void editarVenta(CvVenta venta) {
        Integer totalVenta = 0;
        Integer totalCantindad = 0;
        Integer totalPedido = 0;
        Integer totalPedidoPositivo = 0;
        CvPedido pedido = new CvPedido();
        Date fechaHoy = new Date();
        CvTipoPedido tipoPedido = new CvTipoPedido();

        totalCantindad = venta.getPrecio() * venta.getCantidad();
        totalVenta = totalCantindad + venta.getTotalExtra();
        venta.setTotal(totalVenta);
        totalPedido = venta.getCantidad() - venta.getIdVehiculo().getStock();
        if (totalPedido < 0) {
            Calendar calFechaEntrega = Calendar.getInstance();
            calFechaEntrega.setTime(fechaHoy);
            calFechaEntrega.add(Calendar.MONTH, 1);
            pedido.setFechaEntrega(calFechaEntrega.getTime());
            
            totalPedidoPositivo = venta.getCantidad() - totalPedido;
            totalPedido = totalPedido * -1;
            pedido.setCantidad(totalPedido);
            tipoPedido = ventaBeanLocal.findTipoPedido(TipoPedidoEnum.FABRICA.getValue());
            pedido.setIdTipoPedido(tipoPedido);

            vehiculoBeanLocal.actualizarStockVehiculo(venta.getIdVehiculo().getIdVehiculo(), 0);
            ventaBeanLocal.savePedido(pedido);
            CvPedido pedidoConcesionario = new CvPedido();
            pedidoConcesionario.setFechaEntrega(fechaHoy);
            pedidoConcesionario.setCantidad(totalPedidoPositivo);
            ventaBeanLocal.savePedido(pedidoConcesionario);

        } else {
            pedido.setFechaEntrega(fechaHoy);
            pedido.setCantidad(totalPedido);
            vehiculoBeanLocal.actualizarStockVehiculo(venta.getIdVehiculo().getIdVehiculo(), totalPedido);
            ventaBeanLocal.savePedido(pedido);
        }
        listVenta.set(0, venta);
    }

    public void listaExtraVehiculo() {
        listExtraVehiculo = vehiculoBeanLocal.listExtraVehiculo();
    }

    public void cancelarRegistro() {
        JsfUtil.redirectTo("/ventas/lista.xhtml");
    }

    public void guardarVenta() {
        CvVenta vent = new CvVenta();
        for (CvVenta v : listVenta) {
            v.setUsuarioCreacion(LoginMB.usuario);
            vent = ventaBeanLocal.saveVentas(v);
        }

        if (vent.getIdVehiculo() != null) {
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
        } else {
            JsfUtil.addErrorMessage("Sucedio un error inesperado");
        }
    }

    /*Metodos getters y setters*/
    public List<CvVehiculo> getListSelectedVehiculo() {
        return listSelectedVehiculo;
    }

    public void setListSelectedVehiculo(List<CvVehiculo> listSelectedVehiculo) {
        this.listSelectedVehiculo = listSelectedVehiculo;
    }

    public List<CvVehiculo> getListVehiculo() {
        return listVehiculo;
    }

    public void setListVehiculo(List<CvVehiculo> listVehiculo) {
        this.listVehiculo = listVehiculo;
    }

    public String getBastidor() {
        return bastidor;
    }

    public void setBastidor(String bastidor) {
        this.bastidor = bastidor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public List<CvVenta> getListVenta() {
        return listVenta;
    }

    public void setListVenta(List<CvVenta> listVenta) {
        this.listVenta = listVenta;
    }

    public CvVenta getVentaSeleccionada() {
        return ventaSeleccionada;
    }

    public void setVentaSeleccionada(CvVenta ventaSeleccionada) {
        this.ventaSeleccionada = ventaSeleccionada;
    }

    public boolean isMostrarClienteExistente() {
        return mostrarClienteExistente;
    }

    public void setMostrarClienteExistente(boolean mostrarClienteExistente) {
        this.mostrarClienteExistente = mostrarClienteExistente;
    }

    public boolean isMostrarCliente() {
        return mostrarCliente;
    }

    public void setMostrarCliente(boolean mostrarCliente) {
        this.mostrarCliente = mostrarCliente;
    }

    public CvCliente getCliente() {
        return cliente;
    }

    public void setCliente(CvCliente cliente) {
        this.cliente = cliente;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public boolean isOcultarCliente() {
        return ocultarCliente;
    }

    public void setOcultarCliente(boolean ocultarCliente) {
        this.ocultarCliente = ocultarCliente;
    }

    public CvCliente getClienteNuevo() {
        return clienteNuevo;
    }

    public void setClienteNuevo(CvCliente clienteNuevo) {
        this.clienteNuevo = clienteNuevo;
    }

    public CvTipoPago getSelectedTipoPago() {
        return selectedTipoPago;
    }

    public void setSelectedTipoPago(CvTipoPago selectedTipoPago) {
        this.selectedTipoPago = selectedTipoPago;
    }

    public List<CvTipoPago> getListTipoPago() {
        return listTipoPago;
    }

    public void setListTipoPago(List<CvTipoPago> listTipoPago) {
        this.listTipoPago = listTipoPago;
    }

    public List<CvExtraVehiculo> getListExtraVehiculo() {
        return listExtraVehiculo;
    }

    public void setListExtraVehiculo(List<CvExtraVehiculo> listExtraVehiculo) {
        this.listExtraVehiculo = listExtraVehiculo;
    }

    public List<CvExtraVehiculo> getSelectedListExtraVehiculo() {
        return selectedListExtraVehiculo;
    }

    public void setSelectedListExtraVehiculo(List<CvExtraVehiculo> selectedListExtraVehiculo) {
        this.selectedListExtraVehiculo = selectedListExtraVehiculo;
    }

}
