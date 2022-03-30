import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("1 or 2 players?");
        String players = keyboard.nextLine();
        String word;

        // If the 1 player option is chosen
        if(players.equals("1")) {
            Scanner scanner = new Scanner(new File("src/words_alpha.txt"));
            List<String> words = new ArrayList<>();

            while(scanner.hasNext()) {
                // System.out.println(scanner.nextLine()); -> Prove that we can read from the file.
                words.add(scanner.nextLine());
            }

            Random rand = new Random();
            word = words.get(rand.nextInt(words.size()));  // Pick one word from our list of words and the index of that word will be between 0 and the length of our list of words
        }
        // Otherwise, we assume, it is 2 players
        else {
            System.out.print("Player 1, please enter your word: ");
            word = keyboard.nextLine();
            // We cannot clear the console in Java the way we wanted to, so the player 2 should not see the entered word.
            // So we need to print out many new lines, hoping that player 2 won't cheat by scrolling up :)
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("Ready for player 2! Good luck!");
        }

        // System.out.println(word); // -> Prove

        // Keep a list of letters, that the player has guessed and iterate through each letter in our actual word
        // If one letter is correct, we print out that particular letter instead of - (dash)
        List<Character> player_guesses = new ArrayList<>();

        int wrong_count = 0;
        while(true) {
            printHangedMan(wrong_count);

            if(wrong_count >= 6) {
                System.out.println("You lose!");
                System.out.println("The word was: " + word);
                break;
            }

            printWordState(word, player_guesses);
            if(!getPlayerGuess(keyboard, word, player_guesses)) {
                wrong_count++;
            }

            // If the player has won, break
            if(printWordState(word, player_guesses)) {
                System.out.println("You win!");
                break;
            }

            // If the player has not won, they get a chance to guess the word
            System.out.print("Please enter your guess of the word: ");
            if(keyboard.nextLine().equals(word)) {
                System.out.println("You win!");
                break;
            } else {
                System.out.println("Nope! Try again.");
            }
        }
    }

    private static void printHangedMan(int wrong_count) {
        System.out.println(" -------");
        System.out.println(" |     |");
        if(wrong_count >= 1) {
            System.out.println(" O");
        }

        if(wrong_count >= 2) {
            System.out.print("\\ ");
            if(wrong_count >= 3) {
                System.out.println("/");
            } else {
                System.out.println();
            }
        }

        if(wrong_count >= 4) {
            System.out.println(" |");
        }

        if(wrong_count >= 5) {
            System.out.print("/ ");
            if(wrong_count >= 6) {
                System.out.println("\\");
            } else {
                System.out.println();
            }
        }
        System.out.println();
        System.out.println();
    }

    private static boolean getPlayerGuess(Scanner keyboard, String word, List<Character> player_guesses) {
        // Start the loop of getting the input from the user and adding to the list
        System.out.print("Please enter a letter: ");
        String letter_guess = keyboard.nextLine();
        // Taking only the first character of the input, no cheat!
        player_guesses.add(letter_guess.charAt(0));

        return word.contains(letter_guess);
    }

    private static boolean printWordState(String word, List<Character> player_guesses) {
        int correct_count = 0;
        for(int i = 0; i< word.length(); i++) {
            if(player_guesses.contains(word.charAt(i))) {  // Check if the player has guessed the character at the location that we are currently working at
                System.out.print(word.charAt(i));
                correct_count++;
            }
            else {
                System.out.print("-");
            }
        }
        System.out.println();

        return (word.length() == correct_count);
    }
}









