package com.evandro.marmoraria.repositories;

import com.evandro.marmoraria.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Evandro
 */

public interface ProdutoRepository extends JpaRepository<Produto, Integer>  {
   
    //A interface JpaRepository precisa de dois parâmetros do tipo Generics: 
    //o primeiro é a entidade JPA que representa a tabela e o segundo é
    //o tipo da chave primária (o mesmo tipo do atributo id).
}
