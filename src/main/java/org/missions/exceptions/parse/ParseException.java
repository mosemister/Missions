package org.missions.exceptions.parse;

public class ParseException extends Exception {

    public ParseException(Object parsing, Class<?> to, String reason) {
        super("Failed to convert " + parsing.toString() + " to " + to.getSimpleName() + " due to " + reason);
    }
}
