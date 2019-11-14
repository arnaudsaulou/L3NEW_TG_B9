import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class L3NEW_TG_B9_Graph {

    //region Variables

    private int nodesNumber;
    private int directedEdgesNumber;
    private HashMap<String, L3NEW_TG_B9_Node> nodesHashMap;

    //endregion

    //region Constructor

    public L3NEW_TG_B9_Graph() {
        this.nodesNumber = 0;
        this.directedEdgesNumber = 0;
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

    private boolean isDestinationNodeSuccessor(Set<L3NEW_TG_B9_Node> listSuccessor, L3NEW_TG_B9_Node destinationNode) {
        return listSuccessor.contains(destinationNode);
    }

    private void fillAdjacencyOfANode(Set<L3NEW_TG_B9_Node> listSuccessor) {

        //For each destination node
        for (String destinationNodeLabel : this.nodesHashMap.keySet()) {
            L3NEW_TG_B9_Node destinationNode = this.getSpecificNodeFromLabel(destinationNodeLabel);

            //Check if destinationNode is a successor of the originNode
            if (isDestinationNodeSuccessor(listSuccessor, destinationNode)) {
                System.out.print(String.format("%5s", "V"));
            } else {
                System.out.print(String.format("%5s", "F"));
            }
        }
    }

    private void fillValueOfANode(L3NEW_TG_B9_Node originNode) {

        //For each destination node

    }

    private void displayColumnsHeadersMatrix() {
        System.out.print("\n ");  //To align columns header with columns

        //Display columns header
        for (String nodeLabel : this.nodesHashMap.keySet()) {
            System.out.print(String.format("%5s", nodeLabel));
        }
    }

    public void displayAdjacencyMatrix() {

        this.displayColumnsHeadersMatrix();

        //For each origin node
        for (String originNodeLabel : this.nodesHashMap.keySet()) {

            //Display row header
            System.out.print("\n" + originNodeLabel);

            //Get list successor
            Set<L3NEW_TG_B9_Node> listSuccessor = this.getSpecificNodeFromLabel(originNodeLabel).getListSuccessor();

            fillAdjacencyOfANode(listSuccessor);
        }

        System.out.println();
    }

    public void displayValuesMatrix() {

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

    public int getNodesNumber() {
        return nodesNumber;
    }

    public int getDirectedEdgesNumber() {
        return directedEdgesNumber;
    }

    public HashMap<String, L3NEW_TG_B9_Node> getNodesHashMap() {
        return nodesHashMap;
    }

    //endregion

    //region Setter

    public void setNodesNumber(int nodesNumber) {
        this.nodesNumber = nodesNumber;
    }

    public void setDirectedEdgesNumber(int directedEdgesNumber) {
        this.directedEdgesNumber = directedEdgesNumber;
    }

    public void setNodesList(HashMap<String, L3NEW_TG_B9_Node> nodesHashMap) {
        this.nodesHashMap = nodesHashMap;
    }

    //endregion

    //region Override

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        stringBuilder.append("Nombre de sommet(s) : ").append(nodesNumber).append("\n");
        stringBuilder.append("Nombre d'arc(s) : ").append(directedEdgesNumber).append("\n");
        stringBuilder.append("Origine   Poids   Destination").append("\n");

        for (String key : this.nodesHashMap.keySet()) {
            stringBuilder.append(this.nodesHashMap.get(key));
        }

        return stringBuilder.toString();
    }

    //endregion
}
