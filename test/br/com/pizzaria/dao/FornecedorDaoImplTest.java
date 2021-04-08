/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.dao;

import br.com.pizzaria.modelo.Endereco;
import br.com.pizzaria.modelo.Fornecedor;
import br.com.utilitario.UtilGerador;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kamilla Faust
 */
public class FornecedorDaoImplTest {
    
    private Session sessao;
    private FornecedorDao fornecedorDao;
    private Fornecedor fornecedor;
    
    public FornecedorDaoImplTest() {
        fornecedorDao = new FornecedorDaoImpl();
    }

   //@Test
    public void testSalvar() {
        System.out.println("salvar");
        List<Endereco> enderecos = new ArrayList<>();
        fornecedor = new Fornecedor(null,
                UtilGerador.gerarNome(),
                UtilGerador.gerarEmail(),
                UtilGerador.gerarTelefoneFixo(),
                UtilGerador.gerarNumero(5) + "/0001",
                UtilGerador.gerarNumero(3));
        
        for (int i = 0; i < 2; i++) {
            enderecos.add(gerarEndereco());
        }
        fornecedor.setEnderecos(enderecos);
        
        sessao = HibernateUtil.abrirConexao();
        fornecedorDao.salvarOuAlterar(fornecedor, sessao);
        sessao.close();
        
        assertNotNull(fornecedor.getId());
       
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
        endereco.setPessoa(fornecedor);
        return endereco;        
    }

    //@Test
    public void testAlterar() {
        System.out.println("testAlterar");
        buscarFornecedorBd();
        fornecedor.setCnpj("123456789/0001");
        fornecedor.getEnderecos().get(0).setBairro("Ipiranga");
        sessao = HibernateUtil.abrirConexao();
        fornecedorDao.salvarOuAlterar(fornecedor, sessao);
        sessao.close();
        
        sessao = HibernateUtil.abrirConexao();
        Fornecedor fornecedorAlterado = fornecedorDao.pesquisarFornecedorComEndereco(fornecedor.getId(), sessao);
        sessao.close();
        
        assertEquals(fornecedor.getCnpj(), fornecedorAlterado.getCnpj());
        assertEquals(fornecedor.getEnderecos().get(0).getBairro(), fornecedorAlterado.getEnderecos().get(0).getBairro());
    }

    @Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisarPorNome");
        buscarFornecedorBd();
        String nome = fornecedor.getNome();
        int letra = nome.indexOf(" ");
        nome = nome.substring(0, letra);
        
        sessao = HibernateUtil.abrirConexao();
        List<Fornecedor> fornecedores = fornecedorDao.pesquisarPorNome(nome, sessao);
        sessao.close();
        
        assertTrue(!fornecedores.isEmpty());
   
    }

    
    public Fornecedor buscarFornecedorBd(){
        sessao = HibernateUtil.abrirConexao();
        Query consulta = sessao.createQuery("from Fornecedor");
        List<Fornecedor> fornecedores = consulta.list();
        sessao.close();
        if(fornecedores.isEmpty()){
            testSalvar();
        } else {
            fornecedor = fornecedores.get(0);
        }
       return fornecedor;
    }
    
}
