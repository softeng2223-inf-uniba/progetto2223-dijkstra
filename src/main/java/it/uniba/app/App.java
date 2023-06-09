package it.uniba.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * << Control >>:
 * Main app class.
 */
public final class App {
    private static final Shell SHELL = Shell.getShell();
    private static Match match = null;
    private static boolean isRunning = true;

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
        Shell.printlnMessage("3.  /mostranavi       mostra le navi da affondare presenti sulla griglia");
        Shell.printlnMessage("4.  /tempo numero     imposta la durata massima di gioco in minuti");
        Shell.printlnMessage("5.  /facile           imposta la difficoltà a facile [default: 50 tentativi]");
        Shell.printlnMessage("6.  /facile numero    imposta un nuovo numero di tentativi per la difficoltà facile");
        Shell.printlnMessage("7.  /medio            imposta la difficoltà a medio [default: 30 tentativi]");
        Shell.printlnMessage("8.  /medio numero     imposta un nuovo numero di tentativi per la difficoltà media");
        Shell.printlnMessage("9.  /difficile        imposta la difficoltà a difficile [default: 10 tentativi]");
        Shell.printlnMessage("10. /difficile numero imposta un nuovo numero di tentativi per la difficoltà difficile");
        Shell.printlnMessage("11. /tentativi numero imposta un nuovo numero di tentativi per tutte le difficolta'");
        Shell.printlnMessage("12. /mostralivello    mostra il livello di difficoltà impostato");
        Shell.printlnMessage("13. /standard         imposta a 10x10 la dimensione della griglia");
        Shell.printlnMessage("14. /large            imposta a 18x18 la dimensione della griglia");
        Shell.printlnMessage("15. /extralarge       imposta a 26x26 la dimensione della griglia");
        Shell.printlnMessage("16. /mostragriglia    mostra la griglia di gioco");
        Shell.printlnMessage("17. /mostratempo      visualizza i minuti trascorsi e i minuti ancora disponibili");
        Shell.printlnMessage("18. /mostratentativi  visualizza il numero di tentativi falliti,"
        + " rimasti e il massimo di tentativi falliti");
        Shell.printlnMessage("19. /svelagriglia     mostra la griglia con le navi posizionate");
        Shell.printlnMessage("20. /abbandona        abbandona la partita in corso");
        Shell.printlnMessage("21. /esci             termina il gioco");
        Shell.printlnMessage("\nPer effettuare un tentativo, digitare le coordinate da colpire\n"
        + "nella forma <lettera>-<numero>, ad esempio A-1, B-5, C-10, D-26, ecc.\n");
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
     * @param difficulty
     */
    private static void setDifficulty(final String[] command, final Difficulty difficulty) {
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
                            case "/tentativi":
                                Difficulty.setEasyMaxFailures(number);
                                Difficulty.setMediumMaxFailures(number);
                                Difficulty.setHardMaxFailures(number);
                                break;
                            default:
                                return;
                        }
                        Shell.printlnMessage("Ok!");
                    } else {
                        Shell.printlnError("Non puoi impostare un "
                        + "numero di tentativi negativo o uguale a zero.");
                    }
                } catch (NumberFormatException e) {
                    Shell.printlnError("Numero non valido.");
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
                        Shell.printlnError("Comando inesistente o non riconosciuto.");
                        return;
                }
                Shell.printlnMessage("Ok!");
            }
        } else {
            Shell.printlnError("La partita e' gia' iniziata!");
        }
    }
    private static void showLevel(final Difficulty difficulty) {
        Shell.printMessage("Difficolta': ");
        Shell.printlnMessage(difficulty.getName());
        Shell.printlnMessage("Max. Tentativi Falliti: "
        + difficulty.getCurrentMaxFailures());
    }
    private static void showShips() {
        if (match == null) {
            Shell.printlnError(
                "Non e' in esecuzione nessuna partita!");
            Shell.printlnError(
                "Digita /gioca per iniziare una nuova partita!");
        } else {
            Shell.printlnMessage(match.getMap().getShipStats());
        }
    }
    private static void play(final MapType mapType, final Difficulty difficulty, final int maxMinutes) {
        if (match == null) {
            match = new Match(mapType, difficulty.getCurrentLevel(), maxMinutes);
            Shell.printlnMessage("Partita avviata!");
            Shell.printlnMessage(match.getMap().getMapGrid());
        } else {
            Shell.printlnError(
                "E' gia' in corso un'altra partita!");
            Shell.printlnError(
                "Digita /abbandona per terminare la partita!");
        }
    }
    private static void revealGrid() {
        if (match != null) {
            Shell.printlnMessage(match.getMap().toString());
        } else {
            Shell.printlnError(
                "La partita non e' ancora iniziata.");
            Shell.printlnError(
                "Digita /gioca per iniziare una nuova partita!");
        }
    }
    private static void showGrid() {
        if (match == null) {
            Shell.printlnError(
                "Non e' in esecuzione nessuna partita!");
            Shell.printlnError(
                "Digita /gioca per iniziare una nuova partita!");
        } else {
            Shell.printlnMessage(match.getMap().getMapGrid());
        }
    }
    private static void exit() {
        String command;
        do {
            Shell.printWarning("Sei sicuro di voler uscire? [ s / n ]: ");
            command = SHELL.getInput().toLowerCase();
            if (command.compareTo("s") == 0) {
                isRunning = false;
            }
        } while (isRunning && command.compareTo("n") != 0);
    }
    private static int quit(final int maxMinutes) {
        String command;
        boolean quit = false;
        if (match == null) {
            Shell.printlnError(
                "Non e' in esecuzione nessuna partita!");
            Shell.printlnError(
                "Digita /gioca per iniziare una nuova partita!");
        } else {
            do {
                Shell.printWarning("Sei sicuro di voler abbandonare? [ s / n ]: ");
                command = SHELL.getInput().toLowerCase();
                if (command.compareTo("s") == 0) {
                    quit = true;
                    Shell.printlnMessage("Ecco qual'era la posizione delle navi: ");
                    Shell.printlnMessage(match.getMap().toString());
                    Shell.printlnMessage("Partita terminata!");
                    match = null;
                    return 0;
                }
            } while (!quit && command.compareTo("n") != 0);
        }

        return maxMinutes;
    }
    private static MapType setGridSize(final MapType currentMapType, final MapType newMapType) {
        if (match == null) {
            Shell.printlnMessage("Ok!");
            return newMapType;
        } else {
            Shell.printlnError("La partita e' già iniziata!");
            return currentMapType;
        }
    }

    /**
     * Checks if the input of the user
     * is a move of the game or not.
     * @param command
     * @return
     */
    private static boolean isAMove(final String command) {
        final String moveRegexp = "^[a-z]-\\d{1,2}$";
        Pattern pattern = Pattern.compile(moveRegexp, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(command);

        return matcher.matches();
    }
    private static int setTimer(final String[] command) {
        int number = 0;

        if (match == null) {
            if (command.length == 2) {
                try {
                    number = Integer.parseInt(command[1]);
                    if (number > 0) {
                        Shell.printlnMessage("Ok!");
                    } else {
                        number = 0;
                        Shell.printlnError("Non puoi impostare un "
                        + "numero di minuti negativo o uguale a zero.");
                    }
                } catch (NumberFormatException e) {
                    Shell.printlnError("Numero non valido.");
                }
            } else {
                Shell.printlnError("Numero non valido.");
            }
        } else {
            Shell.printlnError("La partita e' gia' iniziata!");
        }

        return number;
    }

    private static void resetGame() {
        match = null;
    }

    private static void showTime() {
        if (match != null) {
            Shell.printlnMessage(match.getTimer().getGameTime());
        } else {
            Shell.printlnError("La partita non e' ancora iniziata.");
            Shell.printlnError("Digita /gioca per iniziare una nuova partita!");
        }
    }

    private static void showAttempts() {
        if (match == null) {
            Shell.printlnError("La partita non e' ancora iniziata.");
            Shell.printlnError("Digita /gioca per iniziare una nuova partita!");
        } else {
            Shell.printlnMessage("Tentativi effettuati: " + match.getNumberOfAttempts());
            Shell.printlnMessage("Tentativi falliti: " + match.getNumberOfFailedAttempts());
            Shell.printlnMessage("Numero massimo tentativi falliti: "
            + match.getCurrentDifficulty().getCurrentMaxFailures() + "\n");
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
        Difficulty difficulty = new Difficulty(Difficulty.Level.EASY);
        MapType mapType = MapType.STANDARD;
        String command;
        int maxMinutes = 0;
        do {
            if (match != null) {
                if (match.isGameOver()) {
                    resetGame();
                    maxMinutes = 0;
                }
            }

            Shell.printMessage("> ");
            command = SHELL.getInput();
            command = command.toLowerCase();

            String[] splittedCommand = command.split("\\s+");

            if (splittedCommand.length > 2) {
                Shell.printlnError("Comando inesistente o non riconosciuto.");
                continue;
            }

            switch (splittedCommand[0]) {
                case "/help":
                    printDescription();
                    printHelp();
                break;
                case "/facile":
                case "/medio":
                case "/difficile":
                case "/tentativi":
                    setDifficulty(splittedCommand, difficulty);
                    break;
                case "/mostralivello":
                    showLevel(difficulty);
                    break;
                case "/mostranavi":
                    showShips();
                    break;
                case "/gioca":
                    if (maxMinutes > 0) {
                        play(mapType, difficulty, maxMinutes);
                        match.getTimer().startTimer();
                    } else {
                        Shell.printlnError("Non hai impostato un timer di gioco!\n"
                        + "Digita /tempo numero per impostarlo.");
                    }
                    break;
                case "/svelagriglia":
                    revealGrid();
                    break;
                case "/standard":
                    mapType = setGridSize(mapType, MapType.STANDARD);
                    break;
                case "/large":
                    mapType = setGridSize(mapType, MapType.LARGE);
                    break;
                case "/extralarge":
                    mapType = setGridSize(mapType, MapType.EXTRALARGE);
                    break;
                case "/mostragriglia":
                    showGrid();
                    break;
                case "/tempo":
                    maxMinutes = setTimer(splittedCommand);
                break;
                case "/mostratempo":
                    showTime();
                break;
                case "/esci":
                    exit();
                break;
                case "/abbandona":
                    maxMinutes = quit(maxMinutes);
                break;
                case "/mostratentativi":
                    showAttempts();
                break;
                default:
                    if (isAMove(splittedCommand[0])) {
                        if (match == null) {
                            Shell.printlnError(
                                "Non e' in esecuzione nessuna partita!");
                            Shell.printlnError(
                                "Digita /gioca per iniziare una nuova partita!");
                        } else {
                            Shell.printlnMessage(match.makeMove(splittedCommand[0]));
                        }
                    } else {
                        Shell.printlnError("Comando inesistente o non riconosciuto.");
                    }
                    break;
            }
        } while (isRunning);
    }
}
