/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.dao;

import br.com.pizzaria.modelo.*;
import br.com.utilitario.UtilGerador;
import java.math.BigDecimal;
import java.util.*;
import org.hibernate.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kamilla Faust
 */
public class PedidoDaoImplTest {

    private Session sessao;
    private Pedido pedido;
    private PedidoDao pedidoDao;
    private int numeroPedido;

    public PedidoDaoImplTest() {
        pedidoDao = new PedidoDaoImpl();
    }

    //@Test
    public void testSalvar() {
        System.out.println("pesquisarPorId");
        ClienteDaoImplTest clienteDaoImplTest = new ClienteDaoImplTest(); 
        Cliente cliente = clienteDaoImplTest.buscarClienteBd();
        gerarNumeroPedido();
        pedido = new Pedido(null,
                numeroPedido,
                new BigDecimal(UtilGerador.gerarNumero(3)),
                new Date());
        pedido.setCliente(cliente);

        sessao = HibernateUtil.abrirConexao();
        pedidoDao.salvarOuAlterar(pedido, sessao);
        sessao.close();

        assertNotNull(pedido.getId());
    }

    //@Test
    public void testPesquisarPedidoPorNumero() {
        System.out.println("pesquisarPedidoPorNumero");
        buscarPedidoBd();
        sessao = HibernateUtil.abrirConexao();
        Pedido pedidoBanco = pedidoDao.pesquisarPedidoPorNumero(pedido.getNumero(), sessao);
        sessao.close();

        assertNotNull(pedidoBanco);
        assertNotNull(pedidoBanco.getCliente().getEnderecos().get(0));
    }
    
    @Test
    public void testAlterar(){
        System.out.println("alrerar");
        buscarPedidoBd();
        pedido.setValor_total(new BigDecimal(UtilGerador.gerarNumero(3)).setScale(2));
        sessao = HibernateUtil.abrirConexao();
        pedidoDao.salvarOuAlterar(pedido, sessao);
        sessao.close();
        
        sessao = HibernateUtil.abrirConexao();
        Pedido pedidoAlterado = pedidoDao.pesquisarPedidoPorNumero(pedido.getNumero(), sessao);
        sessao.close();
        
        assertEquals(pedido.getValor_total(), pedidoAlterado.getValor_total());
        
    }

    public Pedido buscarPedidoBd() {
        List<Pedido> pedidos = pesquisarPedidoBd();
        if (pedidos.isEmpty()) {
            testSalvar();
        } else {
            pedido = pedidos.get(0);
        }
        return pedido;
    }

    private void gerarNumeroPedido() {
        List<Pedido> pedidos = pesquisarPedidoBd();
        if (pedidos.isEmpty()) {
            numeroPedido = 1;
        } else {
            int tamanhoLista = pedidos.size();
            numeroPedido = tamanhoLista + 1;
        }
    }

    private List<Pedido> pesquisarPedidoBd() throws HibernateException {
        sessao = HibernateUtil.abrirConexao();
        Query consulta = sessao.createQuery("from Pedido");
        List<Pedido> pedidos = consulta.list();
        sessao.close();
        return pedidos;
    }
}
