package com.evandro.marmoraria.resources;

import com.evandro.marmoraria.entities.Cliente;
import com.evandro.marmoraria.resources.exceptions.InputMismatchExceptionsMarmor;
import com.evandro.marmoraria.resources.exceptions.VerifyInpuData;
import com.evandro.marmoraria.services.ClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Evandro
 */
@RestController
@RequestMapping({"/clientes"})
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<List<Cliente>> findAllClientes() {
        return service.findAllClientes();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente obj) throws Exception {
        Cliente cli = service.saveCliente(obj);
        return ResponseEntity.ok().body(cli);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Cliente> findClienteById(@PathVariable Integer id) {

        VerifyInpuData.verifyInpuId(id);

        Cliente c = service.findClienteById(id);
        return ResponseEntity.ok().body(c);

    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> deleteClienteById(@PathVariable Integer id) {
        VerifyInpuData.verifyInpuId(id);
        
        service.deletClienteById(id);
        return ResponseEntity.ok(id);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cliente> updateClienteById(@PathVariable("id") Integer id, @RequestBody Cliente cliente) {
        VerifyInpuData.verifyInpuId(id);
        
        ResponseEntity<Cliente> cli = service.updateClienteById(id, cliente);
        return ResponseEntity.ok().body(cli).getBody();

    }

    @GetMapping("/contar")
    public Long conutCliente() {
        return service.countCliente();
    }
}
