package br.com.departamentopessoal.service;

import br.com.departamentopessoal.entity.Pessoa;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PessoaService {

    public Pessoa validaPessoa(Pessoa pessoa) {

        if(pessoa.getNome() == null || pessoa.getNome().equals(null) || pessoa.getNome().equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Informar Nome", null);
        }

        if(pessoa.getIdentificador() == null || pessoa.getIdentificador().equals(null) || pessoa.getIdentificador().equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Informar o CPF ou CNPJ", null);
        }

        int quantidade = pessoa.getIdentificador().length();

        switch(quantidade) {
            case 11:
                pessoa.setTipoIdentificador("CPF");
                break;

            case 14:
                pessoa.setTipoIdentificador("CNPJ");
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"O formato é invalido");
        }

        return pessoa;
    }


}
