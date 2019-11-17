import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class L3NEW_TG_B9_Node {

    //region Variables

    private Integer label;
    private Set<L3NEW_TG_B9_Node> listPredecessor;
    private Set<L3NEW_TG_B9_Node> listSuccessor;
    private HashMap<L3NEW_TG_B9_Node, Integer> listEdges;

    //endregion

    //region Constructor

    public L3NEW_TG_B9_Node(Integer label) {
        this.label = label;
        this.listSuccessor = new HashSet<>();
        this.listPredecessor = new HashSet<>();
        this.listEdges = new HashMap<>();
    }

    //endregion

    //region Utils

    public void addEdge(L3NEW_TG_B9_Node destinationNode, int weight) {
        this.listEdges.put(destinationNode, weight);
        this.addSuccessor(destinationNode);
        destinationNode.addPredecessor(this);
    }

    public void removeEdge(L3NEW_TG_B9_Node destinationNode){
        this.listEdges.remove(destinationNode);
    }

    public void addSuccessor(L3NEW_TG_B9_Node destinationNode){
        this.listSuccessor.add(destinationNode);
    }

    public void removeSuccessor(L3NEW_TG_B9_Node destinationNode){
        this.listPredecessor.remove(destinationNode);
    }

    public void addPredecessor(L3NEW_TG_B9_Node originNode){
        this.listPredecessor.add(originNode);
    }

    public void removePredecessor(L3NEW_TG_B9_Node originNode){
        this.listPredecessor.remove(originNode);
    }

    public boolean isNodeASuccessor(L3NEW_TG_B9_Node nodeToTest){
        return this.listSuccessor.contains(nodeToTest);
    }

    public Integer getWeightEdge(L3NEW_TG_B9_Node destinationNode){
        return this.listEdges.get(destinationNode);
    }

    //endregion

    //region Getter

    public Integer getLabel() {
        return this.label;
    }

    public Set<L3NEW_TG_B9_Node> getListPredecessor() {
        return this.listPredecessor;
    }

    public Set<L3NEW_TG_B9_Node> getListSuccessor() {
        return this.listSuccessor;
    }

    public HashMap<L3NEW_TG_B9_Node, Integer> getListEdges() {
        return this.listEdges;
    }

    //endregion

    //region Setter

    public void setLabel(Integer label) {
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
            stringBuilder.append(String.format("%1$4s",this.label));
            stringBuilder.append(String.format("%1$5s","=="));
            stringBuilder.append(String.format("%1$4s",this.listEdges.get(successor)));
            stringBuilder.append(String.format("%1$5s","=>"));
            stringBuilder.append(String.format("%1$4s",successor.label));
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof L3NEW_TG_B9_Node)) return false;
        L3NEW_TG_B9_Node that = (L3NEW_TG_B9_Node) o;
        return getLabel().equals(that.getLabel()) &&
                getListPredecessor().equals(that.getListPredecessor()) &&
                getListSuccessor().equals(that.getListSuccessor()) &&
                getListEdges().equals(that.getListEdges());
    }

    //endregion
}
