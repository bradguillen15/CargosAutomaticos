package Controler;

import java.sql.*;
import javax.swing.JOptionPane;

public class DataBase {

    private Connection theConnection;

    public void OpenConecctionToDatabase() {
        try {
            String theDriver = "com.mysql.jdbc.Driver";
            Class.forName(theDriver).newInstance();

            String theUrl = "jdbc:mysql://localhost:3306/";
            String theDatabaseName = "cargosAutomaticos";
            String theUserName = "root";
            String thePassword = "root";
            theConnection = DriverManager.getConnection(theUrl + theDatabaseName, theUserName, thePassword);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void CloseConecctionToDatabase() {
        try {
            theConnection.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public String GetInformationFromDatabase(String theQuery) {
        try {
            Statement theStatement = theConnection.createStatement();
            ResultSet theResult = theStatement.executeQuery(theQuery);

            String theInformation = "";
            while (theResult.next()) {
                theInformation += theResult.getString("cedulaCliente");
            }
            return theInformation;
        } catch (SQLException theException) {
            return "Error: " + theException;
        }
    }

    public void TryCargoAutomatico(String theCardNumber, String theCVV, double theAmountOfMoney, String theInstitution) {
        double elSaldo = GetSaldo(theCardNumber, theCVV);
        if (elSaldo > theAmountOfMoney) {
            String laCedulaCliente = GetCedulaCliente(theCardNumber);
            
            double elSaldoActualizado = elSaldo - theAmountOfMoney;
            UpdateCliente(laCedulaCliente, elSaldoActualizado);
            
            RegisterCargoAutomatico(laCedulaCliente, theInstitution, theAmountOfMoney, true);
        } else {
            String laCedulaCliente = GetCedulaCliente(theCardNumber);
            
            RegisterCargoAutomatico(laCedulaCliente, theInstitution, theAmountOfMoney, false);
        }
    }

    private double GetSaldo(String theCardNumber, String theCVV) {
        try {
            String theQuery = "SELECT numTarjetaCliente, CVV, saldo "
                    + "FROM cliente "
                    + "WHERE numTarjetaCliente='" + theCardNumber
                    + "' AND CVV='" + theCVV + "';";
            Statement theStatement = theConnection.createStatement();
            ResultSet theResult = theStatement.executeQuery(theQuery);

            while (theResult.next()) {
                if (theResult.getString("numTarjetaCliente").equalsIgnoreCase(theCardNumber)
                        && theResult.getString("CVV").equals(theCVV)) {
                    return theResult.getDouble("saldo");
                }
            }
            return 0;
        } catch (SQLException theException) {
            return 0;
        }
    }

    private void RegisterCargoAutomatico(String laCedulaCliente, String laCedulaJuridicaDeLaInstitucion, double theAmountOfMoney, boolean theValidation) {
        try{
            String query = "INSERT INTO transaccion VALUES ( '"
                + laCedulaCliente + "', '"
                + laCedulaJuridicaDeLaInstitucion + "', " 
                //+ new Date(); + ","
                + theAmountOfMoney + ", "
                + theValidation + ");";
            Statement statement = theConnection.createStatement(); 
            statement.executeUpdate(query);
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private String GetCedulaCliente(String theCardNumber) {
        try {
            String theQuery = "SELECT cedulaCliente"
                    + "FROM cliente "
                    + "WHERE numTarjetaCliente='" + theCardNumber + "';";
            Statement theStatement = theConnection.createStatement();
            ResultSet theResult = theStatement.executeQuery(theQuery);
            
            String laCedulaCliente = "";
            while (theResult.next()) {
                laCedulaCliente = theResult.getString("cedulaCliente");
            }
            return laCedulaCliente;
        } catch (SQLException theException) {
            return theException.toString();
        }
    }

    private void UpdateCliente(String laCedulaCliente, double elSaldoActualizado) {
        try{
            String query = "UPDATE cliente"
                        + " SET saldo=" + elSaldoActualizado
                        + " WHERE cedulaCliente" + laCedulaCliente;
            Statement statement = theConnection.createStatement(); 
            statement.executeUpdate(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private String[][] GetBitacora(){
        //CAMBIAR
        String laBitacora[][];
        try{
            int rowCount = getCountRows("cargoautomatico");
            laBitacora = new String[rowCount][6];
            String query = "SELECT " +
                    "transaccion.idTransaccion, " +
                    "cliente.cedulaCliente, " +
                    "banco.nombreBanco, " +
                    "transaccion.fechaHoraTransaccion, " +
                    "transaccion.idCajero, " +
                    "retiroCajero "+
                    "FROM transaccion " +
                    "INNER JOIN cuenta on transaccion.idCuenta = cuenta.idCuenta " +
                    "INNER JOIN cliente on cuenta.idCliente = cliente.idCliente " +
                    "INNER JOIN banco on cuenta.idBanco = cuenta.idBanco " + 
                    "ORDER BY transaccion.idTransaccion ASC;";
            Statement statement = theConnection.createStatement(); 
            ResultSet res = statement.executeQuery(query);
            int count = 0;
            while(res.next()){
                laBitacora[count][0] = "" + res.getInt("idTransaccion");
                laBitacora[count][1] = res.getString("cedulaCliente");
                laBitacora[count][2] = "" + res.getInt("retiroCajero");
                laBitacora[count][3] = res.getString("fechaHoraTransaccion");
                laBitacora[count][4] = res.getString("nombreBanco");
                laBitacora[count][5] = "" + res.getInt("idCajero");
                count++;
            }
            return laBitacora;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    private int getCountRows(String tableName){
        try{
            int countRow = 0;
            String query = "SELECT COUNT(*) FROM " + tableName + ";";
            Statement statement = theConnection.createStatement(); 
            ResultSet res = statement.executeQuery(query);
            if(res.next()){
                countRow =  res.getInt("COUNT(*)");
            }
            return countRow;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }
}
