package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class ParserUtilTest2 {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseParametersAndLabels_emptyString_returnEmptyList() {
        assertEquals(new ArrayList<>(), ParserUtil.parseParametersAndLabels(""));
    }

    @Test
    public void parseParametersAndLabels_validInput_returnCorrectList() {
        String input = "TEST PARAMETER 1";
        List<String> correctList = new ArrayList<>(List.of("TEST PARAMETER 1"));
        assertEquals(correctList, ParserUtil.parseParametersAndLabels(input));
    }

    @Test
    public void parseParametersAndLabels_validInput2_returnCorrectList() {
        String input = "TEST PARAMETER 1 (L a b e l )";
        List<String> correctList = new ArrayList<>(List.of("TEST PARAMETER 1", "(L a b e l )"));
        assertEquals(correctList, ParserUtil.parseParametersAndLabels(input));
    }

    @Test
    public void parseParametersAndLabels_validInput3_returnCorrectList() {
        String input = "TEST PARAMETER 1 (L a b e l 1) TEST PARAMETER 2 (L a b e l 2)";
        List<String> correctList = new ArrayList<>(List.of("TEST PARAMETER 1", "(L a b e l 1)",
                "TEST PARAMETER 2", "(L a b e l 2)"));
        assertEquals(correctList, ParserUtil.parseParametersAndLabels(input));
    }

    @Test
    public void parseParametersAndLabels_validInput4_returnCorrectList() {
        String input = "TEST PARAMETER 1 (L a b e l 1";
        List<String> correctList = new ArrayList<>(List.of("TEST PARAMETER 1", "(L a b e l 1"));
        assertEquals(correctList, ParserUtil.parseParametersAndLabels(input));
    }

    @Test
    public void parseParametersAndLabels_extraSpaces_returnEmptyList() {
        String input = "TEST PARAMETER 1  (L a b e l 1) TEST PARAMETER 2 (L a b e l 2)";
        assertEquals(new ArrayList<>(), ParserUtil.parseParametersAndLabels(input));
    }

    @Test
    public void parseParametersAndLabels_extraSpaces2_returnEmptyList() {
        String input = "TEST PARAMETER 1 (L a b e l 1)  TEST PARAMETER 2 (L a b e l 2)";
        assertEquals(new ArrayList<>(), ParserUtil.parseParametersAndLabels(input));
    }

    @Test
    public void parseParametersAndLabels_missingSpaces_returnEmptyList() {
        String input = "TEST PARAMETER 1(L a b e l 1) TEST PARAMETER 2 (L a b e l 2)";
        assertEquals(new ArrayList<>(), ParserUtil.parseParametersAndLabels(input));
    }

    @Test
    public void parseParametersAndLabels_missingSpaces2_returnEmptyList() {
        String input = "T(L a b e l 1) TEST PARAMETER 2 (L a b e l 2)";
        assertEquals(new ArrayList<>(), ParserUtil.parseParametersAndLabels(input));
    }

    @Test
    public void parseParametersAndLabels_missingSpaces3_returnEmptyList() {
        String input = "TEST PARAMETER 1(L a b e l 1) TEST PARAMETER 2 (L a b e l 2)a";
        assertEquals(new ArrayList<>(), ParserUtil.parseParametersAndLabels(input));
    }

    @Test
    public void parseParametersAndLabels_missingSpaces4_returnEmptyList() {
        String input = "TEST PARAMETER 1(L a b e l 1)a TEST PARAMETER 2 (L a b e l 2)";
        assertEquals(new ArrayList<>(), ParserUtil.parseParametersAndLabels(input));
    }

    @Test
    public void parseParametersAndLabels_incorrectFormat_returnEmptyList() {
        String input = "TEST PARAMETER 1 (L a b e l 1) TEST PARAMETER 2";
        assertEquals(new ArrayList<>(), ParserUtil.parseParametersAndLabels(input));
    }

    @Test
    public void parseParametersAndLabels_incorrectFormat2_returnEmptyList() {
        String input = "TEST PARAMETER 1 (L a b e l 1) TEST PARAMETER 2";
        assertEquals(new ArrayList<>(), ParserUtil.parseParametersAndLabels(input));
    }

    @Test
    public void parseParametersAndLabels_incorrectFormat3_returnEmptyList() {
        String input = "TEST PARAMETER 1 (L a b e l 1) TEST PARAMETER 2 (L a b e l 2)a";
        assertEquals(new ArrayList<>(), ParserUtil.parseParametersAndLabels(input));
    }
}
