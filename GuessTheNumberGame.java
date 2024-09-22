import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberGame {

    public static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public static void playRound(Scanner scanner, int roundNumber, int maxAttempts) {
        int targetNumber = generateRandomNumber(1, 100);
        int attempts = 0;
        boolean isGuessed = false;

        System.out.println("\n-- Round " + roundNumber + " --");
        System.out.println("I have selected a number between 1 and 100. Try to guess it!");

        while (attempts < maxAttempts && !isGuessed) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();
            attempts++;

            if (userGuess == targetNumber) {
                System.out.println("Congratulations! You've guessed the number in " + attempts + " attempts.");
                isGuessed = true;
            } else if (userGuess > targetNumber) {
                System.out.println("Your guess is too high.");
            } else {
                System.out.println("Your guess is too low.");
            }
        }

        if (!isGuessed) {
            System.out.println("Sorry! You've used all " + maxAttempts + " attempts. The correct number was: " + targetNumber);
        }
    }

    public static void startGame() {
        Scanner scanner = new Scanner(System.in);
        int roundsWon = 0;
        int totalRounds = 0;
        final int maxAttempts = 5;

        System.out.println("Welcome to the Guess the Number Game!");
        System.out.println("You will have " + maxAttempts + " attempts to guess the number.");

        boolean playAgain = true;

        while (playAgain) {
            totalRounds++;
            playRound(scanner, totalRounds, maxAttempts);

            System.out.print("Do you want to play another round? (yes/no): ");
            String response = scanner.next().toLowerCase();

            if (response.equals("no")) {
                playAgain = false;
            }

            System.out.print("Did you win this round? (yes/no): ");
            String winResponse = scanner.next().toLowerCase();
            if (winResponse.equals("yes")) {
                roundsWon++;
            }
        }

        System.out.println("\n-- Game Over --");
        System.out.println("Total Rounds Played: " + totalRounds);
        System.out.println("Rounds Won: " + roundsWon);
        System.out.println("Thank you for playing!");

        scanner.close();
    }

    public static void main(String[] args) {
        startGame();
    }
}
