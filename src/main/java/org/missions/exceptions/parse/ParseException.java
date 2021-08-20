package org.missions.exceptions.parse;

/**
 * The exception called when failing to parse X into Y
 */
public class ParseException extends Exception {

    public ParseException(Object parsing, Class<?> to, String reason) {
        super("Failed to convert " + parsing.toString() + " to " + to.getSimpleName() + " due to " + reason);
    }
}
