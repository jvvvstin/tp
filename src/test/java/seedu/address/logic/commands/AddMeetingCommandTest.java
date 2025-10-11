package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AddMeetingCommand.MESSAGE_BLANK_MEETING_NAME;
import static seedu.address.logic.commands.AddMeetingCommand.MESSAGE_BLANK_VENUE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AddMeetingCommandTest {

    private static final String MEETING_NAME_STUB = "Some meeting";
    private static final String MEETING_VENUE_STUB = "Some venue";
    private static final String MEETING_WHEN_STUB = "2025-10-11 1400";
    private static final String MEETING_NAME_STUB_2 = "Some meeting 2";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addMeetingUnfilteredList_success() throws ParseException {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withMeetings(
                new Meeting(MEETING_NAME_STUB, MEETING_VENUE_STUB, MEETING_WHEN_STUB)).build();

        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(INDEX_FIRST_PERSON,
                new Meeting(MEETING_NAME_STUB, MEETING_VENUE_STUB, MEETING_WHEN_STUB));

        String expectedMessage = String.format(AddMeetingCommand.MESSAGE_ADD_MEETING_SUCCESS,
                Messages.format(editedPerson));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(addMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() throws ParseException {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withMeetings(new Meeting(MEETING_NAME_STUB, MEETING_VENUE_STUB, MEETING_WHEN_STUB)).build();

        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(INDEX_FIRST_PERSON,
                new Meeting(MEETING_NAME_STUB, MEETING_VENUE_STUB, MEETING_WHEN_STUB));

        String expectedMessage = String.format(AddMeetingCommand.MESSAGE_ADD_MEETING_SUCCESS,
                Messages.format(editedPerson));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(addMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() throws ParseException {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(outOfBoundIndex,
                new Meeting(MEETING_NAME_STUB, MEETING_VENUE_STUB, MEETING_WHEN_STUB));

        assertCommandFailure(addMeetingCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() throws ParseException {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(outOfBoundIndex,
                new Meeting(MEETING_NAME_STUB, MEETING_VENUE_STUB, MEETING_WHEN_STUB));

        assertCommandFailure(addMeetingCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidMeetingFilteredList_failure() throws ParseException {
        Meeting meeting = new Meeting("", MEETING_VENUE_STUB, MEETING_WHEN_STUB);
        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(INDEX_FIRST_PERSON, meeting);
        assertCommandFailure(addMeetingCommand, model, MESSAGE_BLANK_MEETING_NAME);

        meeting = new Meeting(MEETING_NAME_STUB, "", MEETING_WHEN_STUB);
        addMeetingCommand = new AddMeetingCommand(INDEX_FIRST_PERSON, meeting);
        assertCommandFailure(addMeetingCommand, model, MESSAGE_BLANK_VENUE);
    }

    @Test
    public void equals() throws ParseException {
        Meeting meeting = new Meeting(MEETING_NAME_STUB, MEETING_VENUE_STUB, MEETING_WHEN_STUB);
        Meeting anotherMeeting = new Meeting(MEETING_NAME_STUB_2, MEETING_VENUE_STUB, MEETING_WHEN_STUB);
        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(INDEX_FIRST_PERSON, meeting);
        assertTrue(addMeetingCommand.equals(addMeetingCommand));

        assertFalse(addMeetingCommand.equals(null));

        assertFalse(addMeetingCommand.equals(new AddMeetingCommand(INDEX_FIRST_PERSON, anotherMeeting)));
    }
}
