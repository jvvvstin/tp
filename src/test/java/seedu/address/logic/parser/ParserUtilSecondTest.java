package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Contains unit tests for methods in ParserUtil class
 */
public class ParserUtilSecondTest {
    @Test
    public void parseParametersAndLabels_emptyString_throwsParseException() {
        assertThrows(ParseException.class, () ->
                ParserUtil.parseParametersAndLabels("Parameter", "", false));
    }

    @Test
    public void parseParametersAndLabels_validInput_returnCorrectList() {
        String input = "TEST PARAMETER 1";
        List<String> correctList = new ArrayList<>(List.of("TEST PARAMETER 1"));

        try {
            assertEquals(correctList, ParserUtil.parseParametersAndLabels("Parameter", input, false));
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    public void parseParametersAndLabels_validInput2_returnCorrectList() {
        String input = "TEST PARAMETER 1 (L a b e l )";
        List<String> correctList = new ArrayList<>(List.of("TEST PARAMETER 1", "(L a b e l )"));

        try {
            assertEquals(correctList, ParserUtil.parseParametersAndLabels("Parameter", input, false));
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    public void parseParametersAndLabels_validInput3_returnCorrectList() {
        String input = "TEST PARAMETER 1 (L a b e l 1) TEST PARAMETER 2 (L a b e l 2)";
        List<String> correctList = new ArrayList<>(List.of("TEST PARAMETER 1", "(L a b e l 1)",
                "TEST PARAMETER 2", "(L a b e l 2)"));

        try {
            assertEquals(correctList, ParserUtil.parseParametersAndLabels("Parameter", input, false));
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    public void parseParametersAndLabels_validInput4_returnCorrectList() {
        String input = "TEST PARAMETER 1 (L a b e l 1";
        List<String> correctList = new ArrayList<>(List.of("TEST PARAMETER 1", "(L a b e l 1"));

        try {
            assertEquals(correctList, ParserUtil.parseParametersAndLabels("Parameter", input, false));
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    public void parseParametersAndLabelsCompulsoryLabel_validInput_returnCorrectList() {
        String input = "TEST PARAMETER 1 (L a b e l 1)";
        List<String> correctList = new ArrayList<>(List.of("TEST PARAMETER 1", "(L a b e l 1)"));

        try {
            assertEquals(correctList, ParserUtil.parseParametersAndLabels("Parameter", input, true));
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    public void parseParametersAndLabels_extraSpaces_throwsParseException() {
        String input = "TEST PARAMETER 1  (L a b e l 1) TEST PARAMETER 2 (L a b e l 2)";
        assertThrows(ParseException.class, () ->
                ParserUtil.parseParametersAndLabels("Parameter", input, false));
    }

    @Test
    public void parseParametersAndLabels_extraSpaces2_throwsParseException() {
        String input = "TEST PARAMETER 1 (L a b e l 1)  TEST PARAMETER 2 (L a b e l 2)";
        assertThrows(ParseException.class, () ->
                ParserUtil.parseParametersAndLabels("Parameter", input, false));
    }

    @Test
    public void parseParametersAndLabels_extraSpaces3_throwsParseException() {
        String input = "TEST PARAMETER 1 (L a b e l 1) TEST PARAMETER 2 (L a b e l 2)  TEST PARAMETER 3";
        assertThrows(ParseException.class, () ->
                ParserUtil.parseParametersAndLabels("Parameter", input, false));
    }

    @Test
    public void parseParametersAndLabels_missingSpaces_throwsParseException() {
        String input = "TEST PARAMETER 1(L a b e l 1) TEST PARAMETER 2 (L a b e l 2)";
        assertThrows(ParseException.class, () ->
                ParserUtil.parseParametersAndLabels("Parameter", input, false));
    }

    @Test
    public void parseParametersAndLabels_missingSpaces2_throwsParseException() {
        String input = "T(L a b e l 1) TEST PARAMETER 2 (L a b e l 2)";
        assertThrows(ParseException.class, () ->
                ParserUtil.parseParametersAndLabels("Parameter", input, false));
    }

    @Test
    public void parseParametersAndLabels_missingSpaces3_throwsParseException() {
        String input = "TEST PARAMETER 1(L a b e l 1) TEST PARAMETER 2 (L a b e l 2)a";
        assertThrows(ParseException.class, () ->
                ParserUtil.parseParametersAndLabels("Parameter", input, false));
    }

    @Test
    public void parseParametersAndLabels_missingSpaces4_throwsParseException() {
        String input = "TEST PARAMETER 1(L a b e l 1)a TEST PARAMETER 2 (L a b e l 2)";
        assertThrows(ParseException.class, () ->
                ParserUtil.parseParametersAndLabels("Parameter", input, false));
    }

    @Test
    public void parseParametersAndLabels_missingSpaces5_throwsParseException() {
        String input = "TEST PARAMETER 1 (L a b e l 1) T(L a b e l 2)";
        assertThrows(ParseException.class, () ->
                ParserUtil.parseParametersAndLabels("Parameter", input, false));
    }

    @Test
    public void parseParametersAndLabels_missingSpaces6_throwsParseException() {
        String input = "TEST PARAMETER 1 (L a b e l 1) TEST PARAMETER 2 (L a b e l 2)a";
        assertThrows(ParseException.class, () ->
                ParserUtil.parseParametersAndLabels("Parameter", input, false));
    }

    @Test
    public void parseParametersAndLabels_incorrectFormat_throwsParseException() {
        String input = "TEST PARAMETER 1 (L a b e l 1) TEST PARAMETER 2";
        assertThrows(ParseException.class, () ->
                ParserUtil.parseParametersAndLabels("Parameter", input, false));
    }

    @Test
    public void parseParametersAndLabels_incorrectFormat2_throwsParseException() {
        String input = "TEST PARAMETER 1 (L a b e l 1) TEST PARAMETER 2";
        assertThrows(ParseException.class, () ->
                ParserUtil.parseParametersAndLabels("Parameter", input, false));
    }

    @Test
    public void parseParametersAndLabels_incorrectFormat3_throwsParseException() {
        String input = "TEST PARAMETER 1 (L a b e l 1)TEST PARAMETER 2";
        assertThrows(ParseException.class, () ->
                ParserUtil.parseParametersAndLabels("Parameter", input, false));
    }

    @Test
    public void parseParametersAndLabelsCompulsoryLabel_incorrectFormat_throwsParseException() {
        String input = "TEST PARAMETER 1";
        assertThrows(ParseException.class, () ->
                ParserUtil.parseParametersAndLabels("Parameter", input, true));
    }
}
