package br.com.alura.forum.exception;

public class MailServiceException extends RuntimeException {

    public MailServiceException(String message, Throwable e) {
        super(message, e);
    }
}
