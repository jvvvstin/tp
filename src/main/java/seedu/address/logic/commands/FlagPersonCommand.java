package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Person;

public class FlagPersonCommand extends Command {
    public static final String COMMAND_WORD = "flag";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        editPersonDescriptor.setIsFlagged(true);
        return null;

    }
}
