package jp.genuine.training.sample.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
		super();
	}
	
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ResourceNotFoundException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    private static final long serialVersionUID = 6962506753852347356L;
}
