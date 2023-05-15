package it.uniba.app;

import java.util.Scanner;

/**
 * Main app class.
 */
public final class App {
    private App() { }
    /**
     * Prints the banner.
     */
    public static void printBanner() {
        System.out.println(" ________  ________  _________  _________  ___"
        + "       _______   ________  ___  ___  ___  ________   ");
        System.out.println("|\\   __  \\|\\   __  \\|\\___   ___\\\\___   ___\\\\"
        + "  \\     |\\  ___ \\ |\\   ____\\|\\  \\|\\  \\|\\  \\|\\   __  \\  ");
        System.out.println("\\ \\  \\|\\ /\\ \\  \\|\\  \\|___ \\  \\_\\|_"
        + "__ \\  \\_\\ \\  \\    \\ \\   __/|\\ \\  \\___|\\ \\  \\\\\\  \\ \\  \\ \\  \\|\\  \\ ");
        System.out.println(" \\ \\   __  \\ \\   __  \\   \\ \\  \\     \\"
        + " \\  \\ \\ \\  \\    \\ \\  \\_|/_\\ \\_____  \\ \\   __  \\ \\  \\ \\   ____\\ ");
        System.out.println("  \\ \\  \\|\\  \\ \\  \\ \\  \\   \\ \\  \\  "
        + "   \\ \\  \\ \\ \\  \\____\\ \\  \\_|\\ \\|____|\\  \\ \\  \\ \\  \\ \\  \\ \\  \\___|");
        System.out.println("   \\ \\_______\\ \\__\\ \\__\\   \\ \\__\\   "
        + "  \\ \\__\\ \\ \\_______\\ \\_______\\____\\_\\  \\ \\__\\ \\__\\ \\__\\ \\__\\   ");
        System.out.println("    \\|_______|\\|__|\\|__|    \\|__|      \\"
        + "|__|  \\|_______|\\|_______|\\_________\\|__|\\|__|\\|__|\\|__|   ");
        System.out.println("                                              "
        + "                   \\|_________|                      ");
        System.out.println("");
    }
    private static void printHelp() {
        System.out.println("Buona fortuna nell'affrontare il computer!\n");
        System.out.println("I comandi utilizzabili sono:");
        System.out.println("1. /help");
        System.out.println("2. /gioca");
        System.out.println("3. /esci");
        System.out.println("4. /facile");
        System.out.println("5. /medio");
        System.out.println("6. /difficile");
        System.out.println("7. /mostralivello");
        System.out.println("8. /mostranavi");
        System.out.println("9. /svelagriglia");
    }
    private static void printDescription() {
        System.out.println(
            "La versione a singolo giocatore di Battaglia"
            + "Navale prevede che il giocatore affronti il computer.");
        System.out.println(
        "Il computer posiziona navi di "
        + "diversa tipologia su una griglia 10x10.");
        System.out.println(
        "Il giocatore deve cercare di colpire e affondare tutte le "
        + "navi del computer usando il minor numero di colpi possibile.");
        System.out.println(
        "Il gioco e' strutturato in livelli "
        + "progressivamente sempre piu' difficili, "
        + "in cui diminuisce il numero massimo "
        + "di tentativi falliti per l'utente.");
    }
    /**
     * Entry point.
     * @param args
     */
    public static void main(final String[] args) {
        printBanner();
        printDescription();
        if (args.length != 0
                && (args[0].compareTo("--help") == 0
                    || args[0].compareTo("-h") == 0)) {
                        printHelp();
                    } else {
                        System.out.println("\nPer consultare la lista dei comandi disponibili, digitare /help!");
                    }
        String command;
        Scanner input = new Scanner(System.in);
        Difficulty difficulty = null;
        Map map = null;
        boolean exit = false;
        do {
            System.out.print("> ");
            command = input.nextLine();
            command = command.toLowerCase();
            switch (command) {
                case "/help":
                    printDescription();
                    printHelp();
                break;
                case "/facile":
                    if (map == null) {
                        difficulty = Difficulty.EASY;
                        System.out.println("Ok!");
                    } else {
                        System.out.println("La partita e' gia' iniziata!");
                    }
                    break;
                case "/medio":
                    if (map == null) {
                        difficulty = Difficulty.MEDIUM;
                        System.out.println("Ok!");
                    } else {
                        System.out.println("La partita e' gia' iniziata!");
                    }
                    break;
                case "/difficile":
                    if (map == null) {
                        difficulty = Difficulty.HARD;
                        System.out.println("Ok!");
                    } else {
                        System.out.println("La partita e' gia' iniziata!");
                    }
                    break;
                case "/esci":
                    do {
                        System.out.print(
                        ANSICodes.FYELLOW
                        + "Sei sicuro di voler uscire? [ s / n ]: "
                        + ANSICodes.RESET);
                        command = input.nextLine().toLowerCase();
                        if (command.compareTo("s") == 0) {
                            exit = true;
                        }
                    } while (!exit && command.compareTo("n") != 0);
                break;
                default:
                    System.out.println(
                        ANSICodes.FRED
                        + "Comando inesistente o non riconosciuto."
                        + ANSICodes.RESET);
                break;
            }
        } while (!exit);
        input.close();
    }
}
