package com.evandro.marmoraria.repositories;

import com.evandro.marmoraria.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Evandro
 */
@Repository
public interface ClienteRepositoty extends JpaRepository<Cliente, Integer>{
    
}
