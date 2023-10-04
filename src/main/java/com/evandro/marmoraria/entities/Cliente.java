package com.evandro.marmoraria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Evandro
 */
@Entity
@Table(name = "tbl_Cliente")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50,nullable = false)
    private String cliente;
    @Column(length = 15,nullable = false,unique = true)
    private String cpf;
    @Column(length = 50,nullable = false)
    private String telefone;
    @Column(length = 16,nullable = false)
    private String email;
    
    public Cliente(Integer id, String cliente, String cpf, String telefone, String email) {
        this.id = id;
        this.cliente = cliente;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
    }

    public Cliente() {
    }

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setNome(String cliente) {
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public String getCliente() {
        return cliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Cliente other = (Cliente) obj;
        return Objects.equals(this.id, other.id);
    }

}
