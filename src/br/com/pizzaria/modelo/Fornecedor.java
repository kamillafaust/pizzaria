/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.modelo;

import javax.persistence.*;

/**
 *
 * @author Kamilla Faust
 */

@Entity
@Table (name = "fornecedor")
@PrimaryKeyJoinColumn (name = "id_pessoa")
public class Fornecedor extends Pessoa {
    
    private String cnpj;
    private String inscricao_estadual;

    public Fornecedor() {
    }

    public Fornecedor(Long id, String nome, String email, String telefone, String cnpj, String inscricao_estadual) {
        super(id, nome, email, telefone);
        this.cnpj = cnpj;
        this.inscricao_estadual = inscricao_estadual;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricao_estadual() {
        return inscricao_estadual;
    }

    public void setInscricao_estadual(String inscricao_estadual) {
        this.inscricao_estadual = inscricao_estadual;
    }
  
}
