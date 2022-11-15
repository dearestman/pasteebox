package ru.test.pastbox.exceptions;

/**
 * @author Stupakov D. L.
 **/
public class NotFoundEntityException extends RuntimeException {
    public NotFoundEntityException(String s) {
        super(s);
    }
}
