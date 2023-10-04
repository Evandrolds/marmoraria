package com.evandro.marmoraria.entities;

import jakarta.persistence.*;

/**
 *
 * @author Evandro
 */
@Entity
@Table(name = "tbl_Nota_Fiscal")
public class NFCs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String serie;
    private String razaoSocial;
//    @OneToMany(mappedBy = "notaFiscal")
//    private List<Produto> produtos = new ArrayList<>();
    private String cnpj;
    private Integer quantidade;
    private Double valor;

    public NFCs(Integer id, String serie, String razaoSocial, String cnpj, Integer quantidade, Double valor) {
        this.id = id;
        this.serie = serie;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public NFCs() {
    }

    public Integer getId() {
        return id;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

}
