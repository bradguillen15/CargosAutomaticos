package Main;

import Controler.DataBase;

public class Main {

    public static void main(String[] args) {
        DataBase theDatabase = new DataBase();
        theDatabase.OpenConecctionToDatabase();
        //System.out.println(theDatabase.GetInformationFromDatabase("SELECT * FROM cliente"));
        System.out.println(theDatabase.ValidateThePayment("1111111111111111", "111"));
        theDatabase.CloseConecctionToDatabase();
    }
}
