package com.evandro.marmoraria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Evandro
 */
@Entity
@Table(name = "tbl_Produto")
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    @Column(nullable = false, length = 30)
    private String nome;
    @Column(nullable = false, length = 60)
    private String descricao;
    private Double preco;

    @ManyToMany
    @JoinTable(name = "tb_Categoria_Produtos", joinColumns = @JoinColumn(name = "produto_Fk"), inverseJoinColumns = @JoinColumn(name = "categoria_Fk"))
    private Set<Categoria> categorias = new HashSet<>();
//    @OneToOne
//    @JoinColumn(name = "Nota_do_Produto")
//    private NFCs notaFiscal;

    @OneToMany(mappedBy = "id.produto")
    private Set<ItemDoPedido> itens = new HashSet<>();

    // percorrendo o list de Intens e pegando o predio associado a ele
    // Esse anotation é para não gerar um loop por se tratar de relacionamento de mao dupla
    //@JsonIgnore
    @JsonIgnore
    public Set<Pedido> getPedidos() {
        Set<Pedido> set = new HashSet<>();
        for (ItemDoPedido obj : itens) {
            set.add(obj.getPedido());
        }
        return set;
    }

    @ManyToMany
    @JoinTable(name = "tb_Pedidos_Produtos",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "pedido_id"))
    private List<Pedido> pedidos = new ArrayList<>();

    public Produto(Integer codigo, String nome, String descricao, Double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;

    }

    public Produto() {
    }

    public Double getPreco() {
        return this.preco;
    }

    public String getNome() {
        return this.nome;
    }

    public Integer getCodigo() {
        return this.codigo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<Categoria> getCategorias() {
        return this.categorias;
    }

    @JsonIgnore
    public Set<ItemDoPedido> getItensDoPedido() {
        return this.itens;
    }
}
