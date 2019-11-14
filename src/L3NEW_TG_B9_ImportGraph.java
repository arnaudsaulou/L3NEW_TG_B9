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

    //region Constructor

    public L3NEW_TG_B9_ImportGraph() {
    }

    //endregion

    //region Utils

    public static L3NEW_TG_B9_Graph importGraphProcedure() {
        Scanner scanner = new Scanner(System.in);

        //Continue to ask the user a valid number (only number && existing file)
        while (true) {
            try {
                return importAGraph(askUserGraphNumber(scanner));
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

    private static int askUserGraphNumber(Scanner scanner) throws InputMismatchException {
        System.out.print("Entrer le numero du graphe à importer : ");
        return scanner.nextInt();
    }

    private static L3NEW_TG_B9_Graph importAGraph(int graphNumber) throws IOException {

        BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(GRAPH_PATH + graphNumber + GRAPH_EXTENSION));
        int lineNumber = 0;
        String line = null;
        L3NEW_TG_B9_Graph newGraph = new L3NEW_TG_B9_Graph();

        while ((line = bufferedReader.readLine()) != null) {

            if (lineNumber >= 2) {
                extractDataFromRowLine(line, newGraph);
            } else if (lineNumber >= 1) {
                newGraph.setDirectedEdgesNumber(Integer.parseInt(line));
            } else {
                newGraph.setNodesNumber(Integer.parseInt(line));
            }

            lineNumber++;
        }

        return newGraph;
    }

    private static void extractDataFromRowLine(String line, L3NEW_TG_B9_Graph newGraph) {
        String[] edgeData = line.split(" ");

        L3NEW_TG_B9_Node originNode = getSpecificNodeOfGraphFromString(newGraph, edgeData[0]);
        L3NEW_TG_B9_Node destinationNode = getSpecificNodeOfGraphFromString(newGraph, edgeData[1]);

        originNode.addEdge(destinationNode, Integer.parseInt(edgeData[2]));
    }

    private static L3NEW_TG_B9_Node getSpecificNodeOfGraphFromString(L3NEW_TG_B9_Graph newGraph, String data) {
        L3NEW_TG_B9_Node node;

        //If the node is not already in the list, add it, else get it
        if (newGraph.isNodeNotAlreadyRegister(data)) {
            node = new L3NEW_TG_B9_Node(data);
            newGraph.addNodeToGraph(node);
        } else {
            node = newGraph.getSpecificNodeFromLabel(data);
        }
        return node;
    }

    //endregion
}
