import arena.Main;

public class App {
    public static void main(String[] args) {
        try {
            Main game = new Main();
            game.run();
        } catch (Exception e) {
            System.err.println("An error occurred while running the game: " + e.getMessage());
        }
    }
}
