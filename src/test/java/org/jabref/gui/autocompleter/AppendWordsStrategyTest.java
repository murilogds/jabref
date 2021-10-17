package org.jabref.gui.autocompleter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppendWordsStrategyTest {

    private AppendWordsStrategy aws;

    @BeforeEach
    public void setUp() { aws = new AppendWordsStrategy(); }

    @Test
    void testEmptyString() {
        String input = "";
        AutoCompletionInput result = aws.analyze(input);
        assertEquals("", result.getPrefix());
        assertEquals("", result.getUnfinishedPart());
    }

    // Delimiter is the last " " in the input

    // Supposed to return only the input in the unfinished part
    @Test
    void testWithNoDelimiter() {
        String input = "Testando";
        AutoCompletionInput result = aws.analyze(input);
        assertEquals("", result.getPrefix());
        assertEquals(input, result.getUnfinishedPart());
    }

    // Supposed to return the input in the prefix
    @Test
    void testWithDelimiterInEndOfInput() {
        String input = "Testando ";
        AutoCompletionInput result = aws.analyze(input);
        assertEquals(input, result.getPrefix());
        assertEquals("", result.getUnfinishedPart());
    }

    // Supposed to return " " in the prefix and "Testando" in the unfinished part
    @Test
    void testWithDelimiterInBeginOfInput() {
        String input = " Testando";
        AutoCompletionInput result = aws.analyze(input);
        assertEquals(" ", result.getPrefix());
        assertEquals("Testando", result.getUnfinishedPart());
    }

    // Supposed to return "prefix " in the prefix and "rest" in unfinished part
    @Test
    void testWithDelimiterBetweenTwoStrings() {
        String input = "prefix rest";
        AutoCompletionInput result = aws.analyze(input);
        assertEquals("prefix ", result.getPrefix());
        assertEquals("rest", result.getUnfinishedPart());
    }

    // Supposed to return "that is the prefix - " in the prefix and "rest" in unfinished part
    @Test
    void testWithMultipleWhiteSpaces() {
        String input = "that is the prefix - rest";
        AutoCompletionInput result = aws.analyze(input);
        assertEquals("that is the prefix - ", result.getPrefix());
        assertEquals("rest", result.getUnfinishedPart());
    }
}
