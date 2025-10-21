package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.meeting.Meeting;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class MeetingNameContainsKeywordsPredicate implements Predicate<Person> {
    private static final Logger logger = LogsCenter.getLogger(MeetingNameContainsKeywordsPredicate.class);
    private final List<String> keywords;

    public MeetingNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        List<Meeting> meetings = person.getMeetings();

        String logMessage = String.format("MeetingNameContainsKeywordsPredicate check using: "
                + "Keywords=%s and Meetings=%s.", keywords, meetings);
        logger.info(logMessage);


        return keywords.stream()
                .anyMatch(keyword -> meetings.stream().anyMatch(meeting
                        -> StringUtil.containsWordIgnoreCase(meeting.getMeetingName().meetingName, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MeetingNameContainsKeywordsPredicate)) {
            return false;
        }

        MeetingNameContainsKeywordsPredicate otherMeetingNameContainsKeywordsPredicate =
                (MeetingNameContainsKeywordsPredicate) other;
        return keywords.equals(otherMeetingNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
