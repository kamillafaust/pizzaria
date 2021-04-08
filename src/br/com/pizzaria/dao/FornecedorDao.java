/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.dao;

import br.com.pizzaria.modelo.Fornecedor;
import java.util.List;
import org.hibernate.*;

/**
 *
 * @author Kamilla Faust
 */
public interface FornecedorDao extends BaseDao<Fornecedor, Long>{
    
    Fornecedor pesquisarFornecedorComEndereco (Long id, Session sessao) throws HibernateException;
    
    List<Fornecedor> pesquisarPorNome (String nome, Session sessao) throws HibernateException;
    
}
