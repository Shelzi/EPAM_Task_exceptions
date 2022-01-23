package by.epam.lab.exception;

public class CsvArgsException extends AbstractCsvExceptions{
    public CsvArgsException() {
        super("Wrong parameter(s) in line ");
    }

    public CsvArgsException(String message) {
        super("Wrong parameter(s) in line " + message);
    }

    public CsvArgsException(String message, Throwable cause) {
        super("Wrong parameter(s) in line " + message, cause);
    }

    public CsvArgsException(Throwable cause) {
        super(cause);
    }
}