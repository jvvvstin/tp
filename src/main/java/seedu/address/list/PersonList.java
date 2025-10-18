package seedu.address.list;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.model.person.Person;

public class PersonList {
    private final FilteredList<Person> filteredPersons;
    private final SortedList<Person> sortedPersons;

    public PersonList(ObservableList<Person> persons) {
        requireNonNull(persons);

        this.filteredPersons = new FilteredList<>(persons);
        this.sortedPersons = new SortedList<>(filteredPersons,
                (p1, p2) -> Boolean.compare(!p1.getIsFlagged(), !p2.getIsFlagged())
        );
    }

    public void updatePersonListFilter(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    public ObservableList<Person> getPersonList() {
        return sortedPersons;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PersonList)) {
            return false;
        }

        PersonList otherPersonList = (PersonList) other;
        return sortedPersons.equals(otherPersonList.sortedPersons);
    }


}
