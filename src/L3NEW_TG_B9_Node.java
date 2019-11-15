import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class L3NEW_TG_B9_Node {

    //region Variables

    private String label;
    private Set<L3NEW_TG_B9_Node> listPredecessor;
    private Set<L3NEW_TG_B9_Node> listSuccessor;
    private HashMap<L3NEW_TG_B9_Node, Integer> listEdges;

    //endregion

    //region Constructor

    public L3NEW_TG_B9_Node(String label) {
        this.label = label;
        this.listSuccessor = new HashSet<>();
        this.listPredecessor = new HashSet<>();
        this.listEdges = new HashMap<>();
    }

    //endregion

    //region Utils

    public void addEdge(L3NEW_TG_B9_Node destinationNode, int weight) {
        this.listEdges.put(destinationNode, weight);
        this.listSuccessor.add(destinationNode);
    }

    public boolean isNodeASuccessor(L3NEW_TG_B9_Node nodeToTest){
        return this.listSuccessor.contains(nodeToTest);
    }

    public Integer getWeightEdge(L3NEW_TG_B9_Node destinationNode){
        return this.listEdges.get(destinationNode);
    }

    //endregion

    //region Getter

    public String getLabel() {
        return label;
    }

    public Set<L3NEW_TG_B9_Node> getListPredecessor() {
        return listPredecessor;
    }

    public Set<L3NEW_TG_B9_Node> getListSuccessor() {
        return listSuccessor;
    }

    public HashMap<L3NEW_TG_B9_Node, Integer> getListEdges() {
        return listEdges;
    }

    //endregion

    //region Setter

    public void setLabel(String label) {
        this.label = label;
    }

    public void setListPredecessor(Set<L3NEW_TG_B9_Node> listPredecessor) {
        this.listPredecessor = listPredecessor;
    }

    public void setListSuccessor(Set<L3NEW_TG_B9_Node> listSuccessor) {
        this.listSuccessor = listSuccessor;
    }

    public void setListEdges(HashMap<L3NEW_TG_B9_Node, Integer> listEdges) {
        this.listEdges = listEdges;
    }

    //endregion

    //region Override

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (L3NEW_TG_B9_Node successor : this.listEdges.keySet()) {
            stringBuilder.append(String.format("%4s",this.label));
            stringBuilder.append(String.format("%5s","=="));
            stringBuilder.append(String.format("%4s",this.listEdges.get(successor)));
            stringBuilder.append(String.format("%5s","=>"));
            stringBuilder.append(String.format("%4s",successor.label));
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }


    //endregion
}
