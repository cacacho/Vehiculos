package concesionario.vehiculos.umg.concesionario.api.enums;

/**
 *
 * @author DELL
 */
public enum TipoPedidoEnum {

    CONCESIONARIO(1),
    FABRICA(2);

    Integer tipoPedido;

    private TipoPedidoEnum(Integer tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public Integer getValue() {
        return this.tipoPedido;
    }

}
