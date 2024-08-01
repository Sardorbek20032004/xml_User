package org.example;

import org.example.model.Card;
import org.example.model.History;
import org.example.model.User;
import org.example.servise.CardService;
import org.example.servise.HistoryService;
import org.example.servise.UserService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY:MM:dd");
    static UserService userService = new UserService();
    static HistoryService historyService = new HistoryService();
    static CardService cardService = new CardService();
    static Scanner scannerStr = new Scanner(System.in);
    static Scanner scannerInt = new Scanner(System.in);
    static User user = null;

    public static void main(String[] args) {
        int stepCode = 10;
        while (stepCode != 0) {
            System.out.println("1.Register      2. Login      0. exit");
            stepCode = scannerInt.nextInt();
            switch (stepCode) {
                case 1 -> register();
                case 2 -> login();
            }

        }
    }

    private static void register() {
        UUID id = UUID.randomUUID();
        System.out.print("enter name:  ");
        String name = scannerStr.nextLine();
        System.out.print("enter user name:  ");
        String username = scannerStr.nextLine();
        System.out.print("enter password:  ");
        String password = scannerStr.nextLine();
        System.out.print("enter age:  ");
        int age = scannerInt.nextInt();
        User user = userService.add(new User(id, name, username, password, age));
        if (user != null) {
            System.out.println("user added");
        } else {
            System.out.println("user not added");
        }
    }

    private static void login() {
        System.out.print("enter user name:  ");
        String username = scannerStr.nextLine();
        System.out.print("enter password:  ");
        String password = scannerStr.nextLine();
        user = userService.login(username, password);
        if (user != null) {
            int stepCode = 10;
            while (stepCode != 0) {
                System.out.println("1. Add card      2. My card list      3. P2P      4. My Histories      0. exit");
                stepCode = scannerInt.nextInt();
                switch (stepCode) {
                    case 1 -> addCard();
                    case 2 -> myCard();
                    case 3 -> p2p();
                    case 4 -> myHistory();
                }
            }
        } else {
            System.out.println("user not found");
        }
    }

    private static void addCard() {
        System.out.print("enter card name");
        String name = scannerStr.nextLine();
        System.out.print("enter card number");
        String number = scannerStr.nextLine();
        System.out.print("enter card expire date");
        String expireDate = scannerStr.nextLine();
        System.out.print("enter card amount");
        double amount = scannerInt.nextDouble();
        if (cardService.add(new Card(UUID.randomUUID(), user.getId(), name, number, expireDate, amount)) != null) {
            System.out.println("card adder");
        } else {
            System.out.println("card noot added");
        }
    }

    private static void myCard() {
        int n = 1;
        ArrayList<Card> cards = cardService.list(user.getId());
        for (Card card : cards) {
            System.out.println(n + ". " + card);
            n++;
        }
    }

    private static void p2p() {
        ArrayList<Card> cards = cardService.list(user.getId());
        int n = 1;
        for (Card card : cards) {
            System.out.println(n + ". " + card.getName() + " amount " + card.getAmount());
            n++;
        }
        if (cards.size() != 0) {
            System.out.print("enter card index(or exit -1):  ");
            n = scannerInt.nextInt();
            Card card;
            try {
                card = cards.get(n - 1);
            } catch (Exception e) {
                System.out.println("card not fount");
                return;
            }
            System.out.print("enter from card number:   ");
            String fromCardNumber = scannerStr.nextLine();
            Card fromCard = cardService.login(fromCardNumber);
            if (fromCard != null) {
                System.out.println("enter amout:  ");
                double amount = scannerInt.nextDouble();
                if (card.getAmount() >= amount) {
                    Date date = new Date();
                    String historyDate = simpleDateFormat.format(date);
                    historyService.add(new History(UUID.randomUUID(), card.getCardNumber(), fromCardNumber, amount, historyDate));
                    cardService.p2p(card.getCardNumber(),fromCardNumber,amount);
                } else {
                    System.out.println("Your account doesn't have enough funds");
                }
            } else {
                System.out.println("card not found");
            }
        }
    }

    private static void myHistory() {
        ArrayList<Card> cards = cardService.list(user.getId());
        int n = 1;
        for (Card card : cards) {
            System.out.println(n + ". " + card.getName() + " amount " + card.getAmount());
            n++;
        }
        if (cards.size() != 0) {
            System.out.print("enter card index(or exit -1):  ");
            n = scannerInt.nextInt();
            Card card;
            try {
                card = cards.get(n - 1);
            } catch (Exception e) {
                System.out.println("card not fount");
                return;
            }
            int stepCode = 10;
            while (stepCode != 0) {
                System.out.println("1. All history      2. Limit history      0. exit");
                stepCode = scannerInt.nextInt();
                switch (stepCode) {
                    case 1 -> allHistory(card);
                    case 2 -> limitHistory(card);
                }
            }
        }
    }

    private static void allHistory(Card card) {
        ArrayList<History> histories = historyService.list(card);
        for (History history : histories) {
            System.out.println(history);
        }
    }
    private static void limitHistory(Card card){
        System.out.print("enter start date(format YYYY:MM:dd):   ");
        String startDate = scannerStr.nextLine();
        System.out.print("enter finish date(format YYYY:MM:dd):   ");
        String finishdate = scannerStr.nextLine();
        Date start = new Date();
        Date finish = new Date();
        try {
            start = simpleDateFormat.parse(startDate);
            System.out.println("startd " + startDate);
            finish = simpleDateFormat.parse(startDate);
            System.out.println("finish " + finishdate);
        }catch (Exception e){
            System.out.println("You entered the wrong format");
            return;
        }
        ArrayList<History> histories = historyService.list(start,finish,card);
        int n = 1;
        for (History history: histories){
            System.out.println(n + ". " + history);
            n ++;
        }
    }
}
