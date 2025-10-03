package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WHEN;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Meeting;

/**
 * Adds a meeting to an existing person in the address book.
 */
public class AddMeetingCommand extends Command {
    public static final String COMMAND_WORD = "addmeeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a meeting for the person identified "
            + "by the index number used in the last person listing. "
            + "A new meeting would be added to the person's list of current meetings.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_MEETING + "MEETING\n"
            + PREFIX_VENUE + "VENUE "
            + PREFIX_WHEN + "WHEN\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MEETING + "Financial advice sharing "
            + PREFIX_VENUE + "AMK Hub "
            + PREFIX_WHEN + "2025-11-01 1600";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Meeting: %2$s, Venue: %3$s, When: %4$s";

    private final Index index;
    private final Meeting meeting;

    public AddMeetingCommand(Index index, Meeting meeting) {
        requireAllNonNull(index, meeting);

        this.index = index;
        this.meeting = meeting;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(String.format(MESSAGE_ARGUMENTS, index.getOneBased(), meeting));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddMeetingCommand)) {
            return false;
        }

        AddMeetingCommand e = (AddMeetingCommand) other;
        return index.equals(e.index)
                && meeting.equals(e.meeting);
    }
}

