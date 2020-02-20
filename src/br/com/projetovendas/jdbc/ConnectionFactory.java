/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetovendas.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 *
 * @author ederc
 */
public class ConnectionFactory {
    
    public Connection getConnection(){
     
        try {
            String url = "jdbc:mysql://127.0.0.1/bdvendas";
            String user = "admin";
            String password = "1234";
            
            return DriverManager.getConnection(url, user, password);
            
        } catch (SQLException erro) {
            
            throw new RuntimeException(erro);
            
        }
        
    }

}
