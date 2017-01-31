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

public class TrainerDAO extends DBConnection {

    public void add(Trainer trainer) throws ClassNotFoundException, SQLException {
        try {
            openConnection();
            pstatement = getConnection().prepareStatement("INSERT INTO trainer (name, email, city_name, username, password, money) VALUES (?,?,?,?,?,?)");
            pstatement.setString(1, trainer.getName());
            pstatement.setString(2, trainer.getEmail());
            pstatement.setString(3, trainer.getCity_name());
            pstatement.setString(4, trainer.getUsername());
            pstatement.setString(5, trainer.getPassword());
            pstatement.setDouble(6, trainer.getMoney());
            pstatement.execute();
            pstatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public void delete(int id) throws ClassNotFoundException, SQLException {
        try {
            deleteAllTrainerPokemons(id);
            openConnection();
            pstatement = getConnection().prepareStatement("DELETE FROM trainer WHERE id = ?");
            pstatement.setInt(1, id);
            pstatement.execute();
            pstatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public void update(Trainer trainer) throws ClassNotFoundException, SQLException {
        try {
            openConnection();
            pstatement = getConnection().prepareStatement("UPDATE trainer SET name=?, email=?, city_name=?, username=?, password=?, money=? WHERE id = ?");
            pstatement.setString(1, trainer.getName());
            pstatement.setString(2, trainer.getEmail());
            pstatement.setString(3, trainer.getCity_name());
            pstatement.setString(4, trainer.getUsername());
            pstatement.setString(5, trainer.getPassword());
            pstatement.setDouble(6, trainer.getMoney());
            pstatement.setInt(7, trainer.getId());
            pstatement.execute();
            pstatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public Trainer[] getAll() throws ClassNotFoundException, SQLException {
        Trainer[] trainer;
        ArrayList<Trainer> trainer_list = new ArrayList<>();
        try {
            openConnection();
            ResultSet result;
            statement = getConnection().createStatement();
            result = statement.executeQuery("SELECT * FROM trainer");
            while (result.next()) {
                Trainer trainer_item = new Trainer();
                trainer_item.setId(result.getInt("id"));
                trainer_item.setName(result.getString("name"));
                trainer_item.setEmail(result.getString("email"));
                trainer_item.setCity_name(result.getString("city_name"));
                trainer_item.setUsername(result.getString("username"));
                trainer_item.setPassword(result.getString("password"));
                trainer_item.setMoney(result.getDouble("money"));
                trainer_list.add(trainer_item);
            }
            statement.close();
            result.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeConnection();
        }
        trainer = new Trainer[trainer_list.size()];
        trainer = trainer_list.toArray(trainer);
        return trainer;
    }

    public Trainer get(int id) throws ClassNotFoundException, SQLException {
        Trainer trainer = null;
        try {
            openConnection();
            pstatement = getConnection().prepareStatement("SELECT * FROM trainer WHERE id = ? LIMIT 1");
            pstatement.setInt(1, id);
            try (ResultSet result = pstatement.executeQuery()) {
                while (result.next()) {
                    trainer = new Trainer();
                    trainer.setId(result.getInt("id"));
                    trainer.setName(result.getString("name"));
                    trainer.setEmail(result.getString("email"));
                    trainer.setCity_name(result.getString("city_name"));
                    trainer.setUsername(result.getString("username"));
                    trainer.setPassword(result.getString("password"));
                    trainer.setMoney(result.getDouble("money"));
                }
                pstatement.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeConnection();
        }
        return trainer;
    }

    public ArrayList<Trainer> validate(String username, String password) throws ClassNotFoundException, SQLException {
        ArrayList<Trainer> itens = new ArrayList<>();
        try {
            openConnection();
            ResultSet result;
            pstatement = getConnection().prepareStatement("SELECT * FROM trainer WHERE username = ? AND password = ?");
            pstatement.setString(1, username);
            pstatement.setString(2, password);
            result = pstatement.executeQuery();
            while (result.next()) {
                Trainer item = new Trainer();
                item.setId(result.getInt("id"));
                item.setName(result.getString("name"));
                item.setEmail(result.getString("email"));
                item.setCity_name(result.getString("city_name"));
                item.setUsername(result.getString("username"));
                item.setPassword(result.getString("password"));
                item.setMoney(result.getDouble("money"));
                itens.add(item);
            }
            pstatement.close();
            result.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeConnection();
        }
        return itens;
    }

    public TrainerPokemon[] getTrainerPokemons(Trainer trainer) throws SQLException, ClassNotFoundException {
        TrainerPokemon[] trainer_pokemons;
        ArrayList<TrainerPokemon> trainer_pokemon_list = new ArrayList<>();
        try {
            openConnection();
            pstatement = getConnection().prepareStatement("SELECT trainer_pokemon.id as id, trainer_pokemon.trainer_id as trainer_id, trainer_pokemon.pokemon_id as pokemon_id, pokemon.name as name, pokemon.description as description, pokemon.image_url as image_url, trainer_pokemon.exp as exp, pokemon.default_exp as default_exp FROM pokemon, trainer_pokemon WHERE trainer_pokemon.trainer_id = ? AND trainer_pokemon.pokemon_id = pokemon.id;");
            pstatement.setInt(1, trainer.getId());
            try (ResultSet result = pstatement.executeQuery()) {
                while (result.next()) {
                    TrainerPokemon trainer_pokemon = new TrainerPokemon();
                    Pokemon pokemon = new Pokemon();
                    pokemon.setId(result.getInt("pokemon_id"));
                    pokemon.setName(result.getString("name"));
                    pokemon.setDescription(result.getString("description"));
                    pokemon.setImage_url(result.getString("image_url"));
                    pokemon.setDefault_exp(result.getInt("default_exp"));
                    trainer_pokemon.setPokemon(pokemon);
                    trainer_pokemon.setId(result.getInt("id"));
                    trainer_pokemon.setExp(result.getInt("exp"));
                    trainer_pokemon_list.add(trainer_pokemon);
                }
                pstatement.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeConnection();
        }
        trainer_pokemons = new TrainerPokemon[trainer_pokemon_list.size()];
        trainer_pokemons = trainer_pokemon_list.toArray(trainer_pokemons);
        return trainer_pokemons;
    }

    public Trainer getWithPokemons(int id) throws ClassNotFoundException, SQLException {
        Trainer trainer = get(id);
        if (trainer != null) {
            trainer.setPokemons(getTrainerPokemons(trainer));
        }
        return trainer;
    }

    public void addPokemon(int trainer_id, int pokemon_id, int exp) throws ClassNotFoundException, SQLException {
        try {
            openConnection();
            pstatement = getConnection().prepareStatement("INSERT INTO trainer_pokemon (trainer_id, pokemon_id, exp) VALUES (?,?,?)");
            pstatement.setInt(1, trainer_id);
            pstatement.setInt(2, pokemon_id);
            pstatement.setInt(3, exp);
            pstatement.execute();
            pstatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public void deletePokemon(int id) throws ClassNotFoundException, SQLException {
        try {
            openConnection();
            pstatement = getConnection().prepareStatement("DELETE FROM trainer_pokemon WHERE id = ?");
            pstatement.setInt(1, id);
            pstatement.execute();
            pstatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public void deleteAllTrainerPokemons(int trainer_id) throws ClassNotFoundException, SQLException {
        try {
            openConnection();
            pstatement = getConnection().prepareStatement("DELETE FROM trainer_pokemon WHERE trainer_id = ?");
            pstatement.setInt(1, trainer_id);
            pstatement.execute();
            pstatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeConnection();
        }
    }
}
