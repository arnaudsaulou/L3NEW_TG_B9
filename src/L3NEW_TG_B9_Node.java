import java.util.ArrayList;
import java.util.Objects;

public class L3NEW_TG_B9_Node {
    private String label;
    private ArrayList<L3NEW_TG_B9_DirectedEdge> listPredecessor;
    private ArrayList<L3NEW_TG_B9_DirectedEdge> listSuccessor;


    public L3NEW_TG_B9_Node(String label, ArrayList<L3NEW_TG_B9_DirectedEdge> listPredecessor, ArrayList<L3NEW_TG_B9_DirectedEdge> listSuccessor) {
        this.label = label;
        this.listPredecessor = listPredecessor;
        this.listSuccessor = listSuccessor;
    }

    public L3NEW_TG_B9_Node(String label) {
        this.label = label;
        this.listPredecessor = new ArrayList<>();
        this.listSuccessor =  new ArrayList<>();
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ArrayList<L3NEW_TG_B9_DirectedEdge> getListSuccessor() {
        return listSuccessor;
    }

    public void setListSuccessor(ArrayList<L3NEW_TG_B9_DirectedEdge> listSuccessor) {
        this.listSuccessor = listSuccessor;
    }

    public ArrayList<L3NEW_TG_B9_DirectedEdge> getListPredecessor() {
        return listPredecessor;
    }

    public void setListPredecessor(ArrayList<L3NEW_TG_B9_DirectedEdge> listPredecessor) {
        this.listPredecessor = listPredecessor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof L3NEW_TG_B9_Node)) return false;
        L3NEW_TG_B9_Node that = (L3NEW_TG_B9_Node) o;
        return getLabel().equals(that.getLabel()) &&
                getListPredecessor().equals(that.getListPredecessor()) &&
                getListSuccessor().equals(that.getListSuccessor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLabel(), getListSuccessor(), getListPredecessor());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (L3NEW_TG_B9_DirectedEdge directedEdge: this.listSuccessor) {
            stringBuilder.append(directedEdge).append('\n');
        }
        return stringBuilder.toString();
    }
}
