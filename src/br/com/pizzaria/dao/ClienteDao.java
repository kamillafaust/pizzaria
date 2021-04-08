/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.dao;

import br.com.pizzaria.modelo.Cliente;
import java.util.List;
import org.hibernate.*;

/**
 *
 * @author Kamilla Faust
 */
public interface ClienteDao extends BaseDao <Cliente, Long> {
    
    List<Cliente> pesquisarPorNome (String nome, Session sessao) throws HibernateException;
    
    Cliente pesquisarClienteComEndereco (Long id, Session sessao) throws HibernateException;
    
    Cliente pesquisarClienteTelefone (String telefone, Session sessao) throws HibernateException;
    
}
