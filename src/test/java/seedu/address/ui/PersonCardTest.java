package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;

import javafx.scene.Scene;
import javafx.stage.Stage;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

@DisabledOnOs(OS.LINUX)
@ExtendWith(ApplicationExtension.class)
public class PersonCardTest {

    @Test
    public void flagStatus_flagged_showsFlagIcon(FxRobot robot) {
        // Create a person with flag
        Person flaggedPerson = new PersonBuilder().withName("Alice")
                .withFlagStatus(true).build();
        PersonCard card = new PersonCard(flaggedPerson, 1);

        robot.interact(() -> {
            Stage stage = new Stage();
            stage.setScene(new Scene(card.getRoot()));
            stage.show();
        });

        assertTrue(card.getFlag().isVisible());
        assertTrue(card.getCardPane().getStyleClass().contains("flagged"));
    }

    @Test
    public void flagStatus_notFlagged_hidesFlagIcon(FxRobot robot) {
        // Create a person without flag
        Person unflaggedPerson = new PersonBuilder().withName("Bob")
                .withFlagStatus(false).build();
        PersonCard card = new PersonCard(unflaggedPerson, 1);

        robot.interact(() -> {
            Stage stage = new Stage();
            stage.setScene(new Scene(card.getRoot()));
            stage.show();
        });

        // Assert that flag icon is hidden
        assertFalse(card.getFlag().isVisible());
        assertFalse(card.getCardPane().getStyleClass().contains("flagged"));
    }
}

