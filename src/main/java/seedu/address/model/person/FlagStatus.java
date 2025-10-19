package seedu.address.model.person;

public class FlagStatus {
    public static final Boolean DEFAULT_FLAG_STATUS = false;
    private Boolean isFlagged;

    public FlagStatus(Boolean isFlagged) {
        this.isFlagged = isFlagged;
    }
}
