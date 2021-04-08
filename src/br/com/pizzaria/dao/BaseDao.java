/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.dao;

import org.hibernate.*;

/**
 * @author Kamilla Faust
 * @param <T>
 * @param <ID>
 */
public interface BaseDao<T, ID> {
    
    void salvarOuAlterar(T entidade, Session sessao)throws HibernateException;
    
    void excluir(T entidade, Session sessao)throws HibernateException;
    
    //aqui retorna a Entidade (T) q foi pesquisada
    T pesquisarPorId(ID id, Session sessao)throws HibernateException; 
    
}
