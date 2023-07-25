package br.com.departamentopessoal.controller;

import br.com.departamentopessoal.entity.Pessoa;
import br.com.departamentopessoal.service.PessoaService;
import br.com.departamentopessoal.repository.PessoaRepository;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    PessoaService pessoaService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Insere uma pessoa"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping(produces = "application/json", consumes="application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Pessoa inserirPessoa(@RequestBody Pessoa pessoa) {
        return pessoaRepository.save(pessoaService.validaPessoa(pessoa));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista todas as pessoas da base"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping(produces = "application/json")
    public List<Pessoa> getPessoas() { return pessoaRepository.findAll(); }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista uma pessoa"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping(value="/{id}", produces="application/json")
    public Pessoa getPessoaByID(@PathVariable Long id) {
        var pessoalOptional = pessoaRepository.findById(id);
        if(pessoalOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return pessoalOptional.get();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deleta uma pessoa"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @DeleteMapping(value="/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletePessoaByID(@PathVariable Long id) {
        Pessoa pessoa = getPessoaByID(id);
        pessoaRepository.deleteById(pessoa.getId());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atualiza uma pessoa"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PutMapping(value="/{id}", produces="application/json", consumes="application/json")
    public Pessoa updatePessoaByID(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        Pessoa pessoaUpdate = getPessoaByID(id);
        pessoa.setId(pessoaUpdate.getId());
        return pessoaRepository.save(pessoaService.validaPessoa(pessoa));
    }


}
