package by.epam.lab.exception;

public class ArrayNotSortedException extends Exception {

    public ArrayNotSortedException() {
        super("Array not sorted, result may be unexpected. ");
    }

    public ArrayNotSortedException(String message) {
        super("Array not sorted, result may be unexpected. " + message);
    }

    public ArrayNotSortedException(String message, Throwable cause) {
        super("Array not sorted, result may be unexpected. " + message, cause);
    }

    public ArrayNotSortedException(Throwable cause) {
        super(cause);
    }
}
