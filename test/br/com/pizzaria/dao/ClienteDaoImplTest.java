/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.dao;

import br.com.pizzaria.modelo.*;
import br.com.utilitario.*;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kamilla Faust
 */
public class ClienteDaoImplTest {
    
    private Cliente cliente;
    private ClienteDao clienteDao;
    private Session sessao;
    
    public ClienteDaoImplTest() {
        clienteDao = new ClienteDaoImpl();
    }

    //@Test
    public void testSalvar() {
        System.out.println("salvar");
        List<Endereco> enderecos = new ArrayList<>();
        
        cliente = new Cliente(null,
                UtilGerador.gerarNome(),
                UtilGerador.gerarEmail(),
                UtilGerador.gerarTelefoneFixo(),
                true);
        
        for (int i = 0; i < 2; i++) {
            enderecos.add(gerarEndereco());
        }
        
        cliente.setEnderecos(enderecos);
        sessao = HibernateUtil.abrirConexao();
        clienteDao.salvarOuAlterar(cliente, sessao);
        
        assertNotNull(cliente.getId());
        assertNotNull(cliente.getEnderecos().get(0).getId());
        
    }
    
    private Endereco gerarEndereco() {
        Endereco endereco = new Endereco(null,
                UtilGerador.gerarCaracter(8),
                UtilGerador.gerarNumero(8),
                UtilGerador.gerarNumero(2),
                "centro",
                UtilGerador.gerarCidade(),
                UtilGerador.gerarCaracter(5),
                "casa amarela");
        
        endereco.setPessoa(cliente);
        return endereco;
    }

    //@Test
    public void testAlterar() {
        System.out.println("alterar");
        buscarClienteBd();
        cliente.setNome(UtilGerador.gerarNome());
        cliente.getEnderecos().get(0).setCep("88111-380");
        sessao = HibernateUtil.abrirConexao();
        clienteDao.salvarOuAlterar(cliente, sessao);
        sessao.close();
        
        sessao = HibernateUtil.abrirConexao();
        Cliente clienteAlterado = clienteDao.pesquisarClienteComEndereco(cliente.getId(), sessao);
        sessao.close();
        
        assertEquals(cliente.getNome(), clienteAlterado.getNome());
        assertEquals(cliente.getEnderecos().get(0).getCep(), clienteAlterado.getEnderecos().get(0).getCep());
    }

    //@Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorIdComEndereco");
        buscarClienteBd();
        
        sessao = HibernateUtil.abrirConexao();
        Cliente clientePesquisado = clienteDao.pesquisarClienteComEndereco(cliente.getId(), sessao);
        sessao.close();
        assertNotNull(clientePesquisado);
    }
    
    //@Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisarPorNome");
        buscarClienteBd();
        String nome = cliente.getNome();
        int letra = nome.indexOf(" ");
        nome = nome.substring(0, letra);
        
        sessao = HibernateUtil.abrirConexao();
        List<Cliente> clientes = clienteDao.pesquisarPorNome(nome, sessao);
        sessao.close();
        
        assertTrue(!clientes.isEmpty());
    }
    
    //@Test
    public void testPesquisarClienteTelefone() {
        System.out.println("pesquisarClienteTelefone");
        buscarClienteBd();
        sessao = HibernateUtil.abrirConexao();
        Cliente clienteTelefone = clienteDao.pesquisarClienteTelefone(cliente.getTelefone(), sessao);
        sessao.close();
        
        assertNotNull(clienteTelefone);
        assertNotNull(clienteTelefone.getEnderecos().get(0));
    }
    
    public Cliente buscarClienteBd() {
        sessao = HibernateUtil.abrirConexao();
        Query consulta = sessao.createQuery("from Cliente");
        List<Cliente> clientes = consulta.list();
        sessao.close();
        if (clientes.isEmpty()) {
            testSalvar();
        } else {
            cliente = clientes.get(0);
        }
        return cliente;
    }   
}
