package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AddMeetingCommand.MESSAGE_ARGUMENTS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VENUE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VENUE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WHEN_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WHEN_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;

public class AddMeetingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void exectute() {
        final String meetingName = "Some meeting";
        final String venue = "Some venue";
        final String when = "Some datetime";
        final Meeting meeting = new Meeting(meetingName, venue, when);
        System.out.println();

        assertCommandFailure(new AddMeetingCommand(INDEX_FIRST_PERSON, meeting), model,
                String.format(MESSAGE_ARGUMENTS, INDEX_FIRST_PERSON.getOneBased(), meeting));
    }

    @Test
    public void equals() {
        final AddMeetingCommand standardAddMeetingCommand = new AddMeetingCommand(INDEX_FIRST_PERSON,
                new Meeting(VALID_MEETING_AMY, VALID_VENUE_AMY, VALID_WHEN_AMY));

        AddMeetingCommand commandWithSameValues = new AddMeetingCommand(INDEX_FIRST_PERSON,
                new Meeting(VALID_MEETING_AMY, VALID_VENUE_AMY, VALID_WHEN_AMY));
        assertTrue(standardAddMeetingCommand.equals(commandWithSameValues));

        assertTrue(standardAddMeetingCommand.equals(standardAddMeetingCommand));

        assertFalse(standardAddMeetingCommand.equals(null));

        assertFalse(standardAddMeetingCommand.equals(new ListCommand()));

        assertFalse(standardAddMeetingCommand.equals(new AddMeetingCommand(INDEX_SECOND_PERSON,
                new Meeting(VALID_MEETING_AMY, VALID_VENUE_AMY, VALID_WHEN_AMY))));

        assertFalse(standardAddMeetingCommand.equals(new AddMeetingCommand(INDEX_FIRST_PERSON,
                new Meeting(VALID_MEETING_BOB, VALID_VENUE_BOB, VALID_WHEN_BOB))));
    }
}
