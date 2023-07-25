package br.com.departamentopessoal.repository;

import br.com.departamentopessoal.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
