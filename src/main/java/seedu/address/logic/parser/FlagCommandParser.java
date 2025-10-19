package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.DeleteMeetingCommand.MESSAGE_INVALID_BLANK;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.FlagCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class FlagCommandParser implements Parser<FlagCommand> {

    @Override
    public FlagCommand parse(String args) throws ParseException {
        try {

            // try to parse the index
            Index index = ParserUtil.parseIndex(args);

            assert(index.getZeroBased() >= 0);

            return new FlagCommand(index);
        } catch (ParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FlagCommand.MESSAGE_USAGE, e)
            );
        }
    }

}
