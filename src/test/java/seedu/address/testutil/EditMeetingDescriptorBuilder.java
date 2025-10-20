package seedu.address.testutil;

import static org.junit.jupiter.api.Assertions.fail;

import seedu.address.logic.commands.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.Venue;
import seedu.address.model.meeting.When;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditMeetingDescriptorBuilder {

    private EditMeetingDescriptor descriptor;

    public EditMeetingDescriptorBuilder() {
        descriptor = new EditMeetingDescriptor();
    }

    public EditMeetingDescriptorBuilder(EditMeetingDescriptor descriptor) {
        this.descriptor = new EditMeetingDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditMeetingDescriptor} with fields containing {@code meeting}'s details
     */
    public EditMeetingDescriptorBuilder(Meeting meeting) {
        descriptor = new EditMeetingDescriptor();
        descriptor.setMeetingName(meeting.getMeetingName());
        descriptor.setVenue(meeting.getVenue());
        descriptor.setWhen(meeting.getWhen());
    }

    /**
     * Sets the {@code meetingName} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withMeetingName(String meetingName) {
        descriptor.setMeetingName(new MeetingName(meetingName));
        return this;
    }

    /**
     * Sets the {@code venue} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withVenue(String venue) {
        descriptor.setVenue(new Venue(venue));
        return this;
    }

    /**
     * Sets the {@code when} of the {@code EditMeetingDescriptor} that we are building/
     */
    public EditMeetingDescriptorBuilder withWhen(String when) {
        try {
            descriptor.setWhen(new When(when));
        } catch (ParseException e) {
            // This exception can be safely ignored.
            fail();
        }
        return this;
    }

    public EditMeetingDescriptor build() {
        return descriptor;
    }
}
