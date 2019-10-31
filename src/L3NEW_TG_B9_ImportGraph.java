import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class L3NEW_TG_B9_ImportGraph {

    //region CONSTANTS
    private static final String GRAPH_PATH = "res/L3NEW-TG-B9-g";
    private static final String GRAPH_EXTENSION = ".txt";
    //endregion

    public L3NEW_TG_B9_ImportGraph() {
    }

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

        StringBuilder stringBuilder = new StringBuilder();
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

            stringBuilder.append(line).append("\n");
            lineNumber++;
        }

        return newGraph;
    }


    private static void extractDataFromRowLine(String line, L3NEW_TG_B9_Graph newGraph) {
        String[] edgeData = line.split(" ");

        L3NEW_TG_B9_Node originNode = null;
        L3NEW_TG_B9_Node destinationNode = null;

        //If the node is already in the list
        if (!newGraph.isNodeAlreadyRegister(edgeData[0])) {
            originNode = new L3NEW_TG_B9_Node(edgeData[0]);
            newGraph.addNodeToGraph(originNode);
        } else {
            originNode = newGraph.getSpecificNodeFromLabel(edgeData[0]);
        }

        if (!newGraph.isNodeAlreadyRegister(edgeData[1])) {
            destinationNode = new L3NEW_TG_B9_Node(edgeData[1]);
            newGraph.addNodeToGraph(destinationNode);
        } else {
            destinationNode = newGraph.getSpecificNodeFromLabel(edgeData[1]);
        }

        L3NEW_TG_B9_DirectedEdge newEdge = new L3NEW_TG_B9_DirectedEdge(originNode, destinationNode, Integer.parseInt(edgeData[2]));
        originNode.getListSuccessor().add(newEdge);

    }
}
