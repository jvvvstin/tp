package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meeting.Meeting;

/**
 * Jackson-friendly version of {@link Meeting}.
 */
public class JsonAdaptedMeeting {
    public final String meetingName;
    public final String venue;
    public final String when;

    /**
     * Constructs a {@code JsonAdaptedMeeting} with the given {@code meetingName}, {@code venue}, and {@code when}.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("meetingName") String meetingName, @JsonProperty("venue") String venue,
                              @JsonProperty("when") String when) {
        this.meetingName = meetingName;
        this.venue = venue;
        this.when = when;
    }

    /**
     * Converts a given {@code Meeting} into this class for Jackson use.
     */
    public JsonAdaptedMeeting(Meeting source) {
        this.meetingName = source.meetingName;
        this.venue = source.venue;
        this.when = source.when;
    }

    @JsonProperty("meetingName")
    public String getMeetingName() {
        return this.meetingName;
    }

    @JsonProperty("venue")
    public String getVenue() {
        return this.venue;
    }

    @JsonProperty("when")
    public String getWhen() {
        return this.when;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Meeting} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Meeting toModelType() throws IllegalValueException {
        return new Meeting(this.meetingName, this.venue, this.when);
    }
}
