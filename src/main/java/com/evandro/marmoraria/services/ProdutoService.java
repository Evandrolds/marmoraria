package com.evandro.marmoraria.services;

import com.evandro.marmoraria.entities.Categoria;
import com.evandro.marmoraria.entities.Produto;
import com.evandro.marmoraria.repositories.CategoriaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import com.evandro.marmoraria.repositories.ProdutoRepository;
import com.evandro.marmoraria.resources.exceptions.EntityNotFoundExceptionMarmor;
import com.evandro.marmoraria.resources.exceptions.InputMismatchExceptionsMarmor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Evandro
 */
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;
   

    public Produto saveProdutos(Produto prod) {
        return repository.save(prod);
    }

    public ResponseEntity<List<Produto>> findAllProdutos() {
        List<Produto> pords = repository.findAll();
        return ResponseEntity.ok().body(pords);
    }

    public ResponseEntity<Produto> findProdutosById(Integer id) throws InputMismatchExceptionsMarmor {

        Produto p = repository.findById(id).orElseThrow(() -> new InputMismatchExceptionsMarmor("Id not found"));
        return ResponseEntity.ok().body(p);
    }

    public boolean existsProdutosById(Integer id) {
        return repository.existsById(id);
    }

    public Long countProduto() {

        return repository.count();
    }

    public Produto deleteProdutosById(Integer id) throws InputMismatchExceptionsMarmor {
        if(id == null || id <= 0){
            throw new InputMismatchExceptionsMarmor("Id not found!");
        }
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundExceptionMarmor("Enetity not found!"));
    }

    public ResponseEntity<Produto> updateProdutosById(Integer id, Produto produto,Categoria cat) {


        return repository.findById(id)
                .map(record -> {
                    record.setNome(produto.getNome());
                    record.setDescricao(produto.getDescricao());
                    record.setPreco(produto.getPreco());
                   
                    Produto updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElseThrow(() -> new EntityNotFoundExceptionMarmor("Enetity not foind!"));

    }

}
