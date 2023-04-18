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
import java.util.Random;
import java.util.stream.*;
import java.util.Scanner;

public class WheelOfFortune {	
    public static void main(String[] args) {
        Spinner s = new Spinner();
        int player1total = 0;
        int player2total = 0;
        
	Random randNum = new Random();
        int n = randNum.nextInt(5);// The line number
        String phrase;
        try (Stream<String> lines = Files.lines(Paths.get("/Users/jackstevens/Downloads/samples.txt"))) {
        phrase = lines.skip(n).findFirst().get();
        phrase = phrase.toLowerCase();
        
          // Initialize the number of tries and guessed letters
            
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
            boolean p1turn = true, p2turn = true, playing = true; 
            
            while(playing){
                p1turn = true;
                p2turn = true;
                while(p1turn){
                int points;
                points = s.Spin();
                String guess = scanner.nextLine().toLowerCase();
                System.out.println("Hello");
                if (guess.equals(phrase)) {
                    System.out.println("Congratulations! You guessed the phrase!");
                    playing = false;
                    break;
                } else if (guess.length() > 1) {
                    System.out.println("Sorry, your guess was incorrect.");
                    p1turn = false;
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
                        player1total += points;
                        if (!guessedLetters.toString().contains("-")) {
                            System.out.println("Congratulations! You guessed the phrase!");
                            break;
                        }
                    } else {
                        System.out.println("Sorry, your guess was incorrect.");
                        p1turn = false;
                    }
                }
            }
            while(p2turn){
                int points;
                points = s.Spin();
                String guess = scanner.nextLine().toLowerCase();
                
                if (guess.equals(phrase)) {
                    System.out.println("Congratulations! You guessed the phrase!");
                    playing = false;
                    break;
                } else if (guess.length() > 1) {
                    System.out.println("Sorry, your guess was incorrect.");
                    p2turn = false;
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
                        player2total += points;
                        if (!guessedLetters.toString().contains("-")) {
                            System.out.println("Congratulations! You guessed the phrase!");
                            break;
                        }
                    } else {
                        System.out.println("Sorry, your guess was incorrect.");
                        p2turn = false;
                    }
                }
            }
                
            
                 
        }
        }
catch(IOException e){
        System.out.println(e);
      }        
      }  
}

