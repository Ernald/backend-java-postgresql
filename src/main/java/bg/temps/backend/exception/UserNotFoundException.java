package bg.temps.backend.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserNotFoundException extends UsernameNotFoundException {
    public UserNotFoundException() {
        super("User not found.");
    }
    public UserNotFoundException(String str) {
        super(str);
    }
}
