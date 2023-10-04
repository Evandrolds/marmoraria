package com.evandro.marmoraria.repositories;

import com.evandro.marmoraria.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Evandro
 */
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
    
}
