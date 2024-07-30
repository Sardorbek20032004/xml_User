package org.example;

import org.example.model.Card;
import org.example.model.User;
import org.example.servise.CardService;
import org.example.servise.Cards;
import org.example.servise.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    static UserService userService = new UserService();
    static CardService cardService = new CardService();
    static Scanner scannerStr = new Scanner(System.in);
    static Scanner scannerInt = new Scanner(System.in);
    static   User user = null;
    public static void main(String[] args)  {
      int stepCode = 10;
      while (stepCode != 0){
          System.out.println("1.Register      2. Login      0. exit");
          stepCode = scannerInt.nextInt();
          switch (stepCode){
              case 1 -> register();
              case 2 -> login();
          }

      }
    }
    private static void register(){
        UUID id = UUID.randomUUID();
        System.out.print("enter name:  ");
        String name = scannerStr.nextLine();
        System.out.print("enter user name:  ");
        String username = scannerStr.nextLine();
        System.out.print("enter password:  ");
        String password = scannerStr.nextLine();
        System.out.print("enter age:  ");
        int age = scannerInt.nextInt();
       User user =  userService.add(new User(id,name,username,password,age));
       if (user != null){
           System.out.println("user added");
       }else {
           System.out.println("user not added");
       }
    }
    private static void login(){
        System.out.print("enter user name:  ");
        String username = scannerStr.nextLine();
        System.out.print("enter password:  ");
        String password = scannerStr.nextLine();
         user = userService.login(username,password);
        if (user != null){
            int stepCode = 10;
            while (stepCode != 0){
                System.out.println("1. Add card      2. My card list      0. exit");
                stepCode = scannerInt.nextInt();
                switch (stepCode){
                    case 1 -> addCard();
                    case 2 -> myCard();
                }
            }
        }else{
            System.out.println("user not found");
        }
    }
    private static void addCard(){
        System.out.print("enter card name");
        String name = scannerStr.nextLine();
        System.out.print("enter card number");
        String number = scannerStr.nextLine();
        System.out.print("enter card expire date");
       String expireDate = scannerStr.nextLine();
       if(cardService.add(new Card(UUID.randomUUID(),user.getId(),name,number,expireDate)) != null){
           System.out.println("card adder");
       }else{
           System.out.println("card noot added");
       }
    }
    private static void myCard(){
        int n = 1;
        ArrayList<Card> cards = cardService.list(user.getId());
        for (Card card : cards){
            System.out.println(n + ". " + card);
            n ++;
        }
    }
}