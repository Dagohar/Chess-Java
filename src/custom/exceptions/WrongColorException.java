package custom.exceptions;

import com.company.PlayerInput;

public class WrongColorException extends Exception {
    public WrongColorException() { }

    @Override
    public String getMessage() {
        return "Teraz ruch mają " + (PlayerInput.WhitesTurn ? "białe" : "czarne") + ".";
    }
}
