
package com.evandro.marmoraria.repositories;

import com.evandro.marmoraria.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Evandro
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
    
}
