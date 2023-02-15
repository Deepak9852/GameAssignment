package module2a;
import java.util.Scanner;
import java.util.HashMap;
public class User{
    private String username;
    private int gamesPlayed;
    private int gamesWon;

    public User(String username) {
        this.username = username;
        this.gamesPlayed = 0;
        this.gamesWon = 0;
    }

    public String getUsername() {
        return username;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void incrementGamesPlayed() {
        gamesPlayed++;
    }

    public void incrementGamesWon() {
        gamesWon++;
    }
}


 class Game {
    private int coins;
    private User user;
    private int maxCoinsPerTurn;

    public Game(User user, int maxCoinsPerTurn) {
        this.coins = 21;
        this.user = user;
        this.maxCoinsPerTurn = maxCoinsPerTurn;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        while (coins > 0) {
            // User turn
            int userCoins = 0;
            do {
                System.out.println("There are " + coins + " coins left. How many coins do you want to pick (1-" + maxCoinsPerTurn + ")?");
                userCoins = scanner.nextInt();
            } while (userCoins < 1 || userCoins > maxCoinsPerTurn || userCoins > coins);

            coins -= userCoins;
            System.out.println("You picked " + userCoins + " coins. " + coins + " coins left.");

            if (coins == 0) {
                System.out.println("You lose.");
                user.incrementGamesPlayed();
                return;
            }

            // AI turn
            int aiCoins = Math.min(coins, maxCoinsPerTurn + 1 - userCoins);
            coins -= aiCoins;
            System.out.println("AI picked " + aiCoins + " coins. " + coins + " coins left.");

            if (coins == 0) {
                System.out.println("You win!");
                user.incrementGamesPlayed();
                user.incrementGamesWon();
                return;
            }
        }
    }
}
 class Main {
    private static HashMap<String, String> users = new HashMap<String, String>();

    public static void main(String[] args) {
        users.put("admin", "admin1234");
        users.put("guest", "guest1234");

        Scanner scanner = new Scanner(System.in);

        // Login screen
        System.out.println("Welcome to the game. Please log in.");
        User user = null;
        do {
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            if (users.containsKey(username) && users.get(username).equals(password)) {
                user = new User(username);
                System.out.println("Welcome, " + username + "!");
            } else {
                System.out.println("Invalid username or password.");
            }
        } while (user == null);
        

        // Game play screen
        System.out.println("Starting new game.");
        Game game = new Game(user, 4);
        game.play();

        // Lost screen
        System.out.println("Game over.");
        System.out.println("You played " + user.getGamesPlayed());
    }
 }
