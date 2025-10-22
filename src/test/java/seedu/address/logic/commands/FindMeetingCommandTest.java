package seedu.address.logic.commands;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.MeetingNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindMeetingCommand}.
 */
public class FindMeetingCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        MeetingNameContainsKeywordsPredicate firstPredicate =
                new MeetingNameContainsKeywordsPredicate(Collections.singletonList("first"));
        MeetingNameContainsKeywordsPredicate secondPredicate =
                new MeetingNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindMeetingCommand findFirstCommand = new FindMeetingCommand(firstPredicate);
        FindMeetingCommand findSecondCommand = new FindMeetingCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindMeetingCommand findFirstCommandCopy = new FindMeetingCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different meeting name -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        MeetingNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindMeetingCommand command = new FindMeetingCommand(predicate);

        expectedModel.updatePersonListFilter(predicate);
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getPersonList());
    }

    @Test
    public void execute_validKeywords_noPersonFound() {
        MeetingNameContainsKeywordsPredicate predicate = preparePredicate("alpha bravo charlie");
        FindMeetingCommand command = new FindMeetingCommand(predicate);

        expectedModel.updatePersonListFilter(predicate);
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        MeetingNameContainsKeywordsPredicate predicate = preparePredicate("zoom google teams");
        FindMeetingCommand command = new FindMeetingCommand(predicate);

        expectedModel.updatePersonListFilter(predicate);
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getPersonList());
    }

    @Test
    public void toStringMethod() {
        MeetingNameContainsKeywordsPredicate predicate = new MeetingNameContainsKeywordsPredicate(
                Arrays.asList("keyword"));
        FindMeetingCommand findMeetingCommand = new FindMeetingCommand(predicate);
        String expected = FindMeetingCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";

        assertEquals(expected, findMeetingCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code MeetingNameContainsKeywordsPredicate}.
     */
    private MeetingNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new MeetingNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

