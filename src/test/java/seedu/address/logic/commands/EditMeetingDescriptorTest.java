package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VENUE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WHEN_BOB;
import static seedu.address.logic.commands.EditMeetingCommand.EditMeetingDescriptor;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.EditMeetingDescriptorBuilder;

public class EditMeetingDescriptorTest {

    @Test
    public void equals() throws ParseException {
        // same values -> returns true
        EditMeetingDescriptor descriptorWithSameValues = new EditMeetingDescriptor(MEETING_AMY);
        assertTrue(MEETING_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(MEETING_AMY.equals(MEETING_AMY));

        // null -> returns false
        assertFalse(MEETING_AMY.equals(null));

        // different types -> returns false
        assertFalse(MEETING_AMY.equals(5));

        // different values -> returns false
        assertFalse(MEETING_AMY.equals(MEETING_BOB));

        // different meeting name -> returns false
        EditMeetingDescriptor editedMeeting = new EditMeetingDescriptorBuilder(MEETING_AMY)
                .withMeetingName(VALID_MEETING_BOB).build();
        assertFalse(MEETING_AMY.equals(editedMeeting));

        // different venue -> returns false
        editedMeeting = new EditMeetingDescriptorBuilder(MEETING_AMY).withVenue(VALID_VENUE_BOB).build();
        assertFalse(MEETING_AMY.equals(editedMeeting));

        // different when -> returns false
        editedMeeting = new EditMeetingDescriptorBuilder(MEETING_AMY).withWhen(VALID_WHEN_BOB).build();
        assertFalse(MEETING_AMY.equals(editedMeeting));
    }

    @Test
    public void toStringMethod() {
        EditMeetingDescriptor descriptor = new EditMeetingDescriptor(MEETING_AMY);
        String expectedString = MEETING_AMY.toString();
        assertEquals(expectedString, descriptor.toString());

        descriptor = new EditMeetingDescriptor(MEETING_BOB);
        expectedString = MEETING_BOB.toString();
        assertEquals(expectedString, descriptor.toString());

        descriptor = new EditMeetingDescriptor();
        expectedString = EditMeetingDescriptor.class.getCanonicalName() + "{meetingName="
                + descriptor.getMeetingName().orElse(null)
                + ", venue=" + descriptor.getVenue().orElse(null)
                + ", when=" + descriptor.getWhen().orElse(null) + "}";
        assertEquals(expectedString, descriptor.toString());
    }
}
