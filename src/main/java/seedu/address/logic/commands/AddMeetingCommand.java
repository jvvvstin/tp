package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WHEN;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

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

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Add Meeting command not implemented yet";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_NOT_IMPLEMENTED_YET);
    }
}

