package com.evandro.marmoraria.entities.enumeracao;

/**
 *
 * @author Evandro
 */
public enum StatusDoPedido {
    AGUARDANDO_PAGAMENTO(1),
    PAGO(2),
    ENVIADO(3),
    DEVOLVIDO(4),
    CANSELADO(4),
    EM_ANDAMENTO(5);
    private final int codigo;

    private StatusDoPedido(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }
    public static StatusDoPedido getStatusDoPedido(int codigo){
        for (StatusDoPedido obj : StatusDoPedido.values()) {
            if(obj.getCodigo()== codigo){
                return obj;
            }
        }
        throw new IllegalArgumentException(" Código do Status do pedido inválido! ");
    }
}
