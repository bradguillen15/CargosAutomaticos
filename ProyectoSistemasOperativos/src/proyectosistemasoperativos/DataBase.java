package proyectosistemasoperativos;

import java.sql.*;
import javax.swing.JOptionPane;

public class DataBase {

    private Connection laConexion = null;

    public void ConectarUnClienteALaBaseDeDatos(String theCardNumber, String theCVV, double theAmountOfMoney, String theInstitution) {
        Pool elPool = new Pool();
        try {
            laConexion = elPool.theDataSource.getConnection();
            if (laConexion != null) {
                Cliente elCliente = CargaCliente(theCardNumber, theCVV);
                    //IntentaCargoAutomatico(elCliente, theAmountOfMoney, theInstitution);
                    System.out.println(elCliente);
            }
        } catch (SQLException elSQLException) {
            System.out.println(elSQLException);
        } finally {
            try {
                laConexion.close();
            } catch (SQLException elSQLException) {
                System.out.println(elSQLException);
            }
        }
    }
    
    public Cliente CargaCliente(String theCardNumber, String theCVV) {
        try {
            String theQuery = "SELECT *"
                    + " FROM cliente "
                    + "WHERE numTarjetaCliente='" + theCardNumber
                    + "' AND CVVCliente='" + theCVV + "';";
            Statement theStatement = laConexion.createStatement();
            ResultSet theResult = theStatement.executeQuery(theQuery);
            Cliente elCliente = null;
            while (theResult.next()) {
                elCliente = new Cliente(
                        theResult.getString("cedulaCliente"),
                        theResult.getString("nombreCliente"),
                        theResult.getString("apellidoCliente"),
                        theResult.getString("correoCliente"),
                        theResult.getString("numTarjetaCliente"),
                        theResult.getDouble("saldoCliente"),
                        theResult.getString("CVVCliente")
                );
            }
            return elCliente;
        } catch (SQLException theException) {
            System.out.println(theException);
            return null;
        }
    }

    private void IntentaCargoAutomatico(Cliente elCliente, double elCobro, String laInstitucion) {
        if (elCliente.getSaldo() > elCobro) {
            elCliente.setSaldo(elCliente.getSaldo() - elCobro); 
            UpdateCliente(elCliente);
            InsertCargoAutomatico(elCliente, laInstitucion, elCobro, true);
        } else {
            InsertCargoAutomatico(elCliente, laInstitucion, elCobro, false);
        }
    }
    
    private void UpdateCliente(Cliente elCliente) {
        try {
            String query = "UPDATE cliente"
                    + " SET saldoCliente=" + elCliente.getSaldo()
                    + " WHERE cedulaCliente='" + elCliente.getCedula() + "';";
            Statement statement = laConexion.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void InsertCargoAutomatico(Cliente elCliente, String laCedulaJuridicaDeLaInstitucion, double elCobro, boolean laValidacion) {
        try {
            String query = "INSERT INTO bitacora "
                    + "(cedulaClienteBitacora, cedulaJuridicaInstitucionBitacora, fechaBitacora, montoCargoBitacora, validacionCargoBitacora)"
                    + "VALUES ("
                    + "(SELECT cedulaClienteCargoAuto FROM cargoautomatico WHERE cedulaClienteCargoAuto = '"+ elCliente.getCedula()+"'), "
                    + "(SELECT cedulaJuridicaInstitucionCargoAuto FROM cargoautomatico WHERE cedulaJuridicaInstitucionCargoAuto = '"+ laCedulaJuridicaDeLaInstitucion +"'), "
                    + "NOW(), "
                    + elCobro + ", "
                    + laValidacion + ");";
            Statement statement = laConexion.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private String[][] ObtengaBitacora() {
        //CAMBIAR
        String laBitacora[][];
        try {
            int rowCount = getCountRows("cargoautomatico");
            laBitacora = new String[rowCount][6];
            String query = "SELECT"
                    + "transaccion.idTransaccion, "
                    + "cliente.cedulaCliente, "
                    + "banco.nombreBanco, "
                    + "transaccion.fechaHoraTransaccion, "
                    + "transaccion.idCajero, "
                    + "retiroCajero "
                    + "FROM transaccion "
                    + "INNER JOIN cuenta on transaccion.idCuenta = cuenta.idCuenta "
                    + "INNER JOIN cliente on cuenta.idCliente = cliente.idCliente "
                    + "INNER JOIN banco on cuenta.idBanco = cuenta.idBanco "
                    + "ORDER BY transaccion.idTransaccion ASC;";
            Statement statement = laConexion.createStatement();
            ResultSet res = statement.executeQuery(query);
            int count = 0;
            while (res.next()) {
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

    private int getCountRows(String tableName) {
        try {
            int countRow = 0;
            String query = "SELECT COUNT(*) FROM " + tableName + ";";
            Statement statement = laConexion.createStatement();
            ResultSet res = statement.executeQuery(query);
            if (res.next()) {
                countRow = res.getInt("COUNT(*)");
            }
            return countRow;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }

    
}
