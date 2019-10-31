import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class L3NEW_TG_B9_ImportGraph {

    //region CONSTANTS
    private static final String GRAPH_PATH = "res/L3NEW-TG-B9-g";
    private static final String GRAPH_EXTENSION = ".txt";
    //endregion

    public L3NEW_TG_B9_ImportGraph() {
        importGraphProcedure();
    }

    private void importGraphProcedure() {
        Scanner scanner = new Scanner(System.in);

        //Continue to ask the user a valid number (only number && existing file)
        while (true) {
            try {
                importAGraph(askUserGraphNumber(scanner));
                break;
            } catch (InputMismatchException e) {
                scanner.nextLine(); //To get rid of the last line and so wait a new input
                System.err.println("\b Erreur : Veuillez entrer un chiffre \n");
            } catch (NoSuchFileException e) {
                System.err.println("\b Erreur : Fichier introuvable : " + e.getLocalizedMessage() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private int askUserGraphNumber(Scanner scanner) throws InputMismatchException {
        System.out.print("Entrer le numero du graphe à importer : ");
        return scanner.nextInt();
    }

    private void importAGraph(int graphNumber) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(GRAPH_PATH + graphNumber + GRAPH_EXTENSION));
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }

        //Only print graph temporarily until graph structure
        System.out.println(stringBuilder);
    }
}
