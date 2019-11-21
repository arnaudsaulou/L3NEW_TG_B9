import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class L3NEW_TG_B9_Graph {

    //region Variables

    private final L3NEW_TG_B9_Logger logger;
    private final ArrayList<L3NEW_TG_B9_Node> nodesList;
    private ArrayList<L3NEW_TG_B9_Node> originNodesListOnRank0;

    //endregion

    //region Constructor

    public L3NEW_TG_B9_Graph(int graphNumber) {
        this.logger = new L3NEW_TG_B9_Logger(graphNumber);
        this.nodesList = new ArrayList<>();
        this.originNodesListOnRank0 = new ArrayList<>();
    }

    //endregion

    //region Utils

    /**
     * Check if a node is already register in the list of nodes of the graph
     *
     * @param searchedNode the searched node
     * @return True if the node exist, False else
     */
    public boolean isNodeNotAlreadyRegister(L3NEW_TG_B9_Node searchedNode) {
        return !this.nodesList.contains(searchedNode);
    }

    /**
     * Add a new node to the list of nodes of the graph
     *
     * @param newNode the new node to add
     */
    public void addNodeToGraph(L3NEW_TG_B9_Node newNode) {
        this.nodesList.add(newNode);
    }

    /**
     * Get a the node name like the node pass in parameters
     *
     * @param searchedNod the node reference node
     * @return the node in the graph name like the reference node
     */
    public L3NEW_TG_B9_Node getSpecificNode(L3NEW_TG_B9_Node searchedNod) {
        return this.nodesList.get(this.nodesList.indexOf(searchedNod));
    }

    /**
     * To print the entire graph structure
     */
    public void print() {
        System.out.print(this);
        this.logger.log(this.toString());
    }

    /**
     * Display in row all nodes of the graph
     */
    private void displayColumnsHeadersMatrix() {

        System.out.print(String.format("%3s", ""));  //To align columns header with columns
        this.logger.log(String.format("%3s", ""));  //To align columns header with columns

        //Display columns header
        for (L3NEW_TG_B9_Node node : this.nodesList) {
            System.out.print(String.format("%5s", node.getLabel()));
            this.logger.log(String.format("%5s", node.getLabel()));
        }

    }

    /**
     * Procedure to display the entire adjacency matrix
     */
    public void displayAdjacencyMatrix() {

        System.out.print("\n - - - - - Matrice d'adjacence - - - - - \n");
        this.logger.log("\n - - - - - Matrice d'adjacence - - - - - \n");

        this.displayColumnsHeadersMatrix();

        //For each origin node
        for (L3NEW_TG_B9_Node node : this.nodesList) {

            //Display row header
            System.out.print("\n" + String.format("%3s", node.getLabel()));
            this.logger.log("\n" + String.format("%3s", node.getLabel()));

            fillAdjacencyOfANode(node);
        }

        System.out.println();
        this.logger.log("\n");
    }

    /**
     * Print V if there is an edge between the node pass in parameters and the others node, F else
     *
     * @param originNode the node to refer to
     */
    private void fillAdjacencyOfANode(L3NEW_TG_B9_Node originNode) {
        //For each node of the graph
        for (L3NEW_TG_B9_Node destinationNode : this.nodesList) {

            //Check if destinationNode is a successor of the originNode
            if (originNode.isNodeASuccessor(destinationNode)) {
                System.out.print(String.format("%5s", "V"));
                this.logger.log(String.format("%5s", "V"));
            } else {
                System.out.print(String.format("%5s", "F"));
                this.logger.log(String.format("%5s", "F"));
            }
        }
    }

    /**
     * Procedure to display the entire values matrix
     */
    public void displayValuesMatrix() {

        System.out.print("\n - - - - - Matrice de valeures - - - - - \n");
        this.logger.log("\n - - - - - Matrice de valeures - - - - - \n");

        this.displayColumnsHeadersMatrix();

        //For each origin node
        for (L3NEW_TG_B9_Node node : this.nodesList) {

            //Display row header
            System.out.print("\n" + String.format("%3s", node.getLabel()));
            this.logger.log("\n" + String.format("%3s", node.getLabel()));

            fillValueOfANode(node);
        }

        System.out.println();
        this.logger.log("\n");
    }

    /**
     * Print the value of the edge between the node pass in parameters and the others node
     *
     * @param originNode the node to refer to
     */
    private void fillValueOfANode(L3NEW_TG_B9_Node originNode) {
        //For each node of the graph
        for (L3NEW_TG_B9_Node destinationNode : this.nodesList) {

            //Check if destinationNode is a successor of the originNode
            if (originNode.isNodeASuccessor(destinationNode)) {
                System.out.print(String.format("%5d", originNode.getWeightEdge(destinationNode)));
                this.logger.log(String.format("%5d", originNode.getWeightEdge(destinationNode)));
            } else {
                System.out.print(String.format("%5d", 0));
                this.logger.log(String.format("%5d", 0));
            }
        }
    }

    /**
     * Remove successively nodes without predecessor or successor
     *
     * @param nodesListCopy the list of node to work with
     */
    private void removeNodeWithoutPredecessorOrSuccessor(ArrayList<L3NEW_TG_B9_Node> nodesListCopy) {

        boolean aNodeHasBeenRemoved = true;

        Iterator iterator;

        //While a node has been removed
        while (aNodeHasBeenRemoved) {
            aNodeHasBeenRemoved = false;
            iterator = nodesListCopy.iterator();

            //Run trough all nodes of the graph
            while (iterator.hasNext()) {
                L3NEW_TG_B9_Node node = (L3NEW_TG_B9_Node) iterator.next();

                //Delete the node if it has no predecessor
                if (node.isListPredecessorEmpty()) {
                    System.out.println("Suppression point d'entré : " + node.getLabel());
                    this.logger.log("Suppression point d'entré : " + node.getLabel() + "\n");
                    node.destroyLinks();
                    iterator.remove();
                    aNodeHasBeenRemoved = true;
                }

                //Delete the node if it has no successor
                else if (node.isListSuccessorEmpty()) {
                    System.out.println("Suppression point de sortie : " + node.getLabel());
                    this.logger.log("Suppression point de sortie : " + node.getLabel() + "\n");
                    node.destroyLinks();
                    iterator.remove();
                    aNodeHasBeenRemoved = true;
                }
            }
        }
    }

    /**
     * Check if the graph contains a circuit or not from its list of nodes
     *
     * @param nodesList the list of nodes of the graph to test
     * @return True if the graph contains a circuit, False else
     */
    private boolean isGraphContainsCircuit(ArrayList<L3NEW_TG_B9_Node> nodesList) {
        return !nodesList.isEmpty();
    }

    /**
     * The entire procedure to check if the graph contains a circuit or not
     *
     * @return True if the graph contains a circuit, False else
     */
    public boolean circuitDetection() {

        System.out.print("\n - - - - - Détection de circuit - - - - - \n");
        this.logger.log("\n - - - - - Détection de circuit - - - - - \n");

        ArrayList<L3NEW_TG_B9_Node> arrayListCopy = deepCopyArrayList(this.nodesList);

        removeNodeWithoutPredecessorOrSuccessor(arrayListCopy);

        return isGraphContainsCircuit(arrayListCopy);
    }

    /**
     * To create a real copy of the ArrayList (and all of it components) pass in parameters
     *
     * @param nodesList the list of node to copy
     * @return the copy of the original list
     */
    private ArrayList<L3NEW_TG_B9_Node> deepCopyArrayList(ArrayList<L3NEW_TG_B9_Node> nodesList) {
        ArrayList<L3NEW_TG_B9_Node> nodesListCopy = new ArrayList<>();

        //Copy all nodes
        for (L3NEW_TG_B9_Node node : nodesList) {
            nodesListCopy.add(new L3NEW_TG_B9_Node(node.getLabel()));
        }

        //For each nodes, copy links between others nodes
        for (L3NEW_TG_B9_Node node : nodesList) {
            for (L3NEW_TG_B9_Node successor : node.getListSuccessor()) {
                nodesListCopy.get(nodesListCopy.indexOf(node)).addSuccessor(nodesListCopy.get(nodesListCopy.indexOf(successor)));
                nodesListCopy.get(nodesListCopy.indexOf(successor)).addPredecessor(nodesListCopy.get(nodesListCopy.indexOf(node)));
            }
        }

        return nodesListCopy;
    }

    /**
     * The entire procedure to compute ranks of all nodes of the graph
     */
    public void computeRanks() {

        System.out.print("\n - - - - - Calcul des rang - - - - - \n");
        this.logger.log("\n - - - - - Calcul des rang - - - - - \n");

        //Initialized the number of predecessor of all nodes of the graph
        HashMap<L3NEW_TG_B9_Node, Integer> nodePredecessorNumber = initializedNbPredecessor(this.nodesList);

        //Create a list which will contains all origins node per rank
        ArrayList<L3NEW_TG_B9_Node> origins = new ArrayList<>();

        int rank = 0;
        while (origins.size() != 0 || rank == 0) {

            //Get ride of last pass origins
            origins.clear();

            origins = getOriginNodesDependingOnRank(nodePredecessorNumber, rank);
            displayRankTrace(origins, rank);
            decrementNbPredecessorOfSuccessorOfOriginsNodes(nodePredecessorNumber, origins);
            rank++;
        }
        displayRank();
    }

    /**
     * The procedure to get the list of origin nodes for the specific rank
     *
     * @param nodePredecessorNumber the hashMap that associate a node with it's number of predecessor
     * @param rank                  the actual rank
     * @return the list of origin nodes
     */
    private ArrayList<L3NEW_TG_B9_Node> getOriginNodesDependingOnRank(HashMap<L3NEW_TG_B9_Node, Integer> nodePredecessorNumber, int rank) {
        ArrayList<L3NEW_TG_B9_Node> origins;
        if (rank == 0) {
            //If rank is 0, save the origin nodes in originNodesListOnRank0, we will need that later
            this.originNodesListOnRank0 = getOriginNodes(nodePredecessorNumber, rank);
            origins = new ArrayList<>(this.originNodesListOnRank0);
        } else {
            origins = getOriginNodes(nodePredecessorNumber, rank);
        }
        return origins;
    }

    /**
     * Get the list of origin nodes for the specific rank, decrement the number of predecessor for next next rank and
     * set the actual rank to each node if it's an origin of the graph at that rank
     *
     * @param nodePredecessorNumber the hashMap that associate a node with it's number of predecessor
     * @param rank                  the actual rank
     * @return the list of origin nodes
     */
    private ArrayList<L3NEW_TG_B9_Node> getOriginNodes(HashMap<L3NEW_TG_B9_Node, Integer> nodePredecessorNumber, int rank) {
        ArrayList<L3NEW_TG_B9_Node> origins = new ArrayList<>();
        for (L3NEW_TG_B9_Node node : nodePredecessorNumber.keySet()) {
            if (nodePredecessorNumber.get(node) == 0) {
                origins.add(node);
                nodePredecessorNumber.put(node, nodePredecessorNumber.get(node) - 1);
                node.setRank(rank);
            }
        }
        return origins;
    }

    /**
     * Display the actual steps of the algorithm of rank computing
     *
     * @param origins the list of origin nodes of the graph at that rank
     * @param rank    the actual rank
     */
    private void displayRankTrace(ArrayList<L3NEW_TG_B9_Node> origins, int rank) {
        if (origins.size() != 0) {
            System.out.print("Rang courant = " + rank + "\nPoint d'entrée : ");
            this.logger.log("Rang courant = " + rank + "\nPoint d'entrée : ");
            for (L3NEW_TG_B9_Node origin : origins) {
                System.out.print(origin.getLabel() + " ");
                this.logger.log(origin.getLabel() + " ");
            }
            System.out.println("\n");
            this.logger.log("\n");
        } else {
            System.out.println("Graphe vide\n");
            this.logger.log("Graphe vide\n\n");
        }
    }

    /**
     * Compute the number of predecessor of each nodes of the graph
     *
     * @param nodesListCopy the list of node of the graph
     * @return the hashMap that associate a node with it's number of predecessor
     */
    private HashMap<L3NEW_TG_B9_Node, Integer> initializedNbPredecessor(ArrayList<L3NEW_TG_B9_Node> nodesListCopy) {
        HashMap<L3NEW_TG_B9_Node, Integer> nodePredecessorNumber = new HashMap<>();
        for (L3NEW_TG_B9_Node node : nodesListCopy) {
            nodePredecessorNumber.put(node, node.getListPredecessor().size());
        }
        return nodePredecessorNumber;
    }

    /**
     * Decrement the number of predecessor of all successor of all origins of the graph
     *
     * @param hashMapNodeNbPredecessor the hashMap that associate a node with it's number of predecessor
     * @param origins                  the list of origins of the graph at that rank
     */
    private void decrementNbPredecessorOfSuccessorOfOriginsNodes(HashMap<L3NEW_TG_B9_Node, Integer> hashMapNodeNbPredecessor, ArrayList<L3NEW_TG_B9_Node> origins) {
        for (L3NEW_TG_B9_Node origin : origins) {
            for (L3NEW_TG_B9_Node successor : origin.getListSuccessor()) {

                //Update hashMap : decrement NbPredecessor of a node
                hashMapNodeNbPredecessor.put(successor, hashMapNodeNbPredecessor.get(successor) - 1);
            }
        }
    }

    /**
     * Display computed rank
     */
    private void displayRank() {

        System.out.println("Rang calculés : ");
        this.logger.log("Rang calculés : \n");

        for (L3NEW_TG_B9_Node node : this.nodesList) {
            System.out.print(String.format("%4s", node));
            this.logger.log(String.format("%4s", node));
        }

        System.out.println();
        this.logger.log("\n");

        for (L3NEW_TG_B9_Node node : this.nodesList) {
            System.out.print(String.format("%4d", node.getRank()));
            this.logger.log(String.format("%4d", node.getRank()));
        }

        System.out.println("\n");
        this.logger.log("\n\n");
    }

    /**
     * Check if the graph is a scheduling graph or not
     *
     * @return True if the graph is scheduling graph, False else
     */
    public boolean isSchedulingGraph() {
        int nbEntryPoint = 0, nbExitPoint = 0;
        boolean isAllEdgesPositive = true, isNbEntryPointValid = true, isNbExitPointValid = true, isValueAllEdgesEquals = true;
        boolean isValueEdgesOriginsNull = isValueEdgesOriginsNull();

        if (isValueEdgesOriginsNull) {

            //Run trough each node, stop as soon as a negative edge has been found or max entry node or max exiting node
            Iterator iteratorNode = this.nodesList.iterator();
            while (iteratorNode.hasNext() && isAllEdgesPositive && isNbEntryPointValid && isNbExitPointValid && isValueAllEdgesEquals) {
                L3NEW_TG_B9_Node node = (L3NEW_TG_B9_Node) iteratorNode.next();

                //Conditions to be respected by the graph for it to be a scheduling graph
                isAllEdgesPositive = isAllEdgesPositive(node);
                isNbEntryPointValid = isNbEntryPointValid(nbEntryPoint, node);
                isNbExitPointValid = isNbExitPointValid(nbExitPoint, node);
                isValueAllEdgesEquals = isValueAllEdgesEquals(node);

            }
        }
        return isValueEdgesOriginsNull && isAllEdgesPositive && isNbEntryPointValid && isNbExitPointValid && isValueAllEdgesEquals;
    }

    /**
     * Check if there is only one entry point in the graph
     * @param nbEntryPoint the number of entry point already detected
     * @param node the node to test
     * @return True if there is up to one entry point, False else
     */
    private boolean isNbEntryPointValid(int nbEntryPoint, L3NEW_TG_B9_Node node) {
        if (node.getListPredecessor().size() == 0) {
            nbEntryPoint++;
        }
        return nbEntryPoint < 2;
    }

    /**
     * Check if there is only one exit point in the graph
     * @param nbExitPoint the number of exit point already detected
     * @param node the node to test
     * @return True if there is up to one exit point, False else
     */
    private boolean isNbExitPointValid(int nbExitPoint, L3NEW_TG_B9_Node node) {
        if (node.getListSuccessor().size() == 0) {
            nbExitPoint++;
        }
        return nbExitPoint < 2;
    }

    /**
     * Check if all edges value's exiting from the node pass in parameter are positive
     * @param node the node to test
     * @return True if all edges value's exiting from the node are positive, False else
     */
    private boolean isAllEdgesPositive(L3NEW_TG_B9_Node node) {
        boolean isSchedulingGraph = true;
        Iterator iteratorEdgeValue = node.getListEdges().values().iterator();
        while (iteratorEdgeValue.hasNext() && isSchedulingGraph) {
            Integer keyEdgeValue = (Integer) iteratorEdgeValue.next();
            if (keyEdgeValue < 0) {
                isSchedulingGraph = false;
            }
        }
        return isSchedulingGraph;
    }

    /**
     * Check if all edges value exiting from the origin are 0
     * @return True if all edges value exiting from the origin are 0, False else
     */
    private boolean isValueEdgesOriginsNull() {
        boolean isValueEdgesOriginsNull = true;

        //Run through all origins while isValueEdgesOriginsNull
        Iterator iteratorOrigin = this.originNodesListOnRank0.iterator();
        while (iteratorOrigin.hasNext() && isValueEdgesOriginsNull) {
            L3NEW_TG_B9_Node origin = (L3NEW_TG_B9_Node) iteratorOrigin.next();

            //Run through all edges value of origin while isValueEdgesOriginsNull
            Iterator iteratorEdgeValue = origin.getListEdges().values().iterator();
            while (iteratorEdgeValue.hasNext() && isValueEdgesOriginsNull) {
                Integer edgeValue = (Integer) iteratorEdgeValue.next();
                if (edgeValue != 0) {
                    isValueEdgesOriginsNull = false;
                }
            }
        }
        return isValueEdgesOriginsNull;
    }

    /**
     * Check if all edges value exiting from a node have the same values
     * @param node the node to test
     * @return True if all edges value exiting from a node have the same values, False else
     */
    private boolean isValueAllEdgesEquals(L3NEW_TG_B9_Node node) {
        boolean isValueAllEdgesEquals = true;
        int saveValue = -1;

        //Run through all edges value of origin while isValueEdgesOriginsNull
        Iterator iteratorEdgeValue = node.getListEdges().values().iterator();
        while (iteratorEdgeValue.hasNext() && isValueAllEdgesEquals) {
            Integer edgeValue = (Integer) iteratorEdgeValue.next();

            //Save the value of the first edge
            if (saveValue == -1) {
                saveValue = edgeValue;
            }
            //Compare other edges value's with the saved value
            else if (edgeValue != saveValue) {
                isValueAllEdgesEquals = false;
            }
        }

        return isValueAllEdgesEquals;
    }

    /**
     * Entire procedure to compute calendar (earliest date, latest date and margins)
     */
    public void computeCalendar() {

        System.out.print(" - - - - - Calendrier - - - - - \n");
        this.logger.log(" - - - - - Calendrier - - - - - \n");

        computeEarliestDate(this.originNodesListOnRank0.get(0));
        computeLatestDate(this.originNodesListOnRank0.get(0));
        computeMargin();

        System.out.println("Noeud  |  Date au plus tôt  |  Date au plus tard  |  Marge");
        this.logger.log("Noeud  |  Date au plus tôt  |  Date au plus tard  |  Marge\n");

        //Display calendar of each nod of the graph
        for (L3NEW_TG_B9_Node node : this.nodesList) {
            System.out.println(node.displayCalendar());
            this.logger.log(node.displayCalendar() + "\n");
        }

        System.out.println();
        this.logger.log("\n");

        //To save all logs and close the log file
        this.logger.save();
    }

    /**
     * Compute the earliest date for the node pass in parameter
     * @param node the node to work with
     */
    private void computeEarliestDate(L3NEW_TG_B9_Node node) {

        for (L3NEW_TG_B9_Node successor : node.getListSuccessor()) {
            int edgeValue = node.getWeightEdge(successor);

            //The earliest date is the earliest date of a successor is the earliest date of the node +
            //the value of the edge that connects them
            successor.setEarliestDate(Math.max(node.getEarliestDate() + edgeValue, successor.getEarliestDate()));

            if (!node.isListSuccessorEmpty()) {
                computeEarliestDate(successor);
            }
        }

    }

    /**
     * Compute the latest date for the node pass in parameter
     * @param node the node to work with
     */
    private void computeLatestDate(L3NEW_TG_B9_Node node) {

        //For the exit node, the latest date is the earliest date
        if (node.isListSuccessorEmpty()) {
            node.setLatestDate(node.getEarliestDate());
        } else {
            int heaviestEdge = -1;

            //For each successor of the node
            for (L3NEW_TG_B9_Node successor : node.getListSuccessor()) {
                int edgeValue = node.getListEdges().get(successor);
                computeLatestDate(successor);

                //Save the value of the critical path between the node and the successor
                if (successor.getLatestDate() - edgeValue < heaviestEdge || heaviestEdge == -1) {
                    heaviestEdge = successor.getLatestDate() - edgeValue;
                }
            }
            node.setLatestDate(heaviestEdge);
        }
    }

    /**
     * Compute margin of all nodes of the graph
     */
    private void computeMargin() {
        for (L3NEW_TG_B9_Node node : this.nodesList) {
            node.computeMargin();
        }
    }

    //endregion

    //region Override

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n - - - - - Graphe - - - - - \n");
        stringBuilder.append("Origine   Poids   Destination").append("\n");

        for (L3NEW_TG_B9_Node node : this.nodesList) {
            stringBuilder.append(node.displayEdges());
        }

        return stringBuilder.toString();
    }

    //endregion
}
