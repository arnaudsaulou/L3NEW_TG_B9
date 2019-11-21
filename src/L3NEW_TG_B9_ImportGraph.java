import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

class L3NEW_TG_B9_ImportGraph {

    //region CONSTANTS
    private static final String GRAPH_PATH = "res/L3NEW-TG-B9-g";
    private static final String GRAPH_EXTENSION = ".txt";
    //endregion

    //region Utils

    /**
     * The entire procedure to import a graph from the choice of the user
     *
     * @return the graph selected
     */
    public static L3NEW_TG_B9_Graph importGraphProcedure() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\n - - - - - Importation du graphe - - - - - \n");

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

    /**
     * Ask the user to enter a number which will select the graph to work with
     * @param scanner the keyboard
     * @return the graph number asked by the user
     * @throws InputMismatchException if the user enter anything other than an integer
     */
    private static int askUserGraphNumber(Scanner scanner) throws InputMismatchException {
        System.out.print("Entrer le numero du graphe à importer : ");
        return scanner.nextInt();
    }

    /**
     * Read a graph file from the graph number pass in parameter
     * @param graphNumber the number of the graph asked
     * @return the graph asked
     * @throws IOException if the graph asked doesn't exist in the folder res
     */
    private static L3NEW_TG_B9_Graph importAGraph(int graphNumber) throws IOException {

        BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(GRAPH_PATH + graphNumber + GRAPH_EXTENSION));
        int lineNumber = 0;
        String line;
        L3NEW_TG_B9_Graph newGraph = new L3NEW_TG_B9_Graph(graphNumber);

        //While there is a line to read in the file
        while ((line = bufferedReader.readLine()) != null) {
            //Extract data from the line read
            graphParser(line, newGraph);
            lineNumber++;
        }

        return newGraph;
    }

    /**
     * To extract the data from the line read and transfer them into the graph structure
     * @param line the line read
     * @param newGraph the graph structure
     */
    private static void graphParser(String line, L3NEW_TG_B9_Graph newGraph) {
        //Data are separated by a space, split the line into an array at each space
        String[] edgeData = line.split(" ");

        //The first data of the line is the origin node of the edge
        L3NEW_TG_B9_Node originNode = getSpecificNodeOfGraphFromString(newGraph, edgeData[0]);

        //The second data of the line is the destination node of the edge
        L3NEW_TG_B9_Node destinationNode = getSpecificNodeOfGraphFromString(newGraph, edgeData[1]);

        //The third data of the line is the weight of the edge
        originNode.addEdge(destinationNode, Integer.parseInt(edgeData[2]));
    }

    /**
     * To get an object node present in the graph from its label, if it doesn't exist create it
     *
     * @param newGraph  the graph
     * @param nodeLabel the label of the node
     * @return the node present in the graph
     */
    private static L3NEW_TG_B9_Node getSpecificNodeOfGraphFromString(L3NEW_TG_B9_Graph newGraph, String nodeLabel) {
        L3NEW_TG_B9_Node node = null;

        try {
            node = new L3NEW_TG_B9_Node(Integer.parseInt(nodeLabel));

            //If the node is not already in the list, add it, else get it
            if (newGraph.isNodeNotAlreadyRegister(node)) {
                newGraph.addNodeToGraph(node);
            } else {
                node = newGraph.getSpecificNode(node);
            }

        } catch (InputMismatchException e) {
            e.printStackTrace();
        }

        return node;
    }

    //endregion
}
