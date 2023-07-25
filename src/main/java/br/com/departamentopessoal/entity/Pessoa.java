package br.com.departamentopessoal.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pessoa")
public class Pessoa {

    @ApiModelProperty(value = "Código da pessoa")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(value = "Nome Completo da Pessoa")
    private String Nome;

    @ApiModelProperty(value = "Numero do CPF ou CNPJ")
    private String identificador;

    @ApiModelProperty(value = "CPF ou CNPJ")
    private String tipoIdentificador;

}
