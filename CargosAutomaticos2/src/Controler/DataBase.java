package Controler;

import java.sql.*;
import javax.swing.JOptionPane;

public class DataBase {
        
    private Connection theConnection;
    
    public void OpenConecctionToDatabase(){
        try { 
            String theDriver = "com.mysql.jdbc.Driver"; 
            Class.forName(theDriver).newInstance(); 
            
            String theUrl = "jdbc:mysql://localhost:3306/"; 
            String theDatabaseName = "cargosAutomaticos"; 
            String theUserName = "root"; 
            String thePassword = "root"; 
            theConnection = DriverManager.getConnection(theUrl+theDatabaseName,theUserName,thePassword); 
        } catch (Exception e) { 
            JOptionPane.showMessageDialog(null,e);
        } 
    }
    
    public void CloseConecctionToDatabase(){
        try{
            theConnection.close(); 
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public String GetInformationFromDatabase(String theQuery){
        try {
            Statement theStatement = theConnection.createStatement();
            ResultSet theResult = theStatement.executeQuery(theQuery);
            
            String theInformation = "";
            while(theResult.next()){
                theInformation += theResult.getString("cedulaCliente");
            }
            return theInformation;
        } catch (SQLException theException) {
            return "Error: " + theException;
        }
    }
    
    public boolean ValidateThePayment(String theCardNumber, String theCVV){
        try {
            String theQuery = "SELECT numTarjetaCliente, CVV "
                            + "FROM cliente "
                            + "WHERE numTarjetaCliente='" + theCardNumber 
                            + "' AND CVV='" + theCVV + "';";
            Statement theStatement = theConnection.createStatement();
            ResultSet theResult = theStatement.executeQuery(theQuery);
            
            while(theResult.next()){
                if(theResult.getString("numTarjetaCliente").equalsIgnoreCase(theCardNumber)
                        && theResult.getString("CVV").equals(theCVV)){
                    return true;
                } else {
                    return false;
                }  
            }
            return false;
        } catch (SQLException theException) {
            return false;
        }
    }
}
