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
        Shell.printlnMessage("1.  /help             mostra l'elenco dei comandi disponibili");
        Shell.printlnMessage("2.  /gioca            avvia una nuova partita");
        Shell.printlnMessage("3.  /esci             termina il gioco");
        Shell.printlnMessage("4.  /facile           imposta la difficoltà a facile [default: 50 tentativi]");
        Shell.printlnMessage("5.  /facile numero    imposta un nuovo numero di tentativi per la difficoltà facile");
        Shell.printlnMessage("6.  /medio            imposta la difficoltà a medio [default: 30 tentativi]");
        Shell.printlnMessage("7.  /medio numero     imposta un nuovo numero di tentativi per la difficoltà media");
        Shell.printlnMessage("8.  /difficile        imposta la difficoltà a difficile [default: 10 tentativi]");
        Shell.printlnMessage("9.  /difficile numero imposta un nuovo numero di tentativi per la difficoltà difficile");
        Shell.printlnMessage("10. /mostralivello    mostra il livello di difficoltà impostato");
        Shell.printlnMessage("11. /mostranavi       mostra le navi da affondare presenti sulla griglia");
        Shell.printlnMessage("12. /svelagriglia     mostra la griglia con le navi posizionate");
        Shell.printlnMessage("13. /standard         imposta a 10x10 la dimensione della griglia");
        Shell.printlnMessage("14. /large            imposta a 18x18 la dimensione della griglia");
        Shell.printlnMessage("15. /extralarge       imposta a 26x26 la dimensione della griglia");
        Shell.printlnMessage("16. /mostragriglia    mostra la griglia di gioco");
        Shell.printlnMessage("17. /abbandona        abbandona la partita in corso");
    }
    private static void printDescription() {
        Shell.printlnMessage(
            "La versione a singolo giocatore di Battaglia "
            + "Navale prevede che il giocatore affronti il computer.");
        Shell.printlnMessage(
        "Il computer posiziona navi di "
        + "diversa tipologia su una griglia 10x10, 18x18 o 26x26.");
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
     * Method that sets the game difficulty according to the input of the user.
     * @param command
     * @param match
     * @param difficulty
     */
    private static void setDifficulty(final String[] command, final Match match, final Difficulty difficulty) {
        if (match == null) {
            if (command.length == 2) {
                try {
                    int number = Integer.parseInt(command[1]);
                    if (number > 0) {
                        switch (command[0]) {
                            case "/facile":
                                Difficulty.setEasyMaxFailures(number);
                                break;
                            case "/medio":
                                Difficulty.setMediumMaxFailures(number);
                                break;
                            case "/difficile":
                                Difficulty.setHardMaxFailures(number);
                                break;
                            default:
                                return;
                        }
                        Shell.printlnMessage("Ok!");
                    } else {
                        Shell.printlnMessage("Non puoi impostare un "
                        + "numero di tentativi negativo o uguale a zero.");
                    }
                } catch (NumberFormatException e) {
                    Shell.printlnMessage("Numero non valido.");
                }
            } else {
                switch (command[0]) {
                    case "/facile":
                        difficulty.setCurrentLevel(Difficulty.Level.EASY);
                        break;
                    case "/medio":
                        difficulty.setCurrentLevel(Difficulty.Level.MEDIUM);
                        break;
                    case "/difficile":
                        difficulty.setCurrentLevel(Difficulty.Level.HARD);
                        break;
                    default:
                        return;
                }
                Shell.printlnMessage("Ok!");
            }
        } else {
            Shell.printlnMessage("La partita e' gia' iniziata!");
        }
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
        MapType mapType = MapType.STANDARD;
        Match match = null;

        String command;
        boolean exit = false;
        boolean quit = false;
        do {
            Shell.printMessage("> ");
            command = SHELL.getInput();
            command = command.toLowerCase();

            String[] splittedCommand = command.split("\\s+");

            if (splittedCommand.length > 2) {
                Shell.printlnMessage(ANSICodes.FRED + "Comando inesistente o non riconosciuto." + ANSICodes.RESET);
                continue;
            }

            switch (splittedCommand[0]) {
                case "/help":
                    printDescription();
                    printHelp();
                break;
                case "/facile":
                    setDifficulty(splittedCommand, match, difficulty);
                    break;
                case "/medio":
                    setDifficulty(splittedCommand, match, difficulty);
                    break;
                case "/difficile":
                    setDifficulty(splittedCommand, match, difficulty);
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
                        match = new Match(mapType, difficulty.getCurrentLevel());
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
                case "/standard":
                    if (match == null) {
                        Shell.printlnMessage("Ok!");
                        mapType = MapType.STANDARD;
                    } else {
                        Shell.printlnMessage("La partita e' già iniziata!");
                    }
                    break;
                case "/large":
                    if (match == null) {
                        Shell.printlnMessage("Ok!");
                        mapType = MapType.LARGE;
                    } else {
                        Shell.printlnMessage("La partita e' già iniziata!");
                    }
                    break;
                case "/extralarge":
                    if (match == null) {
                        Shell.printlnMessage("Ok!");
                        mapType = MapType.EXTRALARGE;
                    } else {
                        Shell.printlnMessage("La partita e' già iniziata!");
                    }
                    break;
                case "/mostragriglia":
                    if (match == null) {
                        Shell.printlnMessage(
                            "Non e' in esecuzione nessuna partita!");
                        Shell.printlnMessage(
                            "Digita /gioca per iniziare una nuova partita!");
                    } else {
                        Shell.printlnMessage(match.getMap().getMapGrid());
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
                case "/abbandona":
                            if (match == null) {
                            Shell.printlnMessage(
                                "Non e' in esecuzione nessuna partita!");
                            Shell.printlnMessage(
                                "Digita /gioca per iniziare una nuova partita!");
                            } else {
                                do {
                                    Shell.printMessage(
                                        ANSICodes.FYELLOW
                                        + "Sei sicuro di voler abbandonare? [ s / n ]: "
                                        + ANSICodes.RESET);
                                    command = SHELL.getInput().toLowerCase();
                                    if (command.compareTo("s") == 0) {
                                        quit = true;
                                        Shell.printlnMessage("Ecco qual'era la posizione delle navi: ");
                                        Shell.printlnMessage(match.getMap().toString());
                                        Shell.printlnMessage("Partita terminata!");
                                        match = null;
                                    }
                                } while (!quit && command.compareTo("n") != 0); 
                            }
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
