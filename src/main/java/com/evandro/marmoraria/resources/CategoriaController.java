package com.evandro.marmoraria.resources;

import com.evandro.marmoraria.entities.Categoria;
import com.evandro.marmoraria.resources.exceptions.EntityNotFoundExceptionMarmor;
import com.evandro.marmoraria.services.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Evandro
 */
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    //O Controller é a classe responsável por expor cada URI que estará disponível na API Marmoraria.
    @GetMapping
    public ResponseEntity<List<Categoria>> findAllCategorias() {
        return service.findAllCategorias();
    }

    @GetMapping(value = {"/{id}"})
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Integer id) {
        Categoria cat = service.findCategoriaById(id);
        return ResponseEntity.ok().body(cat);

    }

    @PostMapping("/cadastrar")
    public Categoria saveCategoria(@RequestBody Categoria prod) {
        return service.saveCategoria(prod).getBody();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Categoria> updateCategoriasById(@PathVariable("id") Integer id, @RequestBody Categoria cat) {
        return service.updateCategoriaById(id, cat);

    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity deleteById(@PathVariable Integer id) {
        service.deleteCategoriaById(id);
        return ResponseEntity.ok().body(id);

    }
    @GetMapping("/contar")
    public Long countCategorias() {
        return service.countCategoria();
    }

    @GetMapping(value = "/existe/{id}")
    public Boolean existeCategoria(@PathVariable("id") Integer id) {
         String caracter = String.valueOf(id);
        if (!caracter.matches("[0-9]")) {
            throw new EntityNotFoundExceptionMarmor(" Invalid id! " + id);
        }
        return service.existeCategoriaById(id);
    }
}
