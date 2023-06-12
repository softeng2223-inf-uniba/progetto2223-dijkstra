package it.uniba.app;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 * Util class that handles the
 * output of class methods.
 */
public abstract class OutputHandler {
    /**
     * Methods that execute the method, passed as a parameter,
     * and fetches its output.
     * @param action
     * @return
     */
    public static String captureOutput(final Runnable action) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String result = "";

        try {
            PrintStream printStream = new PrintStream(outputStream, true, "UTF-8");
            System.setOut(printStream);

            action.run();

            System.out.flush();
            System.setOut(originalOut);

            result = outputStream.toString("UTF-8").trim();
        } catch (UnsupportedEncodingException e) {
            Shell.printlnMessage(e.getMessage());
        }

        return result;
    }
}
