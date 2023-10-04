package com.evandro.marmoraria.entities;

import com.evandro.marmoraria.entities.enumeracao.StatusDoPedido;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Evandro
 */
@Entity
@Table(name = "tbl_Pedido")
public final class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataDoPedido;
    private Integer statusDoPedido;

    // Quando é um relacionamento 1 para 1, mapea-se as duas entidade para se ter o mesmo 
    // E é obrigatório se usar CascadeType
    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Pagamento pagamento;
    
  
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    
    @ManyToMany(mappedBy = "pedidos")
    private List<Produto> produtos = new ArrayList<>();

    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemDoPedido> itens = new HashSet<>();

    public Pedido(Integer id, LocalDateTime dataDoPedido, StatusDoPedido statusDoPedido, Cliente cliente) {
        this.id = id;
        this.dataDoPedido = dataDoPedido;
        this.cliente = cliente;
        setStatusDoPedido(statusDoPedido);

    }

    public Pedido() {
    }

    @JsonIgnore
    public List<Produto> getProdutos() {
        return produtos;
    }
    
    public Pagamento getPagamento() {
        return this.pagamento;
    }

    //@JsonIgnore
    public Set<ItemDoPedido> getItensDoPedido() {
        return this.itens;
    }
   

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

 
    public Cliente getCliente() {
        return cliente;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getDataDoPedido() {
        return dataDoPedido;
    }

    public void setDataDoPedido(LocalDateTime dataDoPedido) {
        this.dataDoPedido = dataDoPedido;
    }

    public StatusDoPedido getStatusDoPedido() {
        //Pegando o metodo estatico que foi criado lá da Enum e passando o getStatus no paramentro do metodo
        return StatusDoPedido.getStatusDoPedido(statusDoPedido);
    }

    public void setStatusDoPedido(StatusDoPedido statusDoPedido) {
        if (statusDoPedido != null) {
            //Pegando o status pelo código lá da Enum
            this.statusDoPedido = statusDoPedido.getCodigo();
        }
    }

    public Double getTotal() {
        double soma = 0.0;
        for (ItemDoPedido p : itens) {
            soma += p.getSubTotal();
        }
        return soma;
    }
}
