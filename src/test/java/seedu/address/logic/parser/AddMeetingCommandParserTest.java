package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WHEN;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddMeetingCommand;

public class AddMeetingCommandParserTest {
    private AddMeetingCommandParser parser = new AddMeetingCommandParser();
    private final String nonEmptyMeeting = "Some meeting";
    private final String nonEmptyVenue = "Some venue";
    private final String nonEmptyWhen = "Some datetime";

    @Test
    public void parse_indexSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_MEETING + nonEmptyMeeting + " " + PREFIX_VENUE
                + nonEmptyVenue + " " + PREFIX_WHEN + nonEmptyWhen;
        AddMeetingCommand expectedCommand = new AddMeetingCommand(INDEX_FIRST_PERSON, nonEmptyMeeting, nonEmptyVenue,
                nonEmptyWhen);
        assertParseSuccess(parser, userInput, expectedCommand);

        userInput = targetIndex.getOneBased() + " " + PREFIX_MEETING + " " + PREFIX_VENUE + " " + PREFIX_WHEN;
        expectedCommand = new AddMeetingCommand(INDEX_FIRST_PERSON, "", "", "");
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, AddMeetingCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, AddMeetingCommand.COMMAND_WORD + " " + nonEmptyMeeting + " " + nonEmptyVenue
                + " " + nonEmptyWhen, expectedMessage);
    }
}
