package Model;

import Controller.DataBase;
import java.util.concurrent.Callable;
public class BitacoraHilo implements Callable<String[][]> {

    @Override
    public String[][] call() throws Exception {
        DataBase conexionABaseDeDatos = new DataBase();
        return conexionABaseDeDatos.ObtengaBitacora();
    }
}
