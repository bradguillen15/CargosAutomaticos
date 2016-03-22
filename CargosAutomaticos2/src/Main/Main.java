package Main;

import Controler.DataBase;

public class Main {

    public static void main(String[] args) {
        DataBase theDatabase = new DataBase();
        theDatabase.OpenConecctionToDatabase();
        
        theDatabase.CloseConecctionToDatabase();
    }
}
