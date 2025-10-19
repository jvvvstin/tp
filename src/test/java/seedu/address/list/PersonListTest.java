package seedu.address.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.EditCommand;
import seedu.address.model.person.Person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;

public class PersonListTest {
    private ObservableList<Person> list;
    private PersonList personList;

    @BeforeEach
    public void setUp() {
        list = getTypicalAddressBook().getPersonList();
        personList = new PersonList(list);

    }

    @Test
    public void updatePersonListFilter_validPredicate_personListUpdated() {
        PersonList other = new PersonList(getTypicalAddressBook().getPersonList());
        assertEquals(personList, other);
    }

    @Test
    public void hello() {
        System.out.println(new EditCommand.EditPersonDescriptor());
    }


}
