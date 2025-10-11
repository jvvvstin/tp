package seedu.address.model.meeting;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

import seedu.address.logic.parser.DateTimeParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a Meeting in the address book.
 */
public class Meeting {
    public final String meetingName;
    public final String venue;
    public final LocalDateTime when;

    /**
     * Constructs a {@code Meeting}
     * @param meetingName name of the meeting
     * @param venue venue of the meeting
     * @param when date and time of the meeting
     */
    public Meeting(String meetingName, String venue, String when) throws ParseException {
        requireAllNonNull(meetingName, venue, when);
        this.meetingName = meetingName;
        this.venue = venue;
        this.when = DateTimeParser.parseDateTime(when);
    }

    /**
     * Constructs a {@code Meeting}
     * @param meetingName name of the meeting
     * @param venue venue of the meeting
     * @param when date and time of the meeting in {@code String} format
     */
    public Meeting(String meetingName, String venue, LocalDateTime when) throws ParseException {
        requireAllNonNull(meetingName, venue, when);
        this.meetingName = meetingName;
        this.venue = venue;
        this.when = when;
    }

    @Override
    public String toString() {
        return String.format("%s at %s (%s)", meetingName, venue, DateTimeParser.format(when));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || other instanceof Meeting
                && meetingName.equals(((Meeting) other).meetingName)
                && venue.equals(((Meeting) other).venue)
                && when.equals(((Meeting) other).when);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetingName, venue, when);
    }
}
