import java.util.ArrayList;
import java.util.HashMap;

public class L3NEW_TG_B9_Graph {
    private int nodesNumber;
    private int directedEdgesNumber;
    private HashMap<String,L3NEW_TG_B9_Node> nodesHashMap;

    public L3NEW_TG_B9_Graph(int nodesNumber, int directedEdgesNumber, HashMap<String,L3NEW_TG_B9_Node> nodesHashMap) {
        this.nodesNumber = nodesNumber;
        this.directedEdgesNumber = directedEdgesNumber;
        this.nodesHashMap = nodesHashMap;
    }

    public L3NEW_TG_B9_Graph() {
        this.nodesNumber = 0;
        this.directedEdgesNumber = 0;
        this.nodesHashMap = new HashMap<>();
    }

    public boolean isNodeAlreadyRegister(String researchNodeLabel){
        return this.nodesHashMap.containsKey(researchNodeLabel);
    }

    public L3NEW_TG_B9_Node getSpecificNodeFromLabel(String nodeLabel){
        return this.nodesHashMap.get(nodeLabel);
    }

    public int getNodesNumber() {
        return nodesNumber;
    }

    public void setNodesNumber(int nodesNumber) {
        this.nodesNumber = nodesNumber;
    }

    public int getDirectedEdgesNumber() {
        return directedEdgesNumber;
    }

    public void setDirectedEdgesNumber(int directedEdgesNumber) {
        this.directedEdgesNumber = directedEdgesNumber;
    }

    public HashMap<String, L3NEW_TG_B9_Node> getNodesHashMap() {
        return nodesHashMap;
    }

    public void setNodesList(HashMap<String, L3NEW_TG_B9_Node> nodesHashMap) {
        this.nodesHashMap = nodesHashMap;
    }

    public void addNodeToGraph(L3NEW_TG_B9_Node newNode){
        this.nodesHashMap.put(newNode.getLabel(), newNode);
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(nodesNumber).append("\n");
        stringBuilder.append(directedEdgesNumber).append("\n");
        for (String key: this.nodesHashMap.keySet()) {
            stringBuilder.append(this.nodesHashMap.get(key));
        }
        return stringBuilder.toString();
    }
}
