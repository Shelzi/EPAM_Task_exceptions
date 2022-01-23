package by.epam.lab.exception;

public class CsvLineException extends Exception {
    public CsvLineException() {
        super("Wrong line format : ");
    }

    public CsvLineException(String message) {
        super("Wrong line format : " + message);
    }

    public CsvLineException(String message, Throwable cause) {
        super(message, cause);
    }

    public CsvLineException(Throwable cause) {
        super(cause);
    }

}
