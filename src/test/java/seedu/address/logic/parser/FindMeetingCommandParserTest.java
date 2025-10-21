package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.FindMeetingCommandParser.FIND_MEETING_COMMAND_PARSER_EMPTY_ARGS_MESSAGE;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindMeetingCommand;
import seedu.address.model.person.MeetingNameContainsKeywordsPredicate;

public class FindMeetingCommandParserTest {

    private FindMeetingCommandParser parser = new FindMeetingCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FIND_MEETING_COMMAND_PARSER_EMPTY_ARGS_MESSAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindMeetingCommand expectedFindCommand =
                new FindMeetingCommand(new MeetingNameContainsKeywordsPredicate(
                        Arrays.asList("meeting", "afternoon")));
        assertParseSuccess(parser, "meeting afternoon", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n meeting \n \t afternoon  \t", expectedFindCommand);
    }
}
