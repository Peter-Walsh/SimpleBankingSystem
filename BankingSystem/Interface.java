package banking;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Interface {

    private Bank bank;

    public Interface(Bank bank) {
        this.bank = bank;
    }

    public void run() {
        Scanner scan = new Scanner(System.in);
        int choice = 1;

        while (choice != 0) {
            printInitialOptions();
            choice = choice();

            switch (choice) {

                case 1:
                    printCreatedCard();
                    break;
                case 2:
                    System.out.println();
                    System.out.println("Enter your card number:");
                    String cardNumber = scan.nextLine().trim();
                    System.out.println("Enter your pin:");
                    String pin = scan.nextLine().trim();
                    if (Integer.parseInt(pin) == 0) {
                        choice = 0;
                        break;
                    }
                    Card card = bank.getCard(cardNumber, pin);
                    if (card == null) {
                        System.out.println();
                        System.out.println("Wrong card number or PIN!");
                    } else {
                        System.out.println();
                        System.out.println("You have successfully logged in!");
                        choice = runLogin(card.getNUMBER());
                    }
                    break;
            }
        }
        System.out.println();
        System.out.println("Bye!");
    }

    private int runLogin(String cardNumber) {
        int choice = 1;
        Card card;
        while (choice != 0 && choice != 5) {
            card = bank.getCard(cardNumber);
            printLoginOptions();
            choice = choice();
            switch (choice) {
                case 1:
                    System.out.println();
                    System.out.println(card.getBalance());
                    break;
                case 2:
                    updateBalance(card.getNUMBER());
                    break;
                case 3:
                    transfer(card);
                    break;
                case 4:
                    closeAccount(card.getNUMBER());
                    choice = 5;
                    break;
                case 5:
                    System.out.println();
                    System.out.println("You have successfully logged out!");
                    break;
            }
        }
        return choice;
    }


    private void transfer(Card card) {
        Scanner scan = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter card number:");
        String cardNumber2 = scan.nextLine();
        try {
            if (cardNumber2.equals(card.getNUMBER())) {
                System.out.println("You cannot transfer money to the same account!");
            } else {
                bank.deposit(cardNumber2, 0);
                int amount = transferAmount(card);
                if (amount != -1) {
                    bank.deposit(cardNumber2, amount);
                    bank.deposit(card.getNUMBER(), amount * -1);
                    System.out.println("Success!");
                } else {
                    System.out.println("Not enough money!");
                }
            }
        } catch (InvalidCardException e) {
            System.out.println("Probably you made a mistake in the card number. Please try again!");
        } catch (NoSuchElementException e) {
            System.out.println("Such a card does not exist");
        }
    }

    private int transferAmount(Card card) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter how much money you want to transfer:");
        int amount = scan.nextInt();
        if (amount > card.getBalance()) {
            return -1;
        }
        return amount;
    }

    private void updateBalance(String cardNumber){
        Scanner scan = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter income:");
        int amount = scan.nextInt();
        try {
            bank.deposit(cardNumber, amount);
            System.out.println("Income was added!");
        } catch (InvalidCardException e) {
            System.out.println("Probably you made mistake in the card number. Please try again!");
        } catch (NoSuchElementException e) {
            System.out.println("Such a card does not exist");
        }
    }

    private void closeAccount(String cardNumber)  {
        bank.deleteAccount(cardNumber);
        System.out.println();
        System.out.println("The account has been closed!");
    }

    private void printInitialOptions() {
        System.out.println();
        System.out.println("1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");
    }

    private void printCreatedCard() {
        Card card = this.bank.createCard();
        System.out.println();
        System.out.println("Your card has been created");
        System.out.println("Your card number: ");
        System.out.println(card.getNUMBER());
        System.out.println("Your card pin:");
        System.out.println(card.getPIN());
    }

    private void printLoginOptions() {
        System.out.println();
        System.out.println("1. Balance");
        System.out.println("2. Add income");
        System.out.println("3. Do transfer");
        System.out.println("4. Close account");
        System.out.println("5. Log out");
        System.out.println("0. Exit");
    }

    private Card login() {
        Scanner scan = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter your card number:");
        String cardNumber = scan.nextLine().trim();
        System.out.println("Enter your pin:");
        String pin = scan.nextLine().trim();
        return bank.getCard(cardNumber, pin);
    }

    private int choice() {
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }

}
