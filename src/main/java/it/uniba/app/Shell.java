package it.uniba.app;

import java.util.Scanner;

/**
 * .
 */
public final class Shell {
    private Scanner input;
    private static final Shell SHELL = new Shell();

    private Shell() {
        input = new Scanner(System.in, "UTF-8");
    }

    public static Shell getShell() {
        return SHELL;
    }

    /**
     * Returns the shell input typed by the user.
     * @return String - represents the user input.
     */
    public String getInput() {
        String command;
        command = input.nextLine();
        return command;
    }
    /**
     * Prints the message passed via argument to the shell.
     * @param message
     */
    public static void printMessage(final String message) {
        System.out.print(message);
    }
    /**
     * Prints the message passed via argument to the shell with a newline.
     * @param message
     */
    public static void printlnMessage(final String message) {
        System.out.println(message);
    }
    /**
     * Prints the message with color red passed via argument to the shell.
     * @param message
     */
    public static void printError(final String message) {
        System.err.print(ANSICodes.FRED + message + ANSICodes.RESET);
    }
    /**
     * Prints the message with color red passed via argument to the shell with a newline.
     * @param message
     */
    public static void printlnError(final String message) {
        System.err.println(ANSICodes.FRED + message + ANSICodes.RESET);
    }
    /**
     * Prints the message with color yellow passed via argument to the shell.
     * @param message
     */
    public static void printWarning(final String message) {
        System.err.print(ANSICodes.FYELLOW + message + ANSICodes.RESET);
    }
    /**
     * Prints the message with color yellow passed via argument to the shell with a newline.
     * @param message
     */
    public static void printlnWarning(final String message) {
        System.err.println(ANSICodes.FYELLOW + message + ANSICodes.RESET);
    }
}
