/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class GeradorTabela {
    public static void main(String[] args) {  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PizzariaPU");
        emf.close();
    }
}
