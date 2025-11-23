package pe.edu.upeu.sysflex.service.exception;

public class ServiceSqlException extends RuntimeException {
    public ServiceSqlException(String message) {
        super(message);
    }

    public ServiceSqlException(String message, Throwable cause) {
        super(message, cause);
    }
}