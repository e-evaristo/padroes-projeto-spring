package one.digitalinnovation.padroes.service;

import one.digitalinnovation.padroes.model.Cliente;

public interface ClienteService {

    Cliente findById(Long id);

    void create(Cliente cliente);

    void update(Long id, Cliente cliente);

    void delete(Long id);

    Iterable<Cliente> findAll();

}
