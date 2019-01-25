/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign2example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author c0621483
 */
public class Assign2Example {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String name;
        Scanner kb = new Scanner(System.in);
        System.out.println("Give me a manufacturer name: ");
        name = kb.nextLine();
        
        String sql1 = "SELECT MANUFACTURER_ID FROM MANUFACTURER WHERE NAME = ?";
        
        String sql2 = "SELECT PRODUCT_ID , DESCRIPTION FROM PRODUCT"
                + " WHERE MANUFACTURER_ID = ? ORDER BY DESCRIPTION ASC";
       
      
        
     
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        
            
              String jdbc ="jdbc:derby://localhost:1527/sample";
              
              Connection conn = DriverManager.getConnection(jdbc,"app","app");
               
              PreparedStatement pstmtManufacturer = conn.prepareStatement(sql1);
              pstmtManufacturer.setString(1, name);
              ResultSet rs = pstmtManufacturer.executeQuery();
              while (rs.next()){
                  int id = rs.getInt("MANUFACTURER_ID");
                  PreparedStatement pstmtProduct = conn.prepareStatement(sql2);
                  pstmtProduct.setInt(1,id);
                  ResultSet rsProduct = pstmtProduct.executeQuery();
                  while (rsProduct.next()){
                      System.out.printf("%s: %s\n",
                                rsProduct.getString("PRODUCT_ID"),
                                rsProduct.getString("DESCRIPTION"));
                  }
                  
              }
              } catch (ClassNotFoundException ex) {
            Logger.getLogger(Assign2Example.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Assign2Example.class.getName()).log(Level.SEVERE, null, ex);
        }
              
           
       
      
       
        
        
                
    
}
}
