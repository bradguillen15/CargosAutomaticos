package Controller;

import Model.CargoAutomatico;
import Model.Cliente;
import java.sql.*;
import javax.swing.JOptionPane;

public class DataBase {

    private Connection laConexion = null;
    private int numeroDeCliente;

    public void RealizaElCargoAutomaticamente(int numDeCliente) {
        Pool elPool = new Pool();
        try {
            laConexion = elPool.theDataSource.getConnection();
            if (laConexion != null) {
                CargoAutomatico elCargoAutomatico = CargaCargoAutomatico(numDeCliente);
                IntentaCargoAutomatico(elCargoAutomatico);
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

    private CargoAutomatico CargaCargoAutomatico(int laPosicion) {
        CargoAutomatico elCargoAutomatico = null;
        try {
            String theQuery = "SELECT * "
                    + "FROM cargoAutomatico "
                    + "INNER JOIN cliente "
                    + "ON cargoAutomatico.cedulaClienteCargoAuto=cliente.cedulaCliente "
                    + "WHERE cedulaClienteCargoAuto='" + laPosicion + "'";
            Statement theStatement = laConexion.createStatement();
            ResultSet theResult = theStatement.executeQuery(theQuery);

            while (theResult.next()) {
                elCargoAutomatico = new CargoAutomatico(
                        theResult.getString("cedulaJuridicaInstitucionCargoAuto"),
                        theResult.getString("descripcionCargoAuto"),
                        theResult.getDouble("montoCargoCargoAuto"),
                        new Cliente(
                                theResult.getString("cedulaCliente"),
                                theResult.getString("nombreCliente"),
                                theResult.getString("apellidoCliente"),
                                theResult.getString("correoCliente"),
                                theResult.getString("numTarjetaCliente"),
                                theResult.getDouble("saldoCliente"),
                                theResult.getString("CVVCliente")
                        )
                );
            }
            return elCargoAutomatico;
        } catch (SQLException theException) {
            System.out.println(theException);
            return null;
        }
    }

    private void IntentaCargoAutomatico(CargoAutomatico elCargoAutomatico) {
        if (elCargoAutomatico.getElCliente().getSaldo() > elCargoAutomatico.getMontoCargoCargoAuto()) {
            elCargoAutomatico.getElCliente().setSaldo(elCargoAutomatico.getElCliente().getSaldo() - elCargoAutomatico.getMontoCargoCargoAuto());
            UpdateCliente(elCargoAutomatico.getElCliente());
            InsertCargoAutomatico(elCargoAutomatico, true);
        } else {
            InsertCargoAutomatico(elCargoAutomatico, false);

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

    private void InsertCargoAutomatico(CargoAutomatico elCargoAutomatico, boolean laValidacion) {
        try {
            String query = "INSERT INTO bitacora "
                    + "(cedulaClienteBitacora, cedulaJuridicaInstitucionBitacora, fechaBitacora, montoCargoBitacora, validacionCargoBitacora)"
                    + "VALUES ("
                    + "(SELECT cedulaCliente FROM cliente WHERE cedulaCliente = '" + elCargoAutomatico.getElCliente().getCedula() + "'), "
                    + "(SELECT cedulaJuridicaInstitucion FROM institucion WHERE cedulaJuridicaInstitucion = '" + elCargoAutomatico.getCedulaJuridicaInstitucionCargoAuto() + "'), "
                    + "NOW(), "
                    + elCargoAutomatico.getMontoCargoCargoAuto() + ", "
                    + laValidacion + ");";
            Statement statement = laConexion.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public String[][] ObtengaBitacora() {
        String laBitacora[][];
        try {
            int rowCount = getCountRows("bitacora");
            laBitacora = new String[rowCount][9];
            String query = "SELECT "
                    + "fechaBitacora, "
                    + "cargoAutomatico.descripcionCargoAuto, "
                    + "montoCargoBitacora, "
                    + "validacionCargoBitacora, "
                    + "cedulaJuridicaInstitucionBitacora, "
                    + "institucion.nombreInstitucion, "
                    + "cliente.cedulaCliente, "
                    + "cliente.nombreCliente, "
                    + "cliente.apellidoCliente "
                    + "FROM bitacora "
                    + "INNER JOIN cargoAutomatico "
                    + "ON bitacora.cedulaClienteBitacora=cargoAutomatico.cedulaClienteCargoAuto "
                    + "INNER JOIN institucion "
                    + "ON cargoAutomatico.cedulaJuridicaInstitucionCargoAuto=institucion.cedulaJuridicaInstitucion "
                    + "INNER JOIN cliente "
                    + "ON cargoAutomatico.cedulaClienteCargoAuto=cliente.cedulaCliente;";
            Statement statement = laConexion.createStatement();
            ResultSet res = statement.executeQuery(query);
            int count = 0;
            while (res.next()) {
                laBitacora[count][0] = res.getString("fechaBitacora");
                laBitacora[count][1] = res.getString("descripcionCargoAuto");
                laBitacora[count][2] = res.getString("montoCargoBitacora");
                laBitacora[count][3] = res.getString("validacionCargoBitacora");
                laBitacora[count][4] = res.getString("cedulaJuridicaInstitucionBitacora");
                laBitacora[count][5] = res.getString("nombreInstitucion");
                laBitacora[count][6] = res.getString("cedulaCliente");
                laBitacora[count][7] = res.getString("nombreCliente");
                laBitacora[count][8] = res.getString("apellidoCliente");
                count++;
            }
            return laBitacora;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public int getCountRows(String tableName) {
        Pool elPool = new Pool();
        try {
            laConexion = elPool.theDataSource.getConnection();
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
