package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.EditCommand.createEditedPerson;

import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.FlagStatus;
import seedu.address.model.person.Person;

/**
 * Unflags a person identified using it's displayed index from the address book.
 */
public class UnflagCommand extends Command {
    public static final String COMMAND_WORD = "unflag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unflags the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_FLAG_PERSON_SUCCESS = "Unflagged Person: %1$s";
    public static final String MESSAGE_ALREADY_FLAGGED = "This person is already unflagged.";
    private static final Boolean UNFLAGGED_STATUS = false;

    private final Logger logger = LogsCenter.getLogger(getClass());

    private final Index targetIndex;

    /**
     * Creates an UnflagCommand to unflag the person at the specified {@code targetIndex}.
     */
    public UnflagCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        assert(targetIndex.getZeroBased() >= 0);

        this.targetIndex = targetIndex;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert(targetIndex.getZeroBased() >= 0);

        List<Person> lastShownList = model.getPersonList();

        // zero-based index is longer than list size
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToUnflag = lastShownList.get(targetIndex.getZeroBased());

        // check if person is already unflagged
        if (!personToUnflag.isFlagged()) {
            throw new CommandException(MESSAGE_ALREADY_FLAGGED);
        }

        // create an edited person descriptor with flag status set to false
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        editPersonDescriptor.setFlagStatus(new FlagStatus(UNFLAGGED_STATUS));
        Person unflaggedPerson = createEditedPerson(personToUnflag, editPersonDescriptor);

        model.setPerson(personToUnflag, unflaggedPerson);

        // log the unflagging transition to ensure unflagging took place
        logger.info(String.format("%s was flagged (%b -> %b)",
                unflaggedPerson.getName().toString(),
                personToUnflag.isFlagged(),
                unflaggedPerson.isFlagged()));

        return new CommandResult(String.format(MESSAGE_FLAG_PERSON_SUCCESS,
                Messages.format(unflaggedPerson)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof UnflagCommand)) {
            return false;
        }

        UnflagCommand otherUnflagPersonCommand = (UnflagCommand) other;
        return targetIndex.equals(otherUnflagPersonCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}

