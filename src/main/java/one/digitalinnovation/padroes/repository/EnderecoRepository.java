package one.digitalinnovation.padroes.repository;

import one.digitalinnovation.padroes.model.Endereco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, String> {


}
