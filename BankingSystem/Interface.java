package BankingSystem;

import java.util.Scanner;

public class Interface {

    private Bank bank;

    public int runBank() {

        // todo

        boolean run = true;
        while(run) {
            printInitialOptions();
            int choice = choice();

            switch (choice) {

                case 1:
                    createAccount();
                    break;

                case 2:

            }
        }
        return -1;
    }

    public int runLogin(String cardNumber) {
        // todo
        return -1;
    }

    public Interface(Bank bank) {
        this.bank = bank;
    }

    private void printInitialOptions() {
        System.out.println("1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");
    }

    private void printCreatedCard(Bank.Card card) {
        System.out.println("Your card has been created");
        System.out.println("Your card number: ");
        System.out.println(card.getNUMBER());
        System.out.println("Your card pin:");
        System.out.println(card.getPIN());
    }

    private void printLoginOptions() {
        System.out.println("1. Balance");
        System.out.println("2. Log out");
        System.out.println("0. Exit");
    }

    private boolean login() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your card number");
        String cardNumber = scan.next();
        System.out.print("Enter your pin");
        String pin = scan.next();
        return bank.hasCardAccess(cardNumber, pin);
    }

    private int choice() {
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }

    private void createAccount() {
        Bank.Card card = bank.createCard();
        printCreatedCard(card);
    }
}
