package com.evandro.marmoraria.services;

import com.evandro.marmoraria.entities.Categoria;
import com.evandro.marmoraria.entities.ItemDoPedido;
import com.evandro.marmoraria.entities.Pedido;
import com.evandro.marmoraria.entities.Produto;
import com.evandro.marmoraria.repositories.ItemDoPedidoRepository;
import com.evandro.marmoraria.resources.exceptions.EntityNotFoundExceptionMarmor;
import com.evandro.marmoraria.resources.exceptions.InputMismatchExceptionsMarmor;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Evandro
 */
@Service
public class ItemDoPedidoService {

    @Autowired
    private ItemDoPedidoRepository repository;
    
    public List<ItemDoPedido> findAllItemDoPedidos() {
        return repository.findAll();
    }


//    public ItemDoPedido saveItemDoPedido(ItemDoPedido obj){
//         if (obj. || obj.getCliente().isBlank()) {
//            throw new InputNotFoundExceptionsMarmor("The client can't be null !");
//
//        }
//        return repository.save(obj);
//    }
    public Optional<ItemDoPedido> findItemDoPedidoById(Integer id) throws InputMismatchExceptionsMarmor {
        if(id == null||id <= 0){
            throw new EntityNotFoundExceptionMarmor("Invalid id! " + id);
        }
        return repository.findById(id);
    }

    public ResponseEntity<?> deleteItemDoPedidoById(Integer id) {
        return repository.findById(id).map(m -> {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new EntityNotFoundExceptionMarmor("id not found!"));
       
    }

    public Long countItemDoPedido() {
        return repository.count();
    }

    public ResponseEntity<ItemDoPedido> updateItemDoPediByd(Integer id,ItemDoPedido itemPedido) {
        
       return repository.findById(id)
                .map(item -> {
                item.setQuantidade(item.getQuantidade());
                item.setPreco(item.getPreco());
                ItemDoPedido p = repository.save(item);
                return ResponseEntity.ok().body(p);}).orElseThrow(() -> new EntityNotFoundExceptionMarmor("Entity not found! "));
       
    }

    public Boolean existeItemDoPedidoById(Integer id) {
        return repository.existsById(id);
    }
    
}
