package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Peron's other numbers in the address books.
 */
public class OtherPhones {
    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and it should be at "
                   + "least 3 digits long and numbers should be separared by a tag e.g. 9999 (work) 8888 (office)";

    // Single phone number pattern
    private static final String SINGLE_PHONE_REGEX =
            "^\\s*(?:\\+\\d{1,3}\\s*)?\\d{3,}(?:\\s*x\\d+)?\\s*$";

    // Multiple tagged phone numbers pattern
    private static final String MULTI_PHONE_REGEX =
            "^\\s*(?:(?:\\+\\d{1,3}\\s*)?\\d{3,}(?:\\s*x\\d+)?\\s*\\([^()]+\\)\\s*)+$";

    public final String value;

    /**
     * Constructs a {@code OtherPhones}
     * @param phones Valid phone numbers with a tag each or a single phone number
     */
    public OtherPhones(String phones) {
        if (phones != null && !phones.equals("")) {
            phones = phones.trim();
            checkArgument(isValidPhone(phones), MESSAGE_CONSTRAINTS);
        }

        this.value = phones;
    }

    /**
     * Returns true if the given string follows the specified format
     * @param test
     * @return boolean
     */
    public static boolean isValidPhone(String test) {
        return test.matches(SINGLE_PHONE_REGEX) || test.matches(MULTI_PHONE_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof OtherPhones)) {
            return false;
        }

        OtherPhones otherPhone = (OtherPhones) other;
        return value.equals(otherPhone.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
