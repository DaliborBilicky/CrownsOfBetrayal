package dalibor.crownsofbetrayal.entities;

public class CapacityOverflowException extends Exception {
    public CapacityOverflowException() {
        super("The capacity is not enough for for that many things.");
    }
}
