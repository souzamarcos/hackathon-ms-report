package com.fiap.hackathon.usecase.misc.exception;

public class SendEmailException extends DomainException {
    public SendEmailException(Throwable exception) {
        super("And exception was thrown while sendidng email: ", exception);
    }
}
