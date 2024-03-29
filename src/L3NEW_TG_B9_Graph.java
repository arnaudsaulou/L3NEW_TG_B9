import java.util.HashMap;

public class L3NEW_TG_B9_Graph {

    //region Variables

    private int numberOfNode;
    private int numberOfEdges;
    private HashMap<String, L3NEW_TG_B9_Node> nodesHashMap;

    //endregion

    //region Constructor

    public L3NEW_TG_B9_Graph() {
        this.numberOfNode = 0;
        this.numberOfEdges = 0;
        this.nodesHashMap = new HashMap<>();
    }

    //endregion

    //region Utils

    public boolean isNodeNotAlreadyRegister(String researchNodeLabel) {
        return !this.nodesHashMap.containsKey(researchNodeLabel);
    }

    public void addNodeToGraph(L3NEW_TG_B9_Node newNode) {
        this.nodesHashMap.put(newNode.getLabel(), newNode);
    }

    public L3NEW_TG_B9_Node getSpecificNodeFromLabel(String nodeLabel) {
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
        System.out.print(" ");  //To align columns header with columns

        //Display columns header
        for (String nodeLabel : this.nodesHashMap.keySet()) {
            System.out.print(String.format("%5s", nodeLabel));
        }
    }

    public void displayAdjacencyMatrix() {

        System.out.print("\n - - - - - Matrice d'adjacence - - - - - \n");

        this.displayColumnsHeadersMatrix();

        //For each origin node
        for (String originNodeLabel : this.nodesHashMap.keySet()) {

            //Display row header
            System.out.print("\n" + originNodeLabel);

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
        for (String originNodeLabel : this.nodesHashMap.keySet()) {

            //Display row header
            System.out.print("\n" + originNodeLabel);

            //Get originNode
            L3NEW_TG_B9_Node originNode = this.getSpecificNodeFromLabel(originNodeLabel);

            fillValueOfANode(originNode);
        }

        System.out.println();
    }

    //endregion

    //region Getter

    public int getNumberOfNode() {
        return numberOfNode;
    }

    public int getNumberOfEdges() {
        return numberOfEdges;
    }

    public HashMap<String, L3NEW_TG_B9_Node> getNodesHashMap() {
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

    public void setNodesHashMap(HashMap<String, L3NEW_TG_B9_Node> nodesHashMap) {
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

        for (String key : this.nodesHashMap.keySet()) {
            stringBuilder.append(this.nodesHashMap.get(key));
        }

        return stringBuilder.toString();
    }

    //endregion
}
