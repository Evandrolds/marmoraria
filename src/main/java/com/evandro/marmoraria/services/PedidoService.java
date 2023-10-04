package com.evandro.marmoraria.services;

import com.evandro.marmoraria.entities.Pedido;
import com.evandro.marmoraria.repositories.PedidoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Evandro
 */
@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repository;

    public List<Pedido> findAllPedidos() {
        return repository.findAll();
    }
    
    
    public Pedido savePedido(Pedido obj){
        return repository.save(obj);
    }
  
    public Optional<Pedido> findPedidoById(Integer id){
        return repository.findById(id);
    }
    public void deletePedidoById(Integer id){
        repository.deleteById(id);
    }
    public Long countPedido(){
        return repository.count();
    }
    public Pedido updatePediByd(Pedido pedido){
        if(pedido == null){
        return null;
        }
        repository.findById(pedido.getId());
        return repository.save(pedido);
    }
    public Boolean existePedidoById(Integer id){
        return repository.existsById(id);
    }
    
}
