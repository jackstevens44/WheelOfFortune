/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.wheeloffortune;

/**
 *
 * @author jackstevens
 */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.*;
import java.util.Scanner;

public class WheelOfFortune {	
    public static void main(String[] args) {
	 int n = 5; // The line number
        String phrase;
      try (Stream<String> lines = Files.lines(Paths.get("file.txt"))) {
        phrase = lines.skip(n).findFirst().get();
        
          // Initialize the number of tries and guessed letters
            int numTries = 10;
            StringBuilder guessedLetters = new StringBuilder();
            for (int i = 0; i < phrase.length(); i++) {
                if (phrase.charAt(i) == ' ') {
                    guessedLetters.append(" ");
                } else {
                    guessedLetters.append("-");
                }
            }
            
            // Prompt the user to guess a letter or the phrase
            Scanner scanner = new Scanner(System.in);
            while (numTries > 0) {
                System.out.println("Guess a letter or the phrase (you have " + numTries + " tries remaining):");
                String guess = scanner.nextLine().toLowerCase();
                
                if (guess.equals(phrase)) {
                    System.out.println("Congratulations! You guessed the phrase!");
                    break;
                } else if (guess.length() > 1) {
                    System.out.println("Sorry, your guess was incorrect. You lost one try.");
                    numTries--;
                } else {
                    boolean correctGuess = false;
                    for (int i = 0; i < phrase.length(); i++) {
                        if (guess.charAt(0) == phrase.charAt(i)) {
                            guessedLetters.setCharAt(i, guess.charAt(0));
                            correctGuess = true;
                        }
                    }
                    
                    if (correctGuess) {
                        System.out.println("Correct! The phrase now looks like this: " + guessedLetters);
                        if (!guessedLetters.toString().contains("-")) {
                            System.out.println("Congratulations! You guessed the phrase!");
                            break;
                        }
                    } else {
                        System.out.println("Sorry, your guess was incorrect. You lost one try.");
                        numTries--;
                    }
                }
            }
            
            if (numTries == 0) {
                System.out.println("Sorry, you ran out of tries. The phrase was: " + phrase);
            }
            
            scanner.close();
        }
catch(IOException e){
        System.out.println(e);
      }
        
      }
      
    }
}
