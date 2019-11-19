import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class L3NEW_TG_B9_Node {

    //region Variables

    private Integer label;
    private Set<L3NEW_TG_B9_Node> listPredecessor;
    private Set<L3NEW_TG_B9_Node> listSuccessor;
    private HashMap<L3NEW_TG_B9_Node, Integer> listEdges;
    private int rank;
    private int earliestDate;
    private int latestDate;

    //endregion

    //region Constructor

    public L3NEW_TG_B9_Node(Integer label) {
        this.label = label;
        this.listSuccessor = new HashSet<>();
        this.listPredecessor = new HashSet<>();
        this.listEdges = new HashMap<>();
        this.rank = 0;
        this.earliestDate = 0;
        this.latestDate = 0;
    }

    //endregion

    //region Utils

    public void addEdge(L3NEW_TG_B9_Node destinationNode, int weight) {
        this.listEdges.put(destinationNode, weight);
        this.addSuccessor(destinationNode);
        destinationNode.addPredecessor(this);
    }

    private void removeEdge(L3NEW_TG_B9_Node destinationNode) {
        this.listEdges.remove(destinationNode);
    }

    public void addSuccessor(L3NEW_TG_B9_Node destinationNode) {
        this.listSuccessor.add(destinationNode);
    }

    private void removeSuccessor(L3NEW_TG_B9_Node destinationNode) {
        this.listSuccessor.remove(destinationNode);
    }

    public void addPredecessor(L3NEW_TG_B9_Node originNode) {
        this.listPredecessor.add(originNode);
    }

    private void removePredecessor(L3NEW_TG_B9_Node originNode) {
        this.listPredecessor.remove(originNode);
    }

    public boolean isNodeASuccessor(L3NEW_TG_B9_Node nodeToTest) {
        return this.listSuccessor.contains(nodeToTest);
    }

    public boolean isListPredecessorEmpty() {
        return this.listPredecessor.isEmpty();
    }

    public boolean isListSuccessorEmpty() {
        return this.listSuccessor.isEmpty();
    }

    public Integer getWeightEdge(L3NEW_TG_B9_Node destinationNode) {
        return this.listEdges.get(destinationNode);
    }

    public void destroyLinks() {

        for (L3NEW_TG_B9_Node successor : this.listSuccessor) {
            successor.removePredecessor(this);
        }

        for (L3NEW_TG_B9_Node predecessor : this.listPredecessor) {
            predecessor.removeEdge(this);
            predecessor.removeSuccessor(this);
        }

        this.listSuccessor.clear();
        this.listPredecessor.clear();
        this.listEdges.clear();
    }

    public String displayEdges() {
        StringBuilder stringBuilder = new StringBuilder();

        for (L3NEW_TG_B9_Node successor : this.listEdges.keySet()) {
            stringBuilder.append(String.format("%1$4s", this.label));
            stringBuilder.append(String.format("%1$5s", "=="));
            stringBuilder.append(String.format("%1$4s", this.listEdges.get(successor)));
            stringBuilder.append(String.format("%1$5s", "=>"));
            stringBuilder.append(String.format("%1$4s", successor.label));
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    public void displayCalendar() {
        System.out.println("Node : " + this.getLabel() + " [ " + this.getEarliestDate() + " ; " + this.getLatestDate() + " ]");
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

    public int getRank() {
        return rank;
    }

    public int getEarliestDate() {
        return earliestDate;
    }

    public void setEarliestDate(int earliestDate) {
        this.earliestDate = earliestDate;
    }

    //endregion

    //region Setter

    public int getLatestDate() {
        return latestDate;
    }

    public void setRank(int rank) {
        this.rank = rank;
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

    public void setLatestDate(int latestDate) {
        this.latestDate = latestDate;
    }

    public void setLabel(Integer label) {
        this.label = label;
    }

    public HashMap<L3NEW_TG_B9_Node, Integer> getListEdges() {
        return this.listEdges;
    }

    //endregion

    //region Override

    @Override
    public String toString() {
        return String.valueOf(this.getLabel());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof L3NEW_TG_B9_Node)) return false;
        L3NEW_TG_B9_Node that = (L3NEW_TG_B9_Node) o;
        return getLabel().equals(that.getLabel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLabel());
    }


//endregion
}
