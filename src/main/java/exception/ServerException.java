package exception;

public class ServerException extends RuntimeException {

    private final StatusCode statusCode;

    public ServerException(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public ServerException(String message, Throwable cause, StatusCode statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }
}
