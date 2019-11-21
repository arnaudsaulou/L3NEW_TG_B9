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

    public boolean isNodeNotAlreadyRegister(L3NEW_TG_B9_Node searchedNode) {
        return !this.nodesList.contains(searchedNode);
    }

    public void addNodeToGraph(L3NEW_TG_B9_Node newNode) {
        this.nodesList.add(newNode);
    }

    public L3NEW_TG_B9_Node getSpecificNode(L3NEW_TG_B9_Node searchedNod) {
        return this.nodesList.get(this.nodesList.indexOf(searchedNod));
    }

    public void print() {
        System.out.print(this);
        this.logger.log(this.toString());
    }

    private void fillAdjacencyOfANode(L3NEW_TG_B9_Node originNode) {
        //For each destination node
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

    private void fillValueOfANode(L3NEW_TG_B9_Node originNode) {
        //For each destination node
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

    private void displayColumnsHeadersMatrix() {

        System.out.print(String.format("%3s", ""));  //To align columns header with columns
        this.logger.log(String.format("%3s", ""));  //To align columns header with columns

        //Display columns header
        for (L3NEW_TG_B9_Node node : this.nodesList) {
            System.out.print(String.format("%5s", node.getLabel()));
            this.logger.log(String.format("%5s", node.getLabel()));
        }

    }

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

    private void removeNodeWithoutPredecessorOrSuccessor(ArrayList<L3NEW_TG_B9_Node> nodesListCopy) {

        boolean aNodeHasBeenRemoved = true;

        Iterator iterator;

        while (aNodeHasBeenRemoved) {
            aNodeHasBeenRemoved = false;
            iterator = nodesListCopy.iterator();

            while (iterator.hasNext()) {
                L3NEW_TG_B9_Node node = (L3NEW_TG_B9_Node) iterator.next();

                if (node.isListPredecessorEmpty()) {
                    System.out.println("Suppression point d'entré : " + node.getLabel());
                    this.logger.log("Suppression point d'entré : " + node.getLabel() + "\n");
                    node.destroyLinks();
                    iterator.remove();
                    aNodeHasBeenRemoved = true;
                } else if (node.isListSuccessorEmpty()) {
                    System.out.println("Suppression point de sortie : " + node.getLabel());
                    this.logger.log("Suppression point de sortie : " + node.getLabel() + "\n");
                    node.destroyLinks();
                    iterator.remove();
                    aNodeHasBeenRemoved = true;
                }
            }
        }
    }

    private boolean isGraphContainsCircuit(ArrayList<L3NEW_TG_B9_Node> nodesList) {
        return !nodesList.isEmpty();
    }

    public boolean circuitDetection() {

        System.out.print("\n - - - - - Détection de circuit - - - - - \n");
        this.logger.log("\n - - - - - Détection de circuit - - - - - \n");

        ArrayList<L3NEW_TG_B9_Node> arrayListCopy = deepCopyArrayList(this.nodesList);

        removeNodeWithoutPredecessorOrSuccessor(arrayListCopy);

        return isGraphContainsCircuit(arrayListCopy);
    }

    private ArrayList<L3NEW_TG_B9_Node> deepCopyArrayList(ArrayList<L3NEW_TG_B9_Node> nodesList) {
        ArrayList<L3NEW_TG_B9_Node> nodesListCopy = new ArrayList<>();

        for (L3NEW_TG_B9_Node node : nodesList) {
            nodesListCopy.add(new L3NEW_TG_B9_Node(node.getLabel()));
        }

        for (L3NEW_TG_B9_Node node : nodesList) {
            for (L3NEW_TG_B9_Node successor : node.getListSuccessor()) {
                nodesListCopy.get(nodesListCopy.indexOf(node)).addSuccessor(nodesListCopy.get(nodesListCopy.indexOf(successor)));
                nodesListCopy.get(nodesListCopy.indexOf(successor)).addPredecessor(nodesListCopy.get(nodesListCopy.indexOf(node)));
            }
        }

        return nodesListCopy;
    }

    public void computeRanks() {

        System.out.print("\n - - - - - Calcul des rang - - - - - \n");
        this.logger.log("\n - - - - - Calcul des rang - - - - - \n");

        //Initialized nbPredecessor
        HashMap<L3NEW_TG_B9_Node, Integer> nodePredecessorNumber = initializedNbPredecessor(this.nodesList);

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

    private ArrayList<L3NEW_TG_B9_Node> getOriginNodesDependingOnRank(HashMap<L3NEW_TG_B9_Node, Integer> nodePredecessorNumber, int rank) {
        ArrayList<L3NEW_TG_B9_Node> origins;
        if (rank == 0) {
            this.originNodesListOnRank0 = getOriginNodes(nodePredecessorNumber, rank);
            origins = new ArrayList<>(this.originNodesListOnRank0);
        } else {
            origins = getOriginNodes(nodePredecessorNumber, rank);
        }
        return origins;
    }

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

    private HashMap<L3NEW_TG_B9_Node, Integer> initializedNbPredecessor(ArrayList<L3NEW_TG_B9_Node> nodesListCopy) {
        HashMap<L3NEW_TG_B9_Node, Integer> nodePredecessorNumber = new HashMap<>();
        for (L3NEW_TG_B9_Node node : nodesListCopy) {
            nodePredecessorNumber.put(node, node.getListPredecessor().size());
        }
        return nodePredecessorNumber;
    }

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

    private void decrementNbPredecessorOfSuccessorOfOriginsNodes(HashMap<L3NEW_TG_B9_Node, Integer> hashMapNodeNbPredecessor, ArrayList<L3NEW_TG_B9_Node> origins) {
        for (L3NEW_TG_B9_Node origin : origins) {
            for (L3NEW_TG_B9_Node successor : origin.getListSuccessor()) {

                //Update hashMap : decrement NbPredecessor of a node
                hashMapNodeNbPredecessor.put(successor, hashMapNodeNbPredecessor.get(successor) - 1);
            }
        }
    }

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

    public boolean isSchedulingGraph() {
        int nbEntryPoint = 0, nbExitPoint = 0;
        boolean isAllEdgesPositive = true, isNbEntryPointValid = true, isNbExitPointValid = true, isValueAllEdgesEquals = true;
        boolean isValueEdgesOriginsNull = isValueEdgesOriginsNull();

        if (isValueEdgesOriginsNull) {

            //Run trough each node, stop as soon as a negative edge has been found or max entry node or max exiting node
            Iterator iteratorNode = this.nodesList.iterator();
            while (iteratorNode.hasNext() && isAllEdgesPositive && isNbEntryPointValid && isNbExitPointValid && isValueAllEdgesEquals) {
                L3NEW_TG_B9_Node node = (L3NEW_TG_B9_Node) iteratorNode.next();

                isAllEdgesPositive = isAllEdgesPositive(node);
                isNbEntryPointValid = isNbEntryPointValid(nbEntryPoint, node);
                isNbExitPointValid = isNbExitPointValid(nbExitPoint, node);
                isValueAllEdgesEquals = isValueAllEdgesEquals(node);

            }
        }
        return isValueEdgesOriginsNull && isAllEdgesPositive && isNbEntryPointValid && isNbExitPointValid && isValueAllEdgesEquals;
    }

    private boolean isNbEntryPointValid(int nbEntryPoint, L3NEW_TG_B9_Node node) {
        if (node.getListPredecessor().size() == 0) {
            nbEntryPoint++;
        }
        return nbEntryPoint < 2;
    }

    private boolean isNbExitPointValid(int nbExitPoint, L3NEW_TG_B9_Node node) {
        if (node.getListSuccessor().size() == 0) {
            nbExitPoint++;
        }
        return nbExitPoint < 2;
    }

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

    private boolean isValueAllEdgesEquals(L3NEW_TG_B9_Node node) {
        boolean isValueAllEdgesEquals = true;
        int saveValue = -1;

        //Run through all edges value of origin while isValueEdgesOriginsNull
        Iterator iteratorEdgeValue = node.getListEdges().values().iterator();
        while (iteratorEdgeValue.hasNext() && isValueAllEdgesEquals) {
            Integer edgeValue = (Integer) iteratorEdgeValue.next();

            if (saveValue == -1) {
                saveValue = edgeValue;
            } else if (edgeValue != saveValue) {
                isValueAllEdgesEquals = false;
            }
        }

        return isValueAllEdgesEquals;
    }

    public void computeCalendar() {

        System.out.print(" - - - - - Calendrier - - - - - \n");
        this.logger.log(" - - - - - Calendrier - - - - - \n");

        computeEarliestDate(this.originNodesListOnRank0.get(0));
        computeLatestDate(this.originNodesListOnRank0.get(0));
        computeMargin();

        System.out.println("Noeud  |  Date au plus tôt  |  Date au plus tard  |  Marge");
        this.logger.log("Noeud  |  Date au plus tôt  |  Date au plus tard  |  Marge\n");

        for (L3NEW_TG_B9_Node node : this.nodesList) {
            System.out.println(node.displayCalendar());
            this.logger.log(node.displayCalendar() + "\n");
        }

        System.out.println();
        this.logger.log("\n");

        this.logger.save();
    }

    private void computeEarliestDate(L3NEW_TG_B9_Node node) {

        for (L3NEW_TG_B9_Node successor : node.getListSuccessor()) {
            int edgeValue = node.getWeightEdge(successor);
            successor.setEarliestDate(Math.max(node.getEarliestDate() + edgeValue, successor.getEarliestDate()));

            if (!node.isListSuccessorEmpty()) {
                computeEarliestDate(successor);
            }
        }

    }

    private void computeLatestDate(L3NEW_TG_B9_Node node) {

        if (node.isListSuccessorEmpty()) {
            node.setLatestDate(node.getEarliestDate());
        } else {
            int heaviestEdge = -1;
            for (L3NEW_TG_B9_Node successor : node.getListSuccessor()) {
                int edgeValue = node.getListEdges().get(successor);

                computeLatestDate(successor);

                if (successor.getLatestDate() - edgeValue < heaviestEdge || heaviestEdge == -1) {
                    heaviestEdge = successor.getLatestDate() - edgeValue;
                }
            }
            node.setLatestDate(heaviestEdge);
        }
    }

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
