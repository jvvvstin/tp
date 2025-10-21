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
 * Flags a person identified using it's displayed index from the address book.
 */
public class FlagCommand extends Command {
    public static final String COMMAND_WORD = "flag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Flags the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_FLAG_PERSON_SUCCESS = "Flagged Person: %1$s";
    public static final String MESSAGE_ALREADY_FLAGGED = "This person is already flagged.";
    private static final Boolean FLAGGED_STATUS = true;

    private final Logger logger = LogsCenter.getLogger(getClass());

    private final Index targetIndex;

    /**
     * Creates a FlagCommand to flag the person at the specified {@code targetIndex}.
     */
    public FlagCommand(Index targetIndex) {
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

        Person personToFlag = lastShownList.get(targetIndex.getZeroBased());

        // check if person is already flagged
        if (personToFlag.isFlagged()) {
            throw new CommandException(MESSAGE_ALREADY_FLAGGED);
        }

        // create an edited person descriptor with flagged status set to true
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        editPersonDescriptor.setFlagStatus(new FlagStatus(FLAGGED_STATUS));
        Person flaggedPerson = createEditedPerson(personToFlag, editPersonDescriptor);

        model.setPerson(personToFlag, flaggedPerson);

        // log the flagging transition to ensure flagging took place
        logger.info(String.format("%s was flagged (%b -> %b)",
                flaggedPerson.getName().toString(),
                personToFlag.isFlagged(),
                flaggedPerson.isFlagged()));

        return new CommandResult(String.format(MESSAGE_FLAG_PERSON_SUCCESS, Messages.format(flaggedPerson)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FlagCommand)) {
            return false;
        }

        FlagCommand otherFlagPersonCommand = (FlagCommand) other;
        return targetIndex.equals(otherFlagPersonCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
