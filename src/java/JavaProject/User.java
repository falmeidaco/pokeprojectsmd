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
public class User {
    int id;
    private String name, username, email, password, role;

    public int getId() {
            return id;
    }

    public String getName() {
            return name;
    }

    public String getUsername() {
            return username;
    }

    public String getEmail() {
            return email;
    }

    public String getPassword() {
            return password;
    }

    public String getRole() {
            return role;
    }

    public void setId(int id) {
            this.id = id;
    }

    public void setName(String name) {
            this.name = name;
    }

    public void setUsername(String username) {
            this.username = username;
    }

    public void setEmail(String email) {
            this.email = email;
    }

    public void setPassword(String password) {
            this.password = password;
    }

    public void setRole(String role) {
            this.role = role;
    }

}