package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));
    }

    @Test
    public void isValidName_blankName_throwsParseException() {
        String blankName = "";
        assertThrows(ParseException.class, () -> Name.isValidName(blankName));

        // Check that the exception message is as expected
        try {
            Name.isValidName(blankName);
        } catch (ParseException e) {
            assertEquals(e.getMessage(), Name.MESSAGE_CONSTRAINTS_NO_BLANK_NAME);
        }
    }

    @Test
    public void isValidName_noAlphabetCharacter_throwsParseException() {
        String spacesOnly = " ";
        assertThrows(ParseException.class, () -> Name.isValidName(spacesOnly));

        // Check that the exception message is as expected
        try {
            Name.isValidName(spacesOnly);
        } catch (ParseException e) {
            assertEquals(e.getMessage(), Name.MESSAGE_CONSTRAINTS_AT_LEAST_ONE_ALPHABET);
        }
    }

    @Test
    public void isValidName_noAlphabetCharacter_throwsParseException() {
        String spacesOnly = " ";
        assertThrows(ParseException.class, () -> Name.isValidName(spacesOnly));

        // Check that the exception message is as expected
        try {
            Name.isValidName(spacesOnly);
        } catch (ParseException e) {
            assertEquals(e.getMessage(), Name.MESSAGE_CONSTRAINTS_AT_LEAST_ONE_ALPHABET);
        }
    }

    @Test
    public void isValidName_onlyNonValidCharacter_throwsParseException() {
        String nonValidCharacter = "^";
        assertThrows(ParseException.class, () -> Name.isValidName(nonValidCharacter));

        // Check that the exception message is as expected
        try {
            Name.isValidName(nonValidCharacter);
        } catch (ParseException e) {
            assertEquals(e.getMessage(), Name.MESSAGE_CONSTRAINTS);
        }
    }

    @Test
    public void isValidName_containsNonValidCharacter_throwsParseException() {
        String containsNonValidCharacter = "bobby^";
        assertThrows(ParseException.class, () -> Name.isValidName(containsNonValidCharacter));

        // Check that the exception message is as expected
        try {
            Name.isValidName(containsNonValidCharacter);
        } catch (ParseException e) {
            assertEquals(e.getMessage(), Name.MESSAGE_CONSTRAINTS);
        }
    }

    @Test
    public void isValidName_startsWithNonAlphabet_throwsParseException() {
        String startsWithNonAlphabet = "-bobby";
        assertThrows(ParseException.class, () -> Name.isValidName(startsWithNonAlphabet));

        // Check that the exception message is as expected
        try {
            Name.isValidName(startsWithNonAlphabet);
        } catch (ParseException e) {
            assertEquals(e.getMessage(), Name.MESSAGE_CONSTRAINTS_INVALID_START_END);
        }
    }

    @Test
    public void isValidName_endsWithNonAlphabet_throwsParseException() {
        String endsWithNonAlphabet = "bobby ";
        assertThrows(ParseException.class, () -> Name.isValidName(endsWithNonAlphabet));

        // Check that the exception message is as expected
        try {
            Name.isValidName(endsWithNonAlphabet);
        } catch (ParseException e) {
            assertEquals(e.getMessage(), Name.MESSAGE_CONSTRAINTS_INVALID_START_END);
        }
    }

    @Test
    public void isValidName_consecutiveSpecialCharacter_throwsParseException() {
        String consecutiveSpecialCharacter = "bob----------by";
        assertThrows(ParseException.class, () -> Name.isValidName(consecutiveSpecialCharacter));

        // Check that the exception message is as expected
        try {
            Name.isValidName(consecutiveSpecialCharacter);
        } catch (ParseException e) {
            assertEquals(e.getMessage(), Name.MESSAGE_CONSTRAINTS_NO_CONSECUTIVE_SPECIAL_CHAR);
        }
    }

    @Test
    public void isValidName() {
        // valid name
        try {
            assertTrue(Name.isValidName("peter jack")); // alphabets only
            assertTrue(Name.isValidName("12345")); // numbers only
            assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
            assertTrue(Name.isValidName("Capital Tan")); // with capital letters
            assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
        } catch (ParseException e) {
            // This block should not be reached
        }
    }

    @Test
    public void equals() {
        Name name = new Name("Valid Name");

        // same values -> returns true
        assertTrue(name.equals(new Name("Valid Name")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new Name("Other Valid Name")));
    }
}
