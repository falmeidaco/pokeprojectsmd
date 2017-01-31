package JavaProject;

public class Trainer {

    private int id;
    private double money;
    private String name, email, username, password, city_name;
    private TrainerPokemon[] pokemons;

    public int getId() {
        return id;
    }

    public double getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }
    
    public TrainerPokemon[] getPokemons() {
        return this.pokemons;
    }
    
    public void setPokemons(TrainerPokemon[] pokemons) {
        this.pokemons = pokemons;
    }
}
