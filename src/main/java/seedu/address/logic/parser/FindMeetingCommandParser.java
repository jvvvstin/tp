package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.MeetingNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindMeetingCommand object
 */
public class FindMeetingCommandParser implements Parser<FindMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindMeetingCommand
     * and returns a FindMeetingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindMeetingCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            String emptyArgMessage = "Your arguments should not be empty!\n";
            String exceptionMessage = emptyArgMessage + FindMeetingCommand.MESSAGE_USAGE;

            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, exceptionMessage));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        assert nameKeywords.length > 0 : "Keywords for findmt is empty!";

        return new FindMeetingCommand(new MeetingNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
