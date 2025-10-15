package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n=");
    public static final Prefix PREFIX_MAIN_PHONE = new Prefix("mn=");
    public static final Prefix PREFIX_OTHER_PHONE = new Prefix("on=");
    public static final Prefix PREFIX_EMAIL = new Prefix("e=");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a=");
    public static final Prefix PREFIX_TAG = new Prefix("t=");
    public static final Prefix PREFIX_MEETING = new Prefix("m=");
    public static final Prefix PREFIX_VENUE = new Prefix("v=");
    public static final Prefix PREFIX_WHEN = new Prefix("w=");
    public static final Prefix PREFIX_PERSON_INDEX = new Prefix("p=");
    public static final Prefix PREFIX_MEETING_INDEX = new Prefix("i=");
}
