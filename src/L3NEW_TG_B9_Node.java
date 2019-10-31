import java.util.ArrayList;

public class L3NEW_TG_B9_Node {

    //region Variables

    private String label;
    private ArrayList<L3NEW_TG_B9_DirectedEdge> exitingDirectedEdges;
    private ArrayList<L3NEW_TG_B9_DirectedEdge> incomingDirectedEdges;
    private ArrayList<L3NEW_TG_B9_Node> listPredecessor;
    private ArrayList<L3NEW_TG_B9_Node> listSuccessor;

    //endregion

    //region Constructor

    public L3NEW_TG_B9_Node(String label) {
        this.label = label;
        this.exitingDirectedEdges = new ArrayList<>();
        this.incomingDirectedEdges =  new ArrayList<>();
        this.listSuccessor =  new ArrayList<>();
        this.listPredecessor = new ArrayList<>();
    }

    //endregion

    //region Utils

    public void addExitingDirectedEdges(L3NEW_TG_B9_DirectedEdge newEdge){
        this.exitingDirectedEdges.add(newEdge);
        this.listSuccessor.add(newEdge.getDestinationNode());
    }

    public void addIncomingDirectedEdges(L3NEW_TG_B9_DirectedEdge newEdge){
        this.incomingDirectedEdges.add(newEdge);
        this.listSuccessor.add(newEdge.getOriginNode());
    }

    //endregion

    //region Getter

    public String getLabel() {
        return label;
    }

    public ArrayList<L3NEW_TG_B9_DirectedEdge> getExitingDirectedEdges() {
        return exitingDirectedEdges;
    }

    public ArrayList<L3NEW_TG_B9_DirectedEdge> getIncomingDirectedEdges() {
        return incomingDirectedEdges;
    }

    public ArrayList<L3NEW_TG_B9_Node> getListPredecessor() {
        return listPredecessor;
    }

    public ArrayList<L3NEW_TG_B9_Node> getListSuccessor() {
        return listSuccessor;
    }

    //endregion

    //region Setter

    public void setLabel(String label) {
        this.label = label;
    }

    public void setExitingDirectedEdges(ArrayList<L3NEW_TG_B9_DirectedEdge> exitingDirectedEdges) {
        this.exitingDirectedEdges = exitingDirectedEdges;
    }

    public void setIncomingDirectedEdges(ArrayList<L3NEW_TG_B9_DirectedEdge> incomingDirectedEdges) {
        this.incomingDirectedEdges = incomingDirectedEdges;
    }

    public void setListPredecessor(ArrayList<L3NEW_TG_B9_Node> listPredecessor) {
        this.listPredecessor = listPredecessor;
    }

    public void setListSuccessor(ArrayList<L3NEW_TG_B9_Node> listSuccessor) {
        this.listSuccessor = listSuccessor;
    }

    //endregion

    //region Override

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (L3NEW_TG_B9_DirectedEdge directedEdge: this.exitingDirectedEdges) {
            stringBuilder.append(directedEdge).append('\n');
        }
        return stringBuilder.toString();
    }

    //endregion
}
