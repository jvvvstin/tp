package seedu.address.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.FlagCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.FlagStatus;
import seedu.address.model.person.Person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.EditCommand.createEditedPerson;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIFTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SIXTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

public class PersonListTest {
    private ModelManager model;

    @BeforeEach
    public void setUp() {
        AddressBook addressBook = new AddressBook();
        addressBook.addPerson(BENSON);
        addressBook.addPerson(IDA);
        addressBook.addPerson(DANIEL);
        addressBook.addPerson(FIONA);
        addressBook.addPerson(ALICE);
        addressBook.addPerson(HOON);
        model = new ModelManager(addressBook, new UserPrefs());
    }

    @Test
    public void updatePersonListFilter_validPredicate_personListUpdated() {
        // Replace the third person with a flagged person
        Person person = new PersonBuilder().withFlagStatus(true).build();
        Person unflaggedPerson = model.getPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        model.setPerson(unflaggedPerson, person);

        // Update the person list filter to show only flagged persons
        model.updatePersonListFilter(p -> p.getFlagStatus().isFlagged);

        // Check that the person list only contains the single flagged person
        assertEquals(model.getPersonList().size(), 1);
        assertEquals(model.getPersonList().get(INDEX_FIRST_PERSON.getZeroBased()),
                person);
    }

    @Test
    public void flagCommand_validFlag_retainsOrder() {

        Person firstPerson = model.getPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person secondPerson = model.getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Person thirdPerson = model.getPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        Person fourthPerson = model.getPersonList().get(INDEX_FOURTH_PERSON.getZeroBased());
        Person fifthPerson = model.getPersonList().get(INDEX_FIFTH_PERSON.getZeroBased());
        Person sixthPerson = model.getPersonList().get(INDEX_SIXTH_PERSON.getZeroBased());

        // Flag the first, third and fifth person
        Person firstPersonFlagged = new PersonBuilder(firstPerson).withFlagStatus(true).build();
        Person thirdPersonFlagged = new PersonBuilder(thirdPerson).withFlagStatus(true).build();
        Person fifthPersonFlagged = new PersonBuilder(fifthPerson).withFlagStatus(true).build();
        model.setPerson(firstPerson, firstPersonFlagged);
        model.setPerson(thirdPerson, thirdPersonFlagged);
        model.setPerson(fifthPerson, fifthPersonFlagged);

        // Get the sorted person list
        ObservableList<Person> sortedPersonList = model.getPersonList();

        System.out.println(model.getPersonList());

        // Check that the flagged persons are at the top in the original order
        assertEquals(sortedPersonList.get(INDEX_FIRST_PERSON.getZeroBased()), firstPersonFlagged);
        assertEquals(sortedPersonList.get(INDEX_SECOND_PERSON.getZeroBased()), thirdPersonFlagged);
        assertEquals(sortedPersonList.get(INDEX_THIRD_PERSON.getZeroBased()), fifthPersonFlagged);

        // Check that the unflagged persons are at the bottom in the original order
        assertEquals(sortedPersonList.get(INDEX_FOURTH_PERSON.getZeroBased()), secondPerson);
        assertEquals(sortedPersonList.get(INDEX_FIFTH_PERSON.getZeroBased()), fourthPerson);
        assertEquals(sortedPersonList.get(INDEX_SIXTH_PERSON.getZeroBased()), sixthPerson);
    }

}
