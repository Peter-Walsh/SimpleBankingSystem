package banking;

public class InvalidCardException extends Exception{

    public InvalidCardException() {
        super("This card is not a valid card number");
    }
}
