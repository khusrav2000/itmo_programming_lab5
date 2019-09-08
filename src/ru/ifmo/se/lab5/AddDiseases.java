package ru.ifmo.se.lab5;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
public class AddDiseases {
    private static Scanner scan = new Scanner(System.in);
    private static Connection connection;
    final static String DB_URL = "jdbc:postgresql://localhost:5432/firstDatabase";
    final static String DB_login = "postgres";
    final static String DB_password = "123";
    private void connectToDatabase(){
        try{
            connection = DriverManager.getConnection(DB_URL , DB_login , DB_password);
        }catch (Exception e){
            System.out.println("Не удаётся соединится с базой");
            e.printStackTrace();
        }
    }
    public void AddDiseases(){
        connectToDatabase();
        try {
            String sql = "INSERT INTO diseases (nameOfDiseases) VALUES(?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            System.out.println("Введите имя или тип болезни: ");
            scan.nextLine();
            String nameOfDiseases = scan.nextLine();
            statement.setString(1 , nameOfDiseases);

        }catch (Exception e){
            System.out.println("Ошибка");
        }
    }
}
