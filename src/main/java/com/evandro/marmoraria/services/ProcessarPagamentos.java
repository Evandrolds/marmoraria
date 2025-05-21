package com.evandro.marmoraria.services;

import com.evandro.marmoraria.repositories.PagamentoStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessarPagamentos {

    private final PagamentoStrategy pagamentoStrategy;
    
    @Autowired
    ProcessarPagamentos(PagamentoStrategy pagamentoStrategy){
       this.pagamentoStrategy = pagamentoStrategy;
   }

   public void processarPagamento(double valor){
        if(pagamentoStrategy == null )
            throw new IllegalStateException("Estratégia de pagamento não definida!");

        pagamentoStrategy.pagar(valor);
    }

}
