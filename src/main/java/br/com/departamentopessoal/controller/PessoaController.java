package br.com.departamentopessoal.controller;

import br.com.departamentopessoal.entity.Pessoa;
import br.com.departamentopessoal.service.PessoaService;
import br.com.departamentopessoal.repository.PessoaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/api/pessoa")
@Api(description = "Operações de gerenciamento de pessoas")
public class PessoaController {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    PessoaService pessoaService;

    /**
     *
     * Método responsável por cadastrar um objeto do tipo Pessoa.
     *
     * @param pessoa Inserir no body um objeto do tipo Pessoa.
     * @return  Retorna o objeto do tipo Pessoa inserida.
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Insere uma pessoa"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @ApiOperation("Inserir nova pessoa")
    @PostMapping(produces = "application/json", consumes="application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Pessoa inserirPessoa(@RequestBody Pessoa pessoa) {
        return pessoaRepository.save(pessoaService.validaPessoa(pessoa));
    }

    /**
     *
     * Método responsável por listar todos os objetos do tipo Pessoa.
     *
     * @return Retorna uma lista de objetos do tipo Pessoa.
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista todas as pessoas da base"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @ApiOperation("Buscar todas as pessoas")
    @GetMapping(produces = "application/json")
    public List<Pessoa> getPessoas() { return pessoaRepository.findAll(); }

    /**
     *
     * Método responsável por listar um objeto do tipo Pessoa pelo o ID.
     *
     * @param id Inserir um ID no Path.
     * @return Retorna um objeto do tipo Pessoa pelo o ID.
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista uma pessoa"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @ApiOperation("Buscar pessoa por ID")
    @GetMapping(value="/{id}", produces="application/json")
    public Pessoa getPessoaByID(@PathVariable Long id) {
        var pessoalOptional = pessoaRepository.findById(id);
        if(pessoalOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return pessoalOptional.get();
    }

    /**
     *
     * Método responsável por deletar um objeto do tipo Pessoa pelo o ID.
     *
     * @param id Inserir um ID no Path.
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deleta uma pessoa"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @ApiOperation("Deletar pessoa por ID")
    @DeleteMapping(value="/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletePessoaByID(@PathVariable Long id) {
        Pessoa pessoa = getPessoaByID(id);
        pessoaRepository.deleteById(pessoa.getId());
    }

    /**
     *
     * Método responsável por atualizar um objeto do tipo Pessoa pelo o ID.
     *
     * @param id Inserir um ID no Path.
     * @param pessoa Inserir um objeto do tipo Pessoa no body.
     * @return Retorna um objeto do tipo Pessoa atualizado.
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atualiza uma pessoa"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @ApiOperation("Atualizar pessoa por ID")
    @PutMapping(value="/{id}", produces="application/json", consumes="application/json")
    public Pessoa updatePessoaByID(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        Pessoa pessoaUpdate = getPessoaByID(id);
        pessoa.setId(pessoaUpdate.getId());
        return pessoaRepository.save(pessoaService.validaPessoa(pessoa));
    }


}
