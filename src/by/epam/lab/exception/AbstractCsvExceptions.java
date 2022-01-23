package by.epam.lab.exception;

public abstract class AbstractCsvExceptions extends Exception{
    public AbstractCsvExceptions() {
    }

    public AbstractCsvExceptions(String message) {
        super(message);
    }

    public AbstractCsvExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public AbstractCsvExceptions(Throwable cause) {
        super(cause);
    }
}
