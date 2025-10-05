package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code DeleteMeetingCommand} object
 */
public class DeleteMeetingCommandParser implements Parser<DeleteMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code DeleteMeetingCommand}
     * and returns a {@code DeleteMeetingCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DeleteMeetingCommand parse(String args) throws ParseException {
        requireNonNull(args);

        // Checks if args is empty
        if (args.trim().isEmpty()) {
            throw new ParseException(DeleteMeetingCommand.MESSAGE_INVALID_BLANK);
        }

        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MEETING_INDEX);

        // Checks if person Index is blank
        if (argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(DeleteMeetingCommand.MESSAGE_INVALID_BLANK_PERSON_INDEX);
        }

        // Checks if personIndex is valid
        Index personIndex;
        try {
            personIndex = ParserUtil.parseIndex(argumentMultimap.getPreamble());
        } catch (IllegalArgumentException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMeetingCommand.MESSAGE_USAGE),
                    ive);
        }

        String meetingIndexString = argumentMultimap.getValue(PREFIX_MEETING_INDEX).orElse("");

        //Checks if meeting Index is blank
        if (meetingIndexString.isEmpty()) {
            throw new ParseException(DeleteMeetingCommand.MESSAGE_INVALID_BLANK_MEETING_INDEX);
        }

        // Checks if meetingIndex is valid
        Index meetingIndex;
        try {
            meetingIndex = ParserUtil.parseIndex(meetingIndexString);
        } catch (IllegalArgumentException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMeetingCommand.MESSAGE_USAGE),
                    ive);
        }

        return new DeleteMeetingCommand(personIndex, meetingIndex);
    }
}
