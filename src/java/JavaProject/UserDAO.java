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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO extends DBConnection {

    public void add(User user) throws ClassNotFoundException, SQLException {
        try {
            openConnection();
            pstatement = getConnection().prepareStatement("INSERT INTO user (name, email, username, password, role) VALUES (?,?,?,?,?)");
            pstatement.setString(1, user.getName());
            pstatement.setString(2, user.getEmail());
            pstatement.setString(3, user.getUsername());
            pstatement.setString(4, user.getPassword());
            pstatement.setString(5, user.getRole());
            pstatement.execute();
            pstatement.close();
        } catch (SQLException e) {
        } finally {
            closeConnection();
        }
    }

    public void delete(int id) throws ClassNotFoundException, SQLException {
        try {
            openConnection();
            pstatement = getConnection().prepareStatement("DELETE FROM user WHERE id = ?");
            pstatement.setInt(1, id);
            pstatement.execute();
            pstatement.close();
        } catch (SQLException e) {
        } finally {
            closeConnection();
        }
    }

    public void update(User user) throws ClassNotFoundException, SQLException {
        try {
            openConnection();
            pstatement = getConnection().prepareStatement("UPDATE user SET name=?, email=?, username=?, password=?, role=? WHERE id = ?");
            pstatement.setString(1, user.getName());
            pstatement.setString(2, user.getEmail());
            pstatement.setString(3, user.getUsername());
            pstatement.setString(4, user.getPassword());
            pstatement.setString(5, user.getRole());
            pstatement.setInt(6, user.getId());
            pstatement.execute();
            pstatement.close();
        } catch (SQLException e) {
        } finally {
            closeConnection();
        }
    }

    public User[] getAll() throws ClassNotFoundException, SQLException {
        User[] users;
        ArrayList<User> user_list = new ArrayList<>();
        try {
            openConnection();
            statement = getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM user");
            while (result.next()) {
                User user = new User();
                user.setId(result.getInt("id"));
                user.setName(result.getString("name"));
                user.setEmail(result.getString("email"));
                user.setUsername(result.getString("username"));
                user.setPassword(result.getString("password"));
                user.setRole(result.getString("role"));
                user_list.add(user);
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeConnection();
        }
        users = new User[user_list.size()];
        users = user_list.toArray(users);
        return users;
    }

    public User validate(String username, String password) throws ClassNotFoundException, SQLException {
        User user = null;
        try {
            openConnection();
            pstatement = getConnection().prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
            pstatement.setString(1, username);
            pstatement.setString(2, password);
            ResultSet result = pstatement.executeQuery();
            while (result.next()) {
                user = new User();
                user.setId(result.getInt("id"));
                user.setName(result.getString("name"));
                user.setEmail(result.getString("email"));
                user.setUsername(result.getString("username"));
                user.setPassword(result.getString("password"));
                user.setRole(result.getString("role"));
            }
            result.close();
            pstatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeConnection();
        }
        return user;
    }

    public User get(int id) throws ClassNotFoundException, SQLException {
        User user = null;
        try {
            openConnection();
            pstatement = getConnection().prepareStatement("SELECT * FROM user WHERE id = ? LIMIT 1");
            pstatement.setInt(1, id);
            ResultSet result = pstatement.executeQuery();
            while (result.next()) {
                user = new User();
                user.setId(result.getInt("id"));
                user.setName(result.getString("name"));
                user.setEmail(result.getString("email"));
                user.setUsername(result.getString("username"));
                user.setPassword(result.getString("password"));
                user.setRole(result.getString("role"));
            }
            result.close();
            pstatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeConnection();
        }
        return user;
    }
}
