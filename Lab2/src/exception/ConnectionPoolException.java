package exception;

import java.sql.SQLException;

public class ConnectionPoolException extends SQLException {
    public ConnectionPoolException(String message) {
        super(message);
    }
}
