/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaProject;

/**
 *
 * @author Felipe Almeida
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Transactions {

    private final String DatabaseSource = "jdbc:sqlite:C:\\Users\\db1002634\\Documents\\NetBeansProjects\\PokeProject\\web\\WEB-INF\\database.db";
    private Connection connection;
    private PreparedStatement pstatement;
    private Statement statement;

    private void openConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(DatabaseSource);
    }

    private void closeConnection() throws SQLException {
        connection.close();
    }
}
