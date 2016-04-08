package Model;

import Controller.DataBase;

public class ClienteHilo implements Runnable {
    
    private int numDeCliente;
               
    public ClienteHilo(int numDeCliente) {
       this.numDeCliente=numDeCliente;
    }
  
    @Override
    public void run(){
        DataBase conexionABaseDeDatos = new DataBase(); 
        conexionABaseDeDatos.RealizaElCargoAutomaticamente(numDeCliente);
    }
}
