package one.digitalinnovation.padroes.service.impl;

import one.digitalinnovation.padroes.model.Cliente;
import one.digitalinnovation.padroes.model.Endereco;
import one.digitalinnovation.padroes.repository.ClienteRepository;
import one.digitalinnovation.padroes.repository.EnderecoRepository;
import one.digitalinnovation.padroes.service.ClienteService;
import one.digitalinnovation.padroes.service.ViaCepService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final ViaCepService viaCepService;

    public ClienteServiceImpl(ClienteRepository clienteRepository, EnderecoRepository enderecoRepository, ViaCepService viaCepService) {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.viaCepService = viaCepService;
    }

    @Override
    public Cliente findById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void create(Cliente cliente) {
        this.saveCliente(cliente);
    }

    @Override
    public void update(Long id, Cliente cliente) {
        Cliente clienteEncontrado = findById(id);
        if (clienteEncontrado != null) {
            this.saveCliente(cliente);
        }
    }

    @Override
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public Iterable<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    private void saveCliente(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }
}

