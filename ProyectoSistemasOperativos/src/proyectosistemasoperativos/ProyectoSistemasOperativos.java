package proyectosistemasoperativos;

import java.util.Date;

public class ProyectoSistemasOperativos {

    public static void main(String[] args) {
        DataBase conexionABaseDeDatos = new DataBase(); 
        //asd.ConectarUnClienteALaBaseDeDatos("1111111111111111", "111", 4500, "1");
        conexionABaseDeDatos.ConectarUnClienteALaBaseDeDatos("1111111111111111", "111", 4500, "1");
    }
    
}
