package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.PersonBuilder;
public class MeetingNameContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        MeetingNameContainsKeywordsPredicate firstPredicate =
                new MeetingNameContainsKeywordsPredicate(firstPredicateKeywordList);
        MeetingNameContainsKeywordsPredicate secondPredicate =
                new MeetingNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MeetingNameContainsKeywordsPredicate firstPredicateCopy =
                new MeetingNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different meeting name -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_meetingNameContainsKeywords_returnsTrue() {
        // One keyword
        MeetingNameContainsKeywordsPredicate predicate =
                new MeetingNameContainsKeywordsPredicate(
                        Collections.singletonList("townhall"));

        Meeting meeting = null;
        try {
            meeting = new Meeting("Annual townhall meeting", "Level 3 meeting room",
                    "21-10-2025 14:30");
        } catch (ParseException e) {
            fail();
        }

        assertTrue(predicate.test(new PersonBuilder().withMeetings(meeting).build()));

        // Multiple keywords
        predicate = new MeetingNameContainsKeywordsPredicate(Arrays.asList("townhall", "meeting"));
        assertTrue(predicate.test(new PersonBuilder().withMeetings(meeting).build()));

        // Only one matching keyword
        predicate = new MeetingNameContainsKeywordsPredicate(Arrays.asList("meeting", "food"));
        assertTrue(predicate.test(new PersonBuilder().withMeetings(meeting).build()));

        // Mixed-case keywords
        predicate = new MeetingNameContainsKeywordsPredicate(Arrays.asList("aNnUaL", "tOdaY"));
        assertTrue(predicate.test(new PersonBuilder().withMeetings(meeting).build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        Meeting meeting = null;
        try {
            meeting = new Meeting("Annual townhall meeting", "Level 4 red room",
                    "21-10-2025 14:30");
        } catch (ParseException e) {
            fail();
        }

        // Zero keywords
        MeetingNameContainsKeywordsPredicate predicate =
                new MeetingNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withMeetings(meeting).build()));

        // Non-matching keyword
        predicate = new MeetingNameContainsKeywordsPredicate(Arrays.asList("room"));
        assertFalse(predicate.test(new PersonBuilder().withMeetings(meeting).build()));

        // Keywords match phone, email and address, but does not match meeting name
        predicate = new MeetingNameContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));

        // Keywords match meeting venue and when but not meeting name
        predicate = new MeetingNameContainsKeywordsPredicate(
                Arrays.asList("Level", "4", "red", "room", "21-10-2025", "14:30"));
        assertFalse(predicate.test(new PersonBuilder().withMeetings(meeting).build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        MeetingNameContainsKeywordsPredicate predicate = new MeetingNameContainsKeywordsPredicate(keywords);

        String expected = MeetingNameContainsKeywordsPredicate.class.getCanonicalName()
                + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
