package com.evandro.marmoraria.entities;

import com.evandro.marmoraria.repositories.PagamentoStrategy;

public class PagamentoCartao implements PagamentoStrategy {

    @Override
    public void pagar(double valor){
        System.out.println("Pagamento no carão credito no valor de R$" + String.format("%.2f" +valor)+ "feito com sucesso!");
    }
}
