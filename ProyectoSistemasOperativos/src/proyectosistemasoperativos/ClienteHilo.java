package proyectosistemasoperativos;

public class ClienteHilo implements Runnable {
    private final String msj;
               
    public ClienteHilo(String msj) {
        this.msj = msj;
    }
    @Override
    public void run(){
        DataBase conexionABaseDeDatos = new DataBase(); 
        while(true){
            conexionABaseDeDatos.ConectarUnClienteALaBaseDeDatos("1111111111111111", msj, 4500, "1");
        }
    }


}
