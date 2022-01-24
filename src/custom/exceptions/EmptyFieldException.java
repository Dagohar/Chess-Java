package custom.exceptions;

public class EmptyFieldException extends Exception {
    public EmptyFieldException() { }

    @Override
    public String getMessage() {
        return "Wybrane pole jest puste.";
    }
}
