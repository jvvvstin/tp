package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Peron's other numbers in the address books.
 */
public class OtherPhones {
    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and it should be at least 3 digits long";
    public static final String VALIDATION_REGEX = "^(?:\\+\\d{2,} )?\\d{3,}(?: x\\d+)?$";
    public final String value;

    /**
     * Constructs a {@code Phone}
     * @param phones Valid phone numbers with a tag each or a single phone number
     */
    public OtherPhones(String phones) {
        if (phones != null){
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
        return test.matches(VALIDATION_REGEX);
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