package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UnflagCommand;

public class UnflagCommandParserTest {

    private UnflagCommandParser parser = new UnflagCommandParser();

    @Test
    public void parse_validArgs_returnsUnflagCommand() {
        assertParseSuccess(parser, "1", new UnflagCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnflagCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnflagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "0", String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnflagCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnflagCommand.MESSAGE_USAGE));
    }
}
