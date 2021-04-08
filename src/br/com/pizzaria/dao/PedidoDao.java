/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.dao;

import br.com.pizzaria.modelo.Pedido;
import org.hibernate.*;

/**
 *
 * @author Kamilla Faust
 */
public interface PedidoDao extends BaseDao<Pedido, Long>{
    
    Pedido pesquisarPedidoPorNumero (int numeroPedido, Session sessao) throws HibernateException;
    
}
