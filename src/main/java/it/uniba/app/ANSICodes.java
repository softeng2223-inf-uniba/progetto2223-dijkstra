package it.uniba.app;
/**
 * << Boundary >>:
 * ANSICodes class in order to format text.
 */
public final class ANSICodes {
    public static final String FBLACK = "\033[30m";
    public static final String BBLACK = "\033[40m";
    public static final String FRED = "\033[31m";
    public static final String BRED = "\033[41m";
    public static final String FGREEN = "\033[32m";
    public static final String BGREEN = "\033[42m";
    public static final String FYELLOW = "\033[33m";
    public static final String BYELLOW = "\033[43m";
    public static final String FBLUE = "\033[34m";
    public static final String BBLUE = "\033[44m";
    public static final String FMAG = "\033[35m";
    public static final String BMAG = "\033[45m";
    public static final String FCYAN = "\033[36m";
    public static final String BCYAN = "\033[46m";
    public static final String FLGRAY = "\033[37m";
    public static final String BLGRAY = "\033[47m";
    public static final String FDGRAY = "\033[90m";
    public static final String BDGRAY = "\033[100m";
    public static final String FBRED = "\033[91m";
    public static final String BBRED = "\033[101m";
    public static final String FBGREEN = "\033[92m";
    public static final String BBGREEN = "\033[102m";
    public static final String FBYELLOW = "\033[93m";
    public static final String BBYELLOW = "\033[103m";
    public static final String FBBLUE = "\033[94m";
    public static final String BBBLUE = "\033[104m";
    public static final String FBMAG = "\033[95m";
    public static final String BBMAG = "\033[105m";
    public static final String FBCYAN = "\033[96m";
    public static final String BBCYAN = "\033[106m";
    public static final String FBWHITE = "\033[97m";
    public static final String BBWHITE = "\033[107m";
    public static final String RESET  =  "\033[0m";
    public static final String BOLD = "\033[1m";
    public static final String ITALIC = "\033[3m";
    public static final String UNDERLINE = "\033[4m";
    public static final String NOUNDERLINE = "\033[24m";
    public static final String SLOWBLINK = "\033[5m";
    public static final String BLINK = "\033[6m";
    public static final String HIDE = "\033[8m";
    public static final String REVERSETEXT = "\033[7m";
    public static final String NOTREVERSETEXT = "\033[27m";
    private ANSICodes() {
    }
    /**
     * Method to clear the console.
     */
    public static void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls")
            .inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to set the foreground color.
     * @param r
     * @param g
     * @param b
     * @return
     */
    public static String setFColor(final int r, final int g, final int b) {
        return "\033[38;2;" + r + ";" + g + ";" + b + "m";
    }
    /**
     * Method to set di background color.
     * @param r
     * @param g
     * @param b
     */
    public static String setBColor(final int r, final int g, final int b) {
        return "\033[48;2;" + r + ";" + g + ";" + b + "m";
    }


}
