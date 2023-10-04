
package com.evandro.marmoraria.resources;

import com.evandro.marmoraria.entities.ItemDoPedido;
import com.evandro.marmoraria.resources.exceptions.EntityNotFoundExceptionMarmor;
import com.evandro.marmoraria.resources.exceptions.InputMismatchExceptionsMarmor;
import com.evandro.marmoraria.services.ItemDoPedidoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Evandro
 */
@RestController
@RequestMapping("/itens")
public class ItemDoPedidoController {
    @Autowired
    private  ItemDoPedidoService service;
    

    //O Controller é a classe responsável por expor cada URI que estará disponível na API Marmoraria.
    @GetMapping
    public List<ItemDoPedido> findAllItemDoPedidos() {
        return service.findAllItemDoPedidos();
    }

    @GetMapping(path = {"/{id}"})
    public ItemDoPedido getItemDoPedidoById(@PathVariable Integer id) throws InputMismatchExceptionsMarmor  {
       return service.findItemDoPedidoById(id).orElseThrow(()-> new EntityNotFoundExceptionMarmor("id not found!"));
               
                
    }
//
//    @PostMapping("/salvar")
//    public ItemDoPedido saveItemDoPedido(@RequestBody ItemDoPedido item, Pedido pedido,Produto prod) {
//       ItemDoPedido itens = new ItemDoPedido(pedido,prod , item.getQuantidade(), item.getPreco());
//        return service.saveAllItemDoPedido(item, pedido, prod);
//
//    }

//    @PutMapping(path = "/{id}")
//    public ResponseEntity updateItemDoPedidosById(@PathVariable("id") Integer id, @RequestBody ItemDoPedido ItemDoPedido) {
//      
//       
//        return service.findItemDoPedidoById(id).map(prod -> {
//            LocalDateTime date = LocalDateTime.now();
//            prod.setDataDoItemDoPedido(date);
//            prod.setStatusDoItemDoPedido(ItemDoPedido.getStatusDoItemDoPedido());
//            ItemDoPedido atualizado = service.saveItemDoPedido(prod);
//            return ResponseEntity.ok().body(atualizado);
//        }).orElse(ResponseEntity.notFound().build());
//    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity deleteItemDoPedidoById(@PathVariable Integer id) {
        return ResponseEntity.ok(id);
    }

    @GetMapping(path = {"/count"})
    public Long countProdutos() {
        return service.countItemDoPedido();
    }
    @GetMapping("/exist/{id}")
    public Boolean existeItemDoPedidoById(@PathVariable("id") Integer id){
        return service.existeItemDoPedidoById(id);
    }
}
