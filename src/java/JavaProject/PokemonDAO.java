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

public class PokemonDAO extends DBConnection {

    public void add(Pokemon pokemon) throws ClassNotFoundException, SQLException {
        try {
            openConnection();
            pstatement = getConnection().prepareStatement("INSERT INTO pokemon (name, description, image_url, default_exp) VALUES (?,?,?,?)");
            pstatement.setString(1, pokemon.getName());
            pstatement.setString(2, pokemon.getDescription());
            pstatement.setString(3, pokemon.getImage_url());
            pstatement.setInt(4, pokemon.getDefault_exp());
            pstatement.execute();
            pstatement.close();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public void delete(int id) throws ClassNotFoundException, SQLException {
        try {
            deleteTrainerPokemons(id);
            openConnection();
            pstatement = getConnection().prepareStatement("DELETE FROM pokemon WHERE id = ?");
            pstatement.setInt(1, id);
            pstatement.execute();
            pstatement.close();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public void update(Pokemon pokemon) throws ClassNotFoundException, SQLException {
        try {
            openConnection();
            pstatement = getConnection().prepareStatement("UPDATE pokemon SET name=?, description=?, image_url=?, default_exp=? WHERE id = ?");
            pstatement.setString(1, pokemon.getName());
            pstatement.setString(2, pokemon.getDescription());
            pstatement.setString(3, pokemon.getImage_url());
            pstatement.setInt(4, pokemon.getDefault_exp());
            pstatement.setInt(5, pokemon.getId());
            pstatement.execute();
            pstatement.close();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public Pokemon[] getAll() throws ClassNotFoundException, SQLException {
        Pokemon[] pokemons = null;
        ArrayList<Pokemon> pokemon_list = new ArrayList<>();
        try {
            openConnection();
            statement = getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM pokemon");
            while (result.next()) {
                Pokemon pokemon = new Pokemon();
                pokemon.setId(result.getInt("id"));
                pokemon.setName(result.getString("name"));
                pokemon.setDescription(result.getString("description"));
                pokemon.setImage_url(result.getString("image_url"));
                pokemon.setDefault_exp(result.getInt("default_exp"));
                pokemon_list.add(pokemon);
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        } finally {
            closeConnection();
        }
        pokemons = new Pokemon[pokemon_list.size()];
        pokemons = pokemon_list.toArray(pokemons);
        return pokemons;
    }

    public Pokemon get(int id) throws ClassNotFoundException, SQLException {
        Pokemon pokemon = null;
        try {
            openConnection();
            pstatement = getConnection().prepareStatement("SELECT * FROM pokemon WHERE id = ? LIMIT 1");
            pstatement.setInt(1, id);
            ResultSet result = pstatement.executeQuery();
            while (result.next()) {
                pokemon = new Pokemon();
                pokemon.setId(result.getInt("id"));
                pokemon.setName(result.getString("name"));
                pokemon.setDescription(result.getString("description"));
                pokemon.setImage_url(result.getString("image_url"));
                pokemon.setDefault_exp(result.getInt("default_exp"));
            }
            result.close();
            pstatement.close();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        } finally {
            closeConnection();
        }        
        return pokemon;
    }
    
    public void deleteTrainerPokemons(int id) throws ClassNotFoundException, SQLException {
        try {
            openConnection();
            pstatement = getConnection().prepareStatement("DELETE FROM trainer_pokemon WHERE pokemon_id = ?");
            pstatement.setInt(1, id);
            pstatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeConnection();
        }
    }
    
}
