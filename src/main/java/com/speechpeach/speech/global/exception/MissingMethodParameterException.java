package com.speechpeach.speech.global.exception;

import org.aspectj.lang.Signature;

public class MissingMethodParameterException extends RuntimeException {

    public MissingMethodParameterException(Signature methodSignature, Class<?> clazz) {
        super(String.format("The %s parameter cannot be found in the %s.", clazz.getName(), methodSignature.toString()));
    }
}
