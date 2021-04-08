/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.dao;

import br.com.pizzaria.modelo.Cliente;
import java.io.Serializable;
import java.util.List;
import org.hibernate.*;

/**
 *
 * @author Kamilla Faust
 */
public class ClienteDaoImpl extends BaseDaoImpl<Cliente, Long> implements ClienteDao, Serializable {

    @Override
    public Cliente pesquisarPorId(Long id, Session sessao) throws HibernateException {
        return (Cliente) sessao.get(Cliente.class, id);
    }

    @Override
    public List<Cliente> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("from Cliente c join fetch c.enderecos where nome like :nomeHQL");
        consulta.setParameter("nomeHQL", "%" + nome + "%");
        return consulta.list();
    }

    @Override
    public Cliente pesquisarClienteComEndereco(Long id, Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("from Cliente c join fetch c.enderecos where c.id = :idHQL");
        consulta.setParameter("idHQL", id);
        return (Cliente) consulta.uniqueResult();
    }

    @Override
    public Cliente pesquisarClienteTelefone(String telefone, Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("from Cliente c join fetch c.pedidos where telefone = :tel");
        consulta.setParameter("tel", telefone);
        return (Cliente) consulta.uniqueResult();
    }

   

}
