package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

public class Meeting {
    public final String meetingName;
    public final String venue;
    public final String when;

    public Meeting(String meetingName, String venue, String when) {
        requireAllNonNull(meetingName, venue, when);
        this.meetingName = meetingName;
        this.venue = venue;
        this.when = when;
    }

    @Override
    public String toString() {
        return String.format("%s at %s (%s)",  meetingName, venue, when);
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
