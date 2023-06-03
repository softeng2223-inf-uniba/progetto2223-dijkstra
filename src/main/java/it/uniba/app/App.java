package it.uniba.app;

/**
 * Main app class.
 */
public final class App {
    private static final Shell SHELL = Shell.getShell();
    private App() { }
    /**
     * Prints the banner.
     */
    private static void printBanner() {
        Shell.printlnMessage(" ________  ________  _________  _________  ___"
        + "       _______   ________  ___  ___  ___  ________   ");
        Shell.printlnMessage("|\\   __  \\|\\   __  \\|\\___   ___\\\\___   ___\\\\"
        + "  \\     |\\  ___ \\ |\\   ____\\|\\  \\|\\  \\|\\  \\|\\   __  \\  ");
        Shell.printlnMessage("\\ \\  \\|\\ /\\ \\  \\|\\  \\|___ \\  \\_\\|_"
        + "__ \\  \\_\\ \\  \\    \\ \\   __/|\\ \\  \\___|\\ \\  \\\\\\  \\ \\  \\ \\  \\|\\  \\ ");
        Shell.printlnMessage(" \\ \\   __  \\ \\   __  \\   \\ \\  \\     \\"
        + " \\  \\ \\ \\  \\    \\ \\  \\_|/_\\ \\_____  \\ \\   __  \\ \\  \\ \\   ____\\ ");
        Shell.printlnMessage("  \\ \\  \\|\\  \\ \\  \\ \\  \\   \\ \\  \\  "
        + "   \\ \\  \\ \\ \\  \\____\\ \\  \\_|\\ \\|____|\\  \\ \\  \\ \\  \\ \\  \\ \\  \\___|");
        Shell.printlnMessage("   \\ \\_______\\ \\__\\ \\__\\   \\ \\__\\   "
        + "  \\ \\__\\ \\ \\_______\\ \\_______\\____\\_\\  \\ \\__\\ \\__\\ \\__\\ \\__\\   ");
        Shell.printlnMessage("    \\|_______|\\|__|\\|__|    \\|__|      \\"
        + "|__|  \\|_______|\\|_______|\\_________\\|__|\\|__|\\|__|\\|__|   ");
        Shell.printlnMessage("                                              "
        + "                   \\|_________|                      ");
        Shell.printlnMessage("");
    }
    private static void printHelp() {
        Shell.printlnMessage("I comandi utilizzabili sono:");
        Shell.printlnMessage("1. /help            mostra l'elenco dei comandi disponibili");
        Shell.printlnMessage("2. /gioca           avvia una nuova partita");
        Shell.printlnMessage("3. /esci            termina il gioco");
        Shell.printlnMessage("4. /facile          imposta la difficoltà a facile");
        Shell.printlnMessage("5. /medio           imposta la difficoltà a medio");
        Shell.printlnMessage("6. /difficile       imposta la difficoltà a difficile");
        Shell.printlnMessage("7. /mostralivello   mostra il livello di difficoltà impostato");
        Shell.printlnMessage("8. /mostranavi      mostra le navi da affondare presenti sulla griglia");
        Shell.printlnMessage("9. /svelagriglia    mostra la griglia con le navi posizionate");
    }
    private static void printDescription() {
        Shell.printlnMessage(
            "La versione a singolo giocatore di Battaglia "
            + "Navale prevede che il giocatore affronti il computer.");
        Shell.printlnMessage(
        "Il computer posiziona navi di "
        + "diversa tipologia su una griglia 10x10.");
        Shell.printlnMessage(
        "Il giocatore deve cercare di colpire e affondare tutte le "
        + "navi del computer usando il minor numero di colpi possibile.");
        Shell.printlnMessage(
        "Il gioco e' strutturato in livelli "
        + "progressivamente sempre piu' difficili, "
        + "in cui diminuisce il numero massimo "
        + "di tentativi falliti per l'utente.");
        Shell.printlnMessage("Buona fortuna nell'affrontare il computer!\n");
    }
    /**
     * Main game loop.
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
                        Shell.printlnMessage("\nPer consultare la lista dei comandi disponibili, digitare /help!");
                    }
        String difficultyName;
        Difficulty difficulty = new Difficulty(Difficulty.Level.EASY);
        // MapType mapType = MapType.STANDARD;
        Match match = null;

        String command;
        boolean exit = false;
        do {
            Shell.printMessage("> ");
            command = SHELL.getInput();
            command = command.toLowerCase();
            switch (command) {
                case "/help":
                    printDescription();
                    printHelp();
                break;
                case "/facile":
                    if (match == null) {
                        difficulty.setCurrentLevel(Difficulty.Level.EASY);
                        Shell.printlnMessage("Ok!");
                    } else {
                        Shell.printlnMessage("La partita e' gia' iniziata!");
                    }
                    break;
                case "/medio":
                    if (match == null) {
                        difficulty.setCurrentLevel(Difficulty.Level.MEDIUM);
                        Shell.printlnMessage("Ok!");
                    } else {
                        Shell.printlnMessage("La partita e' gia' iniziata!");
                    }
                    break;
                case "/difficile":
                    if (match == null) {
                        difficulty.setCurrentLevel(Difficulty.Level.HARD);
                        Shell.printlnMessage("Ok!");
                    } else {
                        Shell.printlnMessage("La partita e' gia' iniziata!");
                    }
                    break;
                case "/mostralivello":
                    difficultyName = difficulty.getName();
                    Shell.printMessage("Difficolta': ");
                    Shell.printlnMessage(difficultyName);
                    Shell.printlnMessage("Max. Tentativi Falliti: "
                    + difficulty.getCurrentMaxFailures());
                    break;
                case "/mostranavi":
                    if (match == null) {
                        Shell.printlnMessage(
                            "Non e' in esecuzione nessuna partita!");
                        Shell.printlnMessage(
                            "Digita /gioca per iniziare una nuova partita!");
                    } else {
                        Shell.printlnMessage(match.getMap().getShipStats());
                    }
                    break;
                case "/gioca":
                    if (match == null) {
                        match = new Match(MapType.STANDARD, difficulty.getCurrentLevel());
                        Shell.printlnMessage("Partita avviata!");
                        Shell.printlnMessage(match.getMap().getMapGrid());
                    } else {
                        Shell.printlnMessage(
                            "E' gia' in corso un'altra partita!");
                        Shell.printlnMessage(
                            "Digita /esci per terminare la partita!");
                    }
                    break;
                case "/svelagriglia":
                    if (match != null) {
                        Shell.printlnMessage(match.getMap().toString());
                    } else {
                        Shell.printlnMessage(
                            "La partita non e' ancora iniziata.");
                        Shell.printlnMessage(
                            "Digita /gioca per iniziare una nuova partita!");
                    }
                    break;
                case "/esci":
                    do {
                        Shell.printMessage(
                        ANSICodes.FYELLOW
                        + "Sei sicuro di voler uscire? [ s / n ]: "
                        + ANSICodes.RESET);
                        command = SHELL.getInput().toLowerCase();
                        if (command.compareTo("s") == 0) {
                            exit = true;
                        }
                    } while (!exit && command.compareTo("n") != 0);
                break;
                default:
                    Shell.printlnMessage(
                        ANSICodes.FRED
                        + "Comando inesistente o non riconosciuto."
                        + ANSICodes.RESET);
                break;
            }
        } while (!exit);
    }
}
