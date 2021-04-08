/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.modelo;


import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Kamilla Faust
 */
@Entity
@Table (name = "cliente")
@PrimaryKeyJoinColumn (name = "id_pessoa")
public class Cliente extends Pessoa {
    
    private boolean cupom;
    
    @OneToMany (mappedBy = "cliente")
    private List<Pedido> pedidos;
    
    public Cliente() {
    }

    public Cliente(Long id, String nome, String email, String telefone, boolean cupom) {
        super(id, nome, email, telefone);
        this.cupom = cupom;
    }

    public boolean isCupom() {
        return cupom;
    }

    public void setCupom(boolean cupom) {
        this.cupom = cupom;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
  
}
