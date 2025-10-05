package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

public class DeleteMeetingCommand extends Command {
    public static final String COMMAND_WORD = "deletemeeting";

    public static final String MESSAGE_INVALID_MEETING_DISPLAYED_INDEX = "The meeting index provided is invalid";
    public static final String MESSAGE_DELETE_MEETING_SUCCESS = "Deleted meeting from Person: %1$s";

    private final Index personIndex;
    private final Index meetingIndex;

    public DeleteMeetingCommand(Index personIndex, Index meetingIndex) {
        requireAllNonNull(personIndex, meetingIndex);

        this.personIndex = personIndex;
        this.meetingIndex = meetingIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (personIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(personIndex.getZeroBased());
        List<Meeting> editedMeetings = personToEdit.getMeetings();

        if (meetingIndex.getZeroBased() >= editedMeetings.size()) {
            throw new CommandException(MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        editedMeetings.remove(meetingIndex.getZeroBased());
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(), editedMeetings);
        model.setPerson(editedPerson, personToEdit);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_DELETE_MEETING_SUCCESS, Messages.format(editedPerson)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteMeetingCommand)) {
            return false;
        }

        DeleteMeetingCommand otherCommand = (DeleteMeetingCommand) other;
        return personIndex.equals(otherCommand.personIndex)
                && meetingIndex.equals(otherCommand.meetingIndex);
    }
}
