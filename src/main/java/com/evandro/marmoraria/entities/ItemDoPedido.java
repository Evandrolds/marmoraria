package com.evandro.marmoraria.entities;

import com.evandro.marmoraria.bk.ItemDePedidoPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Evandro
 */
@Entity
@Table(name = "tb_Item_Pedido")
public class ItemDoPedido implements Serializable{

    @EmbeddedId
    private ItemDePedidoPK id = new ItemDePedidoPK();
    private Integer quantidade;
    private Double preco;


    public ItemDoPedido(Pedido pedido, Produto produto, Integer quantidade, Double preco) {
        this.quantidade = quantidade;
        this.preco = preco;
        id.setPedido(pedido);
        id.setProduto(produto);
    }

    public Double getPreco() {
        return preco;
    }
    public ItemDoPedido() {
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
   
    //@JsonIgnore
    public Produto getProduto() {
        return id.getProduto();
    }

    public void setProduto(Produto produto) {
        id.setProduto(produto);
    }

    @JsonIgnore
    public Pedido getPedido() {
        return id.getPedido();
    }

    public void setPedido(Pedido pedido) {
        id.setPedido(pedido);
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }


    public Double getSubTotal() {
        return preco * quantidade;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemDoPedido other = (ItemDoPedido) obj;
        return Objects.equals(this.id, other.id);
    }

}
