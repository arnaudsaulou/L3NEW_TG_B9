import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class L3NEW_TG_B9_Node {

    //region Variables

    private final Integer label;
    private final Set<L3NEW_TG_B9_Node> listPredecessor;
    private final Set<L3NEW_TG_B9_Node> listSuccessor;
    private final HashMap<L3NEW_TG_B9_Node, Integer> listEdges;
    private int rank;
    private int earliestDate;
    private int latestDate;
    private int margin;

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

    /**
     * Add an edge between the node pass in parameters of weight pass in parameters also
     *
     * @param destinationNode the node to link with
     * @param weight          the weight of the edge
     */
    public void addEdge(L3NEW_TG_B9_Node destinationNode, int weight) {
        this.listEdges.put(destinationNode, weight);
        this.addSuccessor(destinationNode);
        destinationNode.addPredecessor(this);
    }

    /**
     * To remove an edge between the node and the destination node pass in parameters
     * @param destinationNode the destination node
     */
    private void removeEdge(L3NEW_TG_B9_Node destinationNode) {
        this.listEdges.remove(destinationNode);
    }

    /**
     * To add a successor to the node
     * @param destinationNode the node to add as a successor
     */
    public void addSuccessor(L3NEW_TG_B9_Node destinationNode) {
        this.listSuccessor.add(destinationNode);
    }

    /**
     * To remove a successor of the node
     * @param destinationNode the node to remove from the successors
     */
    private void removeSuccessor(L3NEW_TG_B9_Node destinationNode) {
        this.listSuccessor.remove(destinationNode);
    }

    /**
     * To add a predecessor to the node
     * @param originNode the node to add as a predecessor
     */
    public void addPredecessor(L3NEW_TG_B9_Node originNode) {
        this.listPredecessor.add(originNode);
    }

    /**
     * To remove a predecessor of the node
     * @param originNode the node to remove from the predecessors
     */
    private void removePredecessor(L3NEW_TG_B9_Node originNode) {
        this.listPredecessor.remove(originNode);
    }

    /**
     * Check if the node pass in parameters is a successor or not
     * @param nodeToTest the note to test
     * @return True if the node pass in parameters is a successor, False else
     */
    public boolean isNodeASuccessor(L3NEW_TG_B9_Node nodeToTest) {
        return this.listSuccessor.contains(nodeToTest);
    }

    /**
     * Check if the list of predecessor is empty
     * @return True if the list of predecessor is empty, False else
     */
    public boolean isListPredecessorEmpty() {
        return this.listPredecessor.isEmpty();
    }

    /**
     * Check if the list of successor is empty
     * @return True if the list of successor is empty, False else
     */
    public boolean isListSuccessorEmpty() {
        return this.listSuccessor.isEmpty();
    }

    /**
     * Get the weight of an edge
     * @param destinationNode The destination node
     * @return the value of the edge
     */
    public Integer getWeightEdge(L3NEW_TG_B9_Node destinationNode) {
        return this.listEdges.get(destinationNode);
    }

    /**
     * To destroy all links of the node from all others nodes
     */
    public void destroyLinks() {

        //Remove the node as a predecessor of all successor
        for (L3NEW_TG_B9_Node successor : this.listSuccessor) {
            successor.removePredecessor(this);
        }

        //Remove the node as a successor of all predecessor
        for (L3NEW_TG_B9_Node predecessor : this.listPredecessor) {
            predecessor.removeEdge(this);
            predecessor.removeSuccessor(this);
        }

        //Clear lists
        this.listSuccessor.clear();
        this.listPredecessor.clear();
        this.listEdges.clear();
    }

    /**
     * To display all edges (and their value)  between the node and all others
     * @return A textual representation of the node links
     */
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

    /**
     * To display the latest date, the earliest date and the margin of the node
     * @return A textual representation of the calendar
     */
    public String displayCalendar() {
        return String.format("%3d", this.getLabel()) +
                String.format("%5s", "|") +
                String.format("%11d", this.getEarliestDate()) +
                String.format("%10s", "|") +
                String.format("%12d", this.getLatestDate()) +
                String.format("%10s", "|") +
                String.format("%5d", this.margin);
    }

    /**
     * To compute margin between latest date and earliest date
     */
    public void computeMargin() {
        this.margin = this.latestDate - this.earliestDate;
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

    public HashMap<L3NEW_TG_B9_Node, Integer> getListEdges() {
        return this.listEdges;
    }

    public int getLatestDate() {
        return latestDate;
    }


    //endregion

    //region Setter

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setLatestDate(int latestDate) {
        this.latestDate = latestDate;
    }

    public void setEarliestDate(int earliestDate) {
        this.earliestDate = earliestDate;
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
