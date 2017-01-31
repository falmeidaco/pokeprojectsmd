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
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    final String DatabaseSource = "jdbc:sqlite:C:\\Users\\db1002634\\Documents\\NetBeansProjects\\PokeProject\\web\\WEB-INF\\database\\database.db";
    private Connection connection;
    PreparedStatement pstatement;
    Statement statement;
    
    public Connection getConnection() {
        return connection;
    }

    void openConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(DatabaseSource);
    }

    void closeConnection() throws SQLException {
        connection.close();
    }
}
