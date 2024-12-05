package com.example.jsonxmlconverter;

import org.junit.Test;
import static org.junit.Assert.*;

public class LoggerTest {

    @Test
    public void testInfo() {
        Logger.info("This is an info message");
        // Verify the info message is logged correctly
    }

    @Test
    public void testDebug() {
        Logger.debug("This is a debug message");
        // Verify the debug message is logged correctly
    }

    @Test
    public void testError() {
        Logger.error("This is an error message");
        // Verify the error message is logged correctly
    }

    @Test
    public void testWarn() {
        Logger.warn("This is a warn message");
        // Verify the warn message is logged correctly
    }
}
