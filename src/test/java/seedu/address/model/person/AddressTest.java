package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Address(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new Address(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> Address.isValidAddress(null));

        // invalid addresses
        assertFalse(Address.isValidAddress("")); // empty string
        assertFalse(Address.isValidAddress(" ")); // spaces only
        assertFalse(Email.isValidEmail("Kent Ridge (home) (school)")); // Another address missing
        assertFalse(Email.isValidEmail("Kent Ridge (home) "
                + "Jurong (home)")); // Duplicate labels
        assertFalse(Email.isValidEmail("Kent Ridge (home) "
                + "Kent Ridge (school)")); // Duplicate addresses
        assertFalse(Email.isValidEmail("test@gmail.com (main) "
                + "test@gmail.com (school)")); // Duplicate emails
        assertFalse(Email.isValidEmail("Kent Ridge ()")); // Empty label
        assertFalse(Email.isValidEmail("Kent Ridge (  )")); // Label with spaces only
        assertFalse(Email.isValidEmail("Kent Ridge (--)")); // Label with hyphen only
        assertFalse(Email.isValidEmail("Kent Ridge ( -- - )")); // Label with spaces and hyphens only
        assertFalse(Email.isValidEmail("Kent Ridge (Test / No)")); // Label other characters
        assertFalse(Email.isValidEmail("Kent Ridge (School) Jurong East(Home)")); // Label together with address

        // valid addresses
        assertTrue(Address.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(Address.isValidAddress("-")); // one character
        assertTrue(Address.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
        assertTrue(Address.isValidAddress("Blk 456, Den Road, #01-355 (House)")); // 1 Address with label
        assertTrue(Address.isValidAddress("Blk 456, Den Road, #01-355 (House) "
                + "Jurong Town, #09-355 (School) Blk 123, O Road, #01-355 (Work)")); // Multiple Address with label
        assertTrue(Address.isValidAddress(" Blk 456, Den Road, "
                + "#01-355 (House) ")); // Trailing spaces allowed
        assertTrue(Address.isValidAddress(" Blk 456, Den Road, "
                + "#01-355 (House 2) ")); // Space allowed in label
    }

    @Test
    public void equals() {
        Address address = new Address("Valid Address");

        // same values -> returns true
        assertTrue(address.equals(new Address("Valid Address")));

        // same object -> returns true
        assertTrue(address.equals(address));

        // null -> returns false
        assertFalse(address.equals(null));

        // different types -> returns false
        assertFalse(address.equals(5.0f));

        // different values -> returns false
        assertFalse(address.equals(new Address("Other Valid Address")));
    }
}
