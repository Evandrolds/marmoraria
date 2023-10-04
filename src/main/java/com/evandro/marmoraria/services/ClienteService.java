package com.evandro.marmoraria.services;

import com.evandro.marmoraria.entities.Cliente;
import java.util.List;
import org.springframework.stereotype.Service;
import com.evandro.marmoraria.repositories.ClienteRepositoty;
import com.evandro.marmoraria.resources.exceptions.EntityNotFoundExceptionMarmor;
import com.evandro.marmoraria.resources.exceptions.IllegalArgumentExceptionMarmor;
import com.evandro.marmoraria.resources.exceptions.InputMismatchExceptionsMarmor;
import com.evandro.marmoraria.resources.exceptions.VerifyInpuData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Evandro
 */
@Service
public class ClienteService {

    @Autowired
    public ClienteRepositoty repository;

    public Cliente saveCliente(Cliente obj) throws Exception {
        VerifyInpuData.verifyClienteData(obj);
        return repository.save(obj);
    }

    public ResponseEntity< List<Cliente>> findAllClientes() {
        List<Cliente> cli = repository.findAll();
        if (cli.isEmpty() || cli.size() <= 0) {
            throw new EntityNotFoundExceptionMarmor("The claent is null !");
        }
        return ResponseEntity.ok().body(cli);
    }
    
    public Cliente findClienteById(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundExceptionMarmor("Id not found -> " + id));
    }

    public ResponseEntity<Cliente> updateClienteById(Integer id, Cliente cliente) {
         VerifyInpuData.verifyInpuId(id);
         
        return repository.findById(id).map(m -> {
            m.setCliente(cliente.getCliente());
            m.setCpf(cliente.getCpf());
            m.setEmail(cliente.getEmail());
            m.setTelefone(cliente.getTelefone());
            Cliente atualizado = repository.save(m);
            
            return ResponseEntity.ok().body(atualizado);
            
        }).orElseThrow(() -> new EntityNotFoundExceptionMarmor("Id Clinete not found!"));
    }

    public ResponseEntity<?> deletClienteById(Integer id) throws InputMismatchExceptionsMarmor {
        VerifyInpuData.verifyInpuId(id);
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().body(id);
                }).orElseThrow(() -> new EntityNotFoundExceptionMarmor(" Id client not found! "));
    }

    public Long countCliente() {
        return repository.count();
    }

    public Boolean ClinteExiste(Integer id) throws InputMismatchExceptionsMarmor {
        if (id == null || id <= 0) {
            throw new EntityNotFoundExceptionMarmor("Id claint invalid !");
        }
        return repository.existsById(id);
    }

}
