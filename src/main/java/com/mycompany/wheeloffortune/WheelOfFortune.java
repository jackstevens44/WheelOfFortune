/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.wheeloffortune;

/**
 *
 * @author jackstevens
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.*;
import java.util.Scanner;

public class WheelOfFortune {
    private static String word;
    private static char[] wordCover;
    
    public static void main(String[] args) {
        try {
            String randomLine = getRandomLineFromFile("/Users/jackstevens/Downloads/samples.txt");
            word = randomLine.toLowerCase();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        int[] playertotal = new int[3];       
        playertotal[0] = 0;
        playertotal[1] = 0;
        playertotal[2] = 0;
        int spin;
        
        
        int currentPlayer = 1;
        wordCover = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            wordCover[i] = '-';
        }

        Scanner scanner = new Scanner(System.in);
        while (!isWordGuessed()) {
            for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == ' ' && wordCover[i] == '-') {
                        wordCover[i] = ' ';
                         
                    }
            }
            spin = Spin();
            
            
            System.out.println("\nPlayer " + currentPlayer + "'s turn!");
            if (spin == -1){
                playertotal[currentPlayer] = 0;
                System.out.println("Bankrupt!");
                spin = 0;
            }
            else
                ;
            System.out.println("You have "+ playertotal[currentPlayer]+" points");
            System.out.println("Your spin was: " + spin);
            System.out.println("Current word: " + String.valueOf(wordCover));
            System.out.print("Enter a letter or the entire word: ");
            String guess = scanner.nextLine().toLowerCase();

            if (guess.length() == 1) {
                boolean foundLetter = false;
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == guess.charAt(0) && wordCover[i] == '-') {
                        wordCover[i] = guess.charAt(0);
                        foundLetter = true;
                    }
                }

                if (foundLetter) {
                    System.out.println("Correct! The letter '" + guess + "' is in the word.");
                    playertotal[currentPlayer] += spin;
                    System.out.println("You get " + spin+" points! Your new total is: " + playertotal[currentPlayer]);
                } else {
                    System.out.println("Incorrect! The letter '" + guess + "' is not in the word.");
                }
            } else if (guess.equals(word)) {
                break;
            } else {
                System.out.println("Incorrect! The word is not '" + guess + "'.");
            }

            currentPlayer = 3 - currentPlayer; // Switch between 1 and 2
        }

        System.out.println("Congratulations, Player " + currentPlayer + "! You've correctly guessed the word: " + word);
        scanner.close();
    }

    private static boolean isWordGuessed() {
        for (char c : wordCover) {
            if (c == '-') {
                return false;
            }
        }
        return true;
    }
    public static int Spin(){
        int spinValue = 0;
        Random r = new Random();
        int num = r.nextInt(21);
        if (num==1||num==2||num==3||num==4){
            spinValue = 600;
        }
        else if (num==5||num==6||num==7){
            spinValue = 650;
        }
        else if (num==8||num==9||num==10||num==11){
            spinValue = 500;
        }
        else if (num==12){
            spinValue = 550;
        }
        else if (num==13||num==14||num==15){
            spinValue = 700;
        }
        else if (num==16){
            spinValue = 800;
        }
        else if (num==17){
            spinValue = 900;
        }
        else if (num==18){
            spinValue = 2500;
        }
        //bankrupt
        else if (num==19||num==20||num==21){
            spinValue = -1;
        }
        
        return spinValue;
    }
    public static String getRandomLineFromFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        if (lines.isEmpty()) {
            throw new IOException("The file is empty.");
        }

        Random random = new Random();
        int randomIndex = random.nextInt(lines.size());
        return lines.get(randomIndex);
    }
}
