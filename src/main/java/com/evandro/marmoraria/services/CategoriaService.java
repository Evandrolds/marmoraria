package com.evandro.marmoraria.services;

import com.evandro.marmoraria.entities.Categoria;
import com.evandro.marmoraria.resources.exceptions.EntityNotFoundExceptionMarmor;
import com.evandro.marmoraria.repositories.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Evandro
 */
@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public ResponseEntity<Categoria> saveCategoria(Categoria cat) {
        if (!cat.getNome().isBlank() || !cat.getNome().isBlank()) {
            throw new EntityNotFoundExceptionMarmor(" The Categoria " + cat.getNome() + "already exist!");

        }
        repository.save(cat);
        return ResponseEntity.ok().body(cat);
    }

    public ResponseEntity<List<Categoria>> findAllCategorias() {
        List<Categoria> cat = repository.findAll();
        if (cat.isEmpty() || cat.size() <= 0) {
            throw new EntityNotFoundExceptionMarmor("Categorias not found! " + cat);
        }
        return ResponseEntity.ok().body(cat);
    }

    public ResponseEntity<Categoria> updateCategoriaById(Integer id, Categoria newCat) throws EntityNotFoundExceptionMarmor {
        return repository.findById(id).map(m -> {
            m.setNome(newCat.getNome());
            Categoria cat = repository.save(m);
            return ResponseEntity.ok().body(cat);
        }).orElseThrow(() -> new EntityNotFoundExceptionMarmor(id + "Id not found!"));

    }

    public Categoria findCategoriaById(Integer id) throws EntityNotFoundExceptionMarmor {

        return repository.findById(id).orElseThrow(() -> new EntityNotFoundExceptionMarmor("Id not found!"));
    }

    public ResponseEntity<?> deleteCategoriaById(Integer id) throws EntityNotFoundExceptionMarmor {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().body(id);
                }).orElseThrow(() -> new EntityNotFoundExceptionMarmor(" Id not exist! "));

    }

    public Long countCategoria() {
        return repository.count();
    }

    public Boolean existeCategoriaById(Integer id) {
        String caracter = String.valueOf(id);
        if (caracter.matches("[0-9]")) {
            throw new IllegalArgumentException("Invalid id!");
        }

        return repository.existsById(id);

    }

}
