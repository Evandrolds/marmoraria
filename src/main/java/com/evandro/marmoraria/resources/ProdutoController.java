package com.evandro.marmoraria.resources;

import com.evandro.marmoraria.entities.Produto;
import com.evandro.marmoraria.resources.exceptions.EntityNotFoundExceptionMarmor;
import com.evandro.marmoraria.resources.exceptions.InputMismatchExceptionsMarmor;
import com.evandro.marmoraria.services.ProdutoService;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Evandro
 */
@RestController
@RequestMapping({"/produtos"})
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    //O Controller é a classe responsável por expor cada URI que estará disponível na API Marmoraria.
    @GetMapping
    public ResponseEntity<List<Produto>> findAllProdutos() {
        return service.findAllProdutos();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Produto> getProdutoById(@PathVariable Integer id) throws InputMismatchExceptionsMarmor {
        return service.findProdutosById(id);
    }

    @PostMapping("/cadastrar")
    public Produto salvarProduto(@RequestBody Produto prod) {
        return service.saveProdutos(prod);

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Produto> updateProdutosById(@PathVariable("id") Integer id, @RequestBody Produto produto) throws InputMismatchExceptionsMarmor {

        return service.findProdutosById(id);
         
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity deleteById(@PathVariable Integer id) throws InputMismatchExceptionsMarmor {
        return service.findProdutosById(id);
    }

    @GetMapping("/contar")
    public Long countProdutos() {
        return service.countProduto();
    }

    @GetMapping("/existe/{id}")
    public Boolean existeProduto(@PathVariable("id") Integer id) {
        return service.existsProdutosById(id);
    }
}
