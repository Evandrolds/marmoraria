
package com.evandro.marmoraria.resources;

import com.evandro.marmoraria.entities.Pedido;
import com.evandro.marmoraria.services.PedidoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Evandro
 */
@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private  PedidoService service;
    

    //O Controller é a classe responsável por expor cada URI que estará disponível na API Marmoraria.
    @GetMapping
    public List<Pedido> findAllPedidos() {
        return service.findAllPedidos();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Integer id) {
        return service.findPedidoById(id)
                .map(p -> ResponseEntity.ok().body(p))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/salvar")
    public Pedido savePedido(@RequestBody Pedido ped) {
        return service.savePedido(ped);

    }

//    @PutMapping(path = "/{id}")
//    public ResponseEntity updatePedidosById(@PathVariable("id") Integer id, @RequestBody Pedido pedido) {
//      
//       
//        return service.findPedidoById(id).map(prod -> {
//            LocalDateTime date = LocalDateTime.now();
//            prod.setDataDoPedido(date);
//            prod.setStatusDoPedido(pedido.getStatusDoPedido());
//            Pedido atualizado = service.savePedido(prod);
//            return ResponseEntity.ok().body(atualizado);
//        }).orElse(ResponseEntity.notFound().build());
//    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity deletePedidoById(@PathVariable Integer id) {
        return service.findPedidoById(id).map(prod -> {
            service.deletePedidoById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/quantidade")
    public Long countProdutos() {
        return service.countPedido();
    }
    @GetMapping("/existe/{id}")
    public Boolean existePedidoById(@PathVariable("id") Integer id){
        return service.existePedidoById(id);
    }
}
