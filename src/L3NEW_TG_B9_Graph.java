import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class L3NEW_TG_B9_Graph {

    //region Variables

    private int numberOfNode;
    private int numberOfEdges;
    private HashMap<Integer, L3NEW_TG_B9_Node> nodesHashMap;

    //endregion

    //region Constructor

    public L3NEW_TG_B9_Graph() {
        this.numberOfNode = 0;
        this.numberOfEdges = 0;
        this.nodesHashMap = new HashMap<>();
    }

    //endregion

    //region Utils

    public boolean isNodeNotAlreadyRegister(Integer researchNodeLabel) {
        return !this.nodesHashMap.containsKey(researchNodeLabel);
    }

    public void addNodeToGraph(L3NEW_TG_B9_Node newNode) {
        this.nodesHashMap.put(newNode.getLabel(), newNode);
    }

    public L3NEW_TG_B9_Node getSpecificNodeFromLabel(Integer nodeLabel) {
        return this.nodesHashMap.get(nodeLabel);
    }

    private void fillAdjacencyOfANode(L3NEW_TG_B9_Node originNode) {

        //For each destination node
        for (L3NEW_TG_B9_Node destinationNode : this.nodesHashMap.values()) {

            //Check if destinationNode is a successor of the originNode
            if (originNode.isNodeASuccessor(destinationNode)) {
                System.out.print(String.format("%5s", "V"));
            } else {
                System.out.print(String.format("%5s", "F"));
            }
        }
    }

    private void fillValueOfANode(L3NEW_TG_B9_Node originNode) {

        //For each destination node
        for (L3NEW_TG_B9_Node destinationNode : this.nodesHashMap.values()) {

            //Check if destinationNode is a successor of the originNode
            if (originNode.isNodeASuccessor(destinationNode)) {
                System.out.print(String.format("%5d", originNode.getWeightEdge(destinationNode)));
            } else {
                System.out.print(String.format("%5d", 0));
            }
        }
    }

    private void displayColumnsHeadersMatrix() {

        System.out.print(String.format("%3s", ""));  //To align columns header with columns

        //Display columns header
        for (Integer nodeLabel : this.nodesHashMap.keySet()) {
            System.out.print(String.format("%5s", nodeLabel));
        }

    }

    public void displayAdjacencyMatrix() {

        System.out.print("\n - - - - - Matrice d'adjacence - - - - - \n");

        this.displayColumnsHeadersMatrix();

        //For each origin node
        for (Integer originNodeLabel : this.nodesHashMap.keySet()) {

            //Display row header
            System.out.print("\n" + String.format("%3s", originNodeLabel));

            //Get list successor
            L3NEW_TG_B9_Node originNode = this.getSpecificNodeFromLabel(originNodeLabel);

            fillAdjacencyOfANode(originNode);
        }

        System.out.println();
    }

    public void displayValuesMatrix() {

        System.out.print("\n - - - - - Matrice de valeures - - - - - \n");

        this.displayColumnsHeadersMatrix();

        //For each origin node
        for (Integer originNodeLabel : this.nodesHashMap.keySet()) {

            //Display row header
            System.out.print("\n" + String.format("%3s", originNodeLabel));

            //Get originNode
            L3NEW_TG_B9_Node originNode = this.getSpecificNodeFromLabel(originNodeLabel);

            fillValueOfANode(originNode);
        }

        System.out.println();
    }

    public void removeNodeWithoutPredecessorOrSuccessor(HashMap<Integer, L3NEW_TG_B9_Node> nodesHashMapCopy) {

        boolean aNodeHasBeenRemoved = true;

        while (aNodeHasBeenRemoved) {
            aNodeHasBeenRemoved = false;

            Iterator iterator = nodesHashMapCopy.keySet().iterator();

            while (iterator.hasNext()) {
                Integer key = (Integer) iterator.next();
                L3NEW_TG_B9_Node node = nodesHashMapCopy.get(key);

                if (node.isListPredecessorEmpty()) {
                    System.out.println("Suppression point d'entré : " + node.getLabel());
                    node.destroyLinks();
                    iterator.remove();
                    aNodeHasBeenRemoved = true;
                } else if (node.isListSuccessorEmpty()) {
                    System.out.println("Suppression point de sortie : " + node.getLabel());
                    node.destroyLinks();
                    iterator.remove();
                    aNodeHasBeenRemoved = true;
                }
            }
        }
    }

    private boolean isGraphContainsCircuit(HashMap<Integer, L3NEW_TG_B9_Node> nodesHashMapCopy) {
        return !nodesHashMapCopy.isEmpty();
    }

    public void circuitDetection() {

        System.out.print("\n - - - - - Détection de circuit - - - - - \n");

        HashMap<Integer, L3NEW_TG_B9_Node> nodesHashMapCopy = new HashMap<>(this.nodesHashMap);

        //TODO Error reference copy instead of value copy

        //removeNodeWithoutPredecessorOrSuccessor(nodesHashMapCopy);

        /*if(isGraphContainsCircuit(nodesHashMapCopy)){
            System.out.println("Le circuit contient au moins un circuit");
        }else{*/
        this.computeRank();
        //}

    }

    private void computeRank() {

        System.out.print("\n - - - - - Calcul des rang - - - - - \n");

        //Initialized nbPredecessor
        HashMap<L3NEW_TG_B9_Node, Integer> nodePredecessorNumber = initializedNbPredecessor(this.nodesHashMap);

        ArrayList<L3NEW_TG_B9_Node> origins = new ArrayList<>();

        int rank = 0;
        while (origins.size() != 0 || rank == 0) {

            //Get ride of last pass origins
            origins.clear();

            getOriginNodes(nodePredecessorNumber, origins, rank);
            decrementNbPredecessorOfSuccessorOfOriginsNodes(nodePredecessorNumber, origins);

            rank++;

        }

        displayRank();
    }

    private HashMap<L3NEW_TG_B9_Node, Integer> initializedNbPredecessor(HashMap<Integer, L3NEW_TG_B9_Node> nodesHashMapCopy) {
        HashMap<L3NEW_TG_B9_Node, Integer> nodePredecessorNumber = new HashMap<>();
        for (L3NEW_TG_B9_Node node : nodesHashMapCopy.values()) {
            nodePredecessorNumber.put(node, node.getListPredecessor().size());
        }
        return nodePredecessorNumber;
    }

    private void getOriginNodes(HashMap<L3NEW_TG_B9_Node, Integer> nodePredecessorNumber, ArrayList<L3NEW_TG_B9_Node> origins, int rank) {
        for (L3NEW_TG_B9_Node node : nodePredecessorNumber.keySet()) {
            if (nodePredecessorNumber.get(node) == 0) {
                origins.add(node);
                nodePredecessorNumber.put(node, nodePredecessorNumber.get(node) - 1);
                node.setRank(rank);
            }
        }
    }

    private void decrementNbPredecessorOfSuccessorOfOriginsNodes(HashMap<L3NEW_TG_B9_Node, Integer> nodePredecessorNumber, ArrayList<L3NEW_TG_B9_Node> origins) {
        for (L3NEW_TG_B9_Node origin : origins) {
            for (L3NEW_TG_B9_Node successor : origin.getListSuccessor()) {
                nodePredecessorNumber.put(successor, nodePredecessorNumber.get(successor) - 1);
            }
        }
    }

    private void displayRank() {
        for (Integer node : this.nodesHashMap.keySet()) {
            System.out.print(String.format("%4d", node));
        }

        System.out.println();

        for (L3NEW_TG_B9_Node node : this.nodesHashMap.values()) {
            System.out.print(String.format("%4d", node.getRank()));
        }
    }

    //endregion

    //region Getter

    public int getNumberOfNode() {
        return numberOfNode;
    }

    public int getNumberOfEdges() {
        return numberOfEdges;
    }

    public HashMap<Integer, L3NEW_TG_B9_Node> getNodesHashMap() {
        return nodesHashMap;
    }

    //endregion

    //region Setter

    public void setNumberOfNode(int numberOfNode) {
        this.numberOfNode = numberOfNode;
    }

    public void setNumberOfEdges(int numberOfEdges) {
        this.numberOfEdges = numberOfEdges;
    }

    public void setNodesHashMap(HashMap<Integer, L3NEW_TG_B9_Node> nodesHashMap) {
        this.nodesHashMap = nodesHashMap;
    }

    //endregion

    //region Override

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n - - - - - Graphe - - - - - \n");
        stringBuilder.append("Nombre de sommet(s) : ").append(numberOfNode).append("\n");
        stringBuilder.append("Nombre d'arc(s) : ").append(numberOfEdges).append("\n");
        stringBuilder.append("Origine   Poids   Destination").append("\n");

        for (Integer key : this.nodesHashMap.keySet()) {
            stringBuilder.append(this.nodesHashMap.get(key));
        }

        return stringBuilder.toString();
    }

    //endregion
}
