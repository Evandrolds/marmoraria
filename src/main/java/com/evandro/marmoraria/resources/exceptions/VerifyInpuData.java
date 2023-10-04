package com.evandro.marmoraria.resources.exceptions;

import com.evandro.marmoraria.entities.Cliente;

/**
 *
 * @author Evandro
 */
public class VerifyInpuData {

    public static Integer verifyInpuId(Integer id) {
        String number = String.valueOf(id);
        boolean numero = number.matches("\\d");
        if (!numero) {
            throw new IllegalArgumentException("You must enter integer numbers!");
        }
        int n = Integer.parseInt(number);
        return n;
    }

    public static Cliente verifyClienteData(Cliente obj) {
        if (obj.getCliente().isEmpty() || obj.getCliente().isBlank()) {
            throw new EntityNotFoundExceptionMarmor("The client can't be null !");
        }
        if (obj.getCpf().isBlank() || obj.getCpf().isEmpty()) {
            throw new EntityNotFoundExceptionMarmor("The zip code can't be null !");
        }
        if (obj.getEmail().isBlank() || obj.getEmail().isEmpty()) {
            throw new EntityNotFoundExceptionMarmor("The email can't be null !");

        }
        if (obj.getTelefone().isBlank() || obj.getTelefone().isEmpty()) {
            throw new EntityNotFoundExceptionMarmor("The phone can't be null !");

        }
        return obj;
    }
}
