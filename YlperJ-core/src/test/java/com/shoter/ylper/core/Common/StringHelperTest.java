package com.shoter.ylper.core.Common;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringHelperTest {
    @Test
    public void isStringTrimmed_trimmedString_shouldReturnTrue()
    {
        String string = "normal trimmed string";
        assertTrue(StringHelper.isStringTrimmed(string));
    }

    @Test
    public void isStringTrimmed_nonTrimmedString_shouldReturnFalse()
    {
        String string = " this is not trimmed string    ";
        assertFalse(StringHelper.isStringTrimmed(string));
    }
}
