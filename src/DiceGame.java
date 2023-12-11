import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DiceGame {
    public static void main(String[] args) throws IOException {
        File output = new File("DiceGameOutput.txt");
        FileWriter fileWriter = new FileWriter(output);
        System.out.println(output.getPath());
        Scanner scanner = new Scanner(System.in);
        ArrayList<Player> playerList = new ArrayList<>();

        System.out.println("Enter the number of sides for the die being used: ");
        int numSides = scanner.nextInt();
        System.out.println("Enter the number of players: ");
        int numPlayers = scanner.nextInt();
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Enter the name for Player " + (i + 1) + ":");
            if (i == 0) {
                scanner.nextLine();
            }
            String playerName = scanner.nextLine();
            playerList.add(new Player(playerName, new Die(numSides)));
        }
        for (Player player : playerList) {
            player.getDie().roll();
            fileWriter.write(player.getName() + " rolled a " + player.getDie().getValue() + " on a " + player.getDie().getNumSides() + " sided Die." + "\n");
            System.out.println(player.getName() + " rolled a " + player.getDie().getValue() + " on a " + player.getDie().getNumSides() + " sided Die.");
        }
        String winner = decideWinner(playerList);
        fileWriter.write(winner);
        System.out.println(winner);
        fileWriter.close();
    }

    public static String decideWinner(ArrayList<Player> playerList) {
        int size = playerList.size();
        Player winningPlayer;
        for (int i = 0; i < size - 1; i++) {
            if (playerList.get(0).getDie().getValue() > playerList.get(1).getDie().getValue()) {
                playerList.remove(1);
            } else if (playerList.get(0).getDie().getValue() == playerList.get(1).getDie().getValue()) {
                return playerList.get(0).getName() + " and " + playerList.get(1).getName() + " tied the game.";
            } else {
                playerList.remove(0);
            }
        }
        winningPlayer = playerList.get(0);
        return winningPlayer.getName() + " won the game!";
    }
}
