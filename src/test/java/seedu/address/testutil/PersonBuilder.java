package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.FlagStatus;
import seedu.address.model.person.Name;
import seedu.address.model.person.OtherPhones;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_OTHER_PHONE = "92317869";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final Boolean DEFAULT_FLAG = false;

    private Name name;
    private Phone phone;
    private OtherPhones otherPhones;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private List<Meeting> meetings;
    private FlagStatus flagStatus;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        otherPhones = new OtherPhones(DEFAULT_OTHER_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        meetings = new ArrayList<>();
        flagStatus = new FlagStatus(DEFAULT_FLAG);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        otherPhones = personToCopy.getOtherPhones();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        meetings = new ArrayList<>(personToCopy.getMeetings());
        flagStatus = personToCopy.getFlagStatus();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code OtherPhones} of the {@code Person} that we are building.
     */
    public PersonBuilder withOtherPhones(String otherPhones) {
        this.otherPhones = new OtherPhones(otherPhones);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Parses the {@code meetings} into a {@code List<Meeting>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withMeetings(Meeting ... meetings) {
        this.meetings = List.of(meetings);
        return this;
    }

    /**
     * Sets the {@code isFlagged} of the {@code Person} that we are building.
     */
    public PersonBuilder withFlagStatus(Boolean isFlagged) {
        this.flagStatus = new FlagStatus(isFlagged);
        return this;
    }

    public Person build() {
        return new Person(name, phone, otherPhones, email, address, tags, meetings, flagStatus);
    }

}
