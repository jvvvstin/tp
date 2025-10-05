package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.meeting.Meeting;

public class MeetingTest {
    @Test
    public void equals() {
        Meeting meeting = new Meeting("meeting", "venue", "datetime");

        assertTrue(meeting.equals(meeting));

        Meeting sameMeeting = new Meeting(meeting.meetingName, meeting.venue, meeting.when);
        assertTrue(meeting.equals(sameMeeting));

        assertFalse(meeting.equals(1));

        assertFalse(meeting.equals(null));

        Meeting differentMeeting = new Meeting("another meeting", "another venue",
                "another datetime");
        assertFalse(meeting.equals(differentMeeting));
    }
}
