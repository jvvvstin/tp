package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

public class FlagStatus implements Comparable<FlagStatus> {
    public static final FlagStatus DEFAULT_FLAG_STATUS = new FlagStatus(false);
    public final Boolean isFlagged;

    public FlagStatus(Boolean isFlagged) {
        requireNonNull(isFlagged);
        this.isFlagged = isFlagged;
    }

    @Override
    public int compareTo(FlagStatus other) {
        return Boolean.compare(!this.isFlagged, !other.isFlagged);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FlagStatus)) {
            return false;
        }
        FlagStatus otherFlagStatus = (FlagStatus) other;
        return this.isFlagged.equals(otherFlagStatus.isFlagged);
    }

    @Override
    public String toString() {
        return isFlagged ? "Flagged" : "Not Flagged";
    }

    @Override
    public int hashCode() {
        return isFlagged.hashCode();
    }
}
