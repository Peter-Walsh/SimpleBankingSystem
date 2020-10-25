package BankingSystem;

public class Interface {

    private Bank bank;

    public Interface(Bank bank) {
        this.bank = bank;
    }

    public void run() {
        int choice = 1;

        while (choice != 0) {
            printInitialOptions();
            choice = choice();

            switch (choice) {

                case 1:
                    printCreatedCard();
                    break;
                case 2:
                    Card card = login();
                    if (card == null) {
                        System.out.println();
                        System.out.println("Wrong card number or PIN!");
                    } else {
                        System.out.println();
                        System.out.println("You have successfully logged in!");
                        choice = runLogin(card);
                        System.out.println();
                        System.out.println("You have successfully logged out!");
                    }
                    break;
            }
            System.out.println();
            System.out.println("Bye!");
        }
    }

    private int runLogin(Card card) {
        printLoginOptions();
        int choice = choice();
        while (choice != 0 && choice != 2) {
            System.out.println();
            System.out.println("Balance:" + card.getBalance());
            printLoginOptions();
            choice = choice();
        }
        return choice;
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
        System.out.println("2. Log out");
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
