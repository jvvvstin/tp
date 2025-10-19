package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.logic.Messages.MESSAGE_INVALID_DATETIME_FORMAT;

import java.time.LocalDateTime;

import seedu.address.logic.parser.DateTimeParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a Meeting's date and time.
 * Guarantees: immutable; is valid as declared in {@link #isValidWhen(String)}
 */
public class When {
    public static final String MESSAGE_CONSTRAINTS = MESSAGE_INVALID_DATETIME_FORMAT;

    public final LocalDateTime value;

    /**
     * Constructs a {@code When}.
     *
     * @param when A valid date and time in String format.
     */
    public When(String when) throws ParseException {
        requireNonNull(when);
        checkArgument(isValidWhen(when), MESSAGE_CONSTRAINTS);
        value = DateTimeParser.parseDateTime(when);
    }

    /**
     * Constructs a {@code When}.
     *
     * @param when A valid date and time.
     */
    public When(LocalDateTime when) throws ParseException {
        requireNonNull(when);
        checkArgument(isValidWhen(when), MESSAGE_CONSTRAINTS);
        value = when;
    }

    /**
     * Returns true if a given string is a valid date and time.
     */
    public static boolean isValidWhen(String test) {
        try {
            DateTimeParser.parseDateTime(test);
            return true;
        } catch (ParseException pe) {
            return false;
        }
    }

    /**
     * Always returns true for a given {@code LocalDateTime}
     */
    public static boolean isValidWhen(LocalDateTime test) {
        return true;
    }

    /**
     * Returns true if the value is not empty
     */
    public boolean isEmpty() {
        return value == null;
    }

    @Override
    public String toString() {
        return DateTimeParser.format(value);
    }

    public LocalDateTime getWhen() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof When)) {
            return false;
        }

        When otherWhen = (When) other;
        return value.equals(otherWhen.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
