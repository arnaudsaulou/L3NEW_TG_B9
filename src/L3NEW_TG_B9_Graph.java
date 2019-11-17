import java.util.HashMap;
import java.util.Set;

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
            System.out.print("\n" + String.format("%3s",originNodeLabel));

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
            System.out.print("\n" + String.format("%3s",originNodeLabel));

            //Get originNode
            L3NEW_TG_B9_Node originNode = this.getSpecificNodeFromLabel(originNodeLabel);

            fillValueOfANode(originNode);
        }

        System.out.println();
    }

    public void circuitDetection(){

        //For each origin node

        while(!this.nodesHashMap.keySet().isEmpty()){

            Integer originNodeLabel = this.nodesHashMap.get().;

            System.out.println();

            //Get originNode
            L3NEW_TG_B9_Node originNode = this.getSpecificNodeFromLabel(originNodeLabel);

            if(originNode.getListPredecessor().isEmpty()){
                for (L3NEW_TG_B9_Node sucessor : originNode.getListSuccessor()){
                    originNode.removeEdge(sucessor);
                }
                this.nodesHashMap.remove(originNode.getLabel());
            }

        }

        System.out.println(this);


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
