package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedMeeting.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meeting.Meeting;

public class JsonAdaptedMeetingTest {
    private static final String VALID_MEETING_NAME = "Financial sharing";
    private static final String VALID_VENUE = "AMK Hub";
    private static final LocalDateTime VALID_DATETIME = LocalDateTime.parse("2025-11-10T14:30:00");

    @Test
    public void toModelType_validMeetingDetails_returnsMeeting() throws Exception {
        Meeting meeting = new Meeting(VALID_MEETING_NAME, VALID_VENUE, VALID_DATETIME);
        JsonAdaptedMeeting jsonMeeting = new JsonAdaptedMeeting(meeting);
        assertEquals(meeting, jsonMeeting.toModelType());
    }

    @Test
    public void toModelType_nullMeetingName_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(null, VALID_VENUE, VALID_DATETIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Meeting.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test void toModelType_nullVenue_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(VALID_MEETING_NAME, null, VALID_DATETIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Meeting.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test void toModelType_nullDateTime_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(VALID_MEETING_NAME, VALID_VENUE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalDateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

}
