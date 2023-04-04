package com.mycompany.wheeloffortune;
/**
 *
 * @author jackstevens
 */
import java.util.Random;
import java.util.Scanner;
public class Spinner {
    
    public int Spin(){
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
    public int total(int currentTotal){
        int temp = Spin();
        if (temp==-1){
            System.out.println("BANKRUPT!");
            return 0;
        }
        else
            return currentTotal+temp;
    }
    public String getName(){
        Scanner read = new Scanner(System.in);
        System.out.println("What is your name? ");
        String name = read.nextLine();
        return name;
    }
}
