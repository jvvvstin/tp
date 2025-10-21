package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FlagStatusTest {
    private FlagStatus flaggedStatus = new FlagStatus(true);
    private FlagStatus unflaggedStatus = new FlagStatus(false);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FlagStatus(null));
    }

    @Test
    public void toString_flaggedStatus_returnsTrueString() {
        assert(flaggedStatus.toString().equals("Flagged"));
        assert(unflaggedStatus.toString().equals("Not Flagged"));
    }

    @Test
    public void hashCode_sameFlagStatus_equalHashCode() {
        assertEquals(flaggedStatus.hashCode(), new FlagStatus(true).hashCode());
    }

    @Test
    public void hashCode_differentFlagStatus_unequalHashCode() {
        assertFalse(flaggedStatus.hashCode() == unflaggedStatus.hashCode());
    }

    @Test
    public void equals() {
        // same values -> returns true
        assert(flaggedStatus.equals(new FlagStatus(true)));

        // same object -> returns true
        assert(flaggedStatus.equals(flaggedStatus));

        // null -> returns false
        assert(!flaggedStatus.equals(null));

        // different types -> returns false
        assert(!flaggedStatus.equals(5.0f));

        // different values -> returns false
        assert(!flaggedStatus.equals(unflaggedStatus));
    }
}
