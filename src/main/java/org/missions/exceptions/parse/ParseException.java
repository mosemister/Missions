package org.missions.exceptions.parse;

/**
 * The exception called when failing to parse X into Y
 */
public class ParseException extends Exception {

    /**
     * construct a Parse Exception
     *
     * @param parsing The value attempting to be parsed
     * @param to      The type that the value was attempted to be parsed into
     * @param reason  The reason why it failed to parse
     */
    public ParseException(Object parsing, Class<?> to, String reason) {
        super("Failed to convert " + parsing.toString() + " to " + to.getSimpleName() + " due to " + reason);
    }
}
